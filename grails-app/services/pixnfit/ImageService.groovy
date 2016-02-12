package pixnfit

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifIFD0Directory
import com.sun.image.codec.jpeg.JPEGCodec
import com.sun.image.codec.jpeg.JPEGEncodeParam
import com.sun.image.codec.jpeg.JPEGImageEncoder
import grails.transaction.Transactional
import org.imgscalr.Scalr

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Transactional
class ImageService {

    DownloadService downloadService
    ImageTypeService imageTypeService

    Image createDefaultLoremIpsumImage(User creator) {
        createLoremIpsumImage(creator, 256, 256, "selfie", "fashion")
    }

    Image createLoremIpsumImage(User creator, int width, int height, String... keywords) {
        String keywordTag = keywords.join(",")
        String filename = "loremflickr_${keywords.join("_")}.jpg"
        String name = "${keywords.join(" ")} (${width}x${height})"
        String url = "http://www.loremflickr.com/$width/$height/${keywordTag}"
        byte[] data = downloadService.download(url)

        ImageData imageData = new ImageData(
                name: name,
                filename: filename,
                data: data
        )
        imageData.setCreator(creator)
        imageData.updateAutoCalculatedFields()

        Image image = new Image(
                name: name,
                imageData: imageData
        )
        // On ne doit pas le mettre dans le constructeur, sinon user.image se met à jour avec cette valeur !!!
        image.setCreator(creator)
        image.save(failOnError: true)

        return image
    }

    byte[] getDataAsJPEG(BufferedImage bufferedImage, float quality = 0.9) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream)
        JPEGEncodeParam jpegEncodeParam = jpegImageEncoder.getDefaultJPEGEncodeParam(bufferedImage)

        jpegEncodeParam.setQuality(quality, true)
        jpegImageEncoder.setJPEGEncodeParam(jpegEncodeParam)
        jpegImageEncoder.encode(bufferedImage)

        byte[] data = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()

        return data
    }

    BufferedImage getScaledImageCropCenteredAndKeepOrientationUsingEXIFMetadata(byte[] data, int width, int height) {
        OrientationInfo orientationInfo = getOrientationInformation(data)

        // Cette image est potentiellement mal orientée car ImageIO ne lit pas les données EXIF
        BufferedImage sourceImage = ImageIO.read(new ByteArrayInputStream(data))

        // Dans le but d'optimiser les calculs, on retaille l'image AVANT de faire la rotation
        // Si on a une rotation de 90°, on doit inverser width et height avant de faire le rescale, puis rotate
        // Si la rotation finale fait intervertir largeur et hauteur, on le prend en compte dès maintenant
        if (orientationInfo.invertWidthAndHeight) {
            // Swap width and height to create scaled BufferedImage
            int swap = width
            width = height
            height = swap
        }

        // On retaille l'image à la dimension souhaitée
        // largeur de l'image qui est en fait peut être la hauteur si on a une rotation à 90°
        int sourceWidth = sourceImage.getWidth()
        // hauteur de l'image qui est en fait peut être la largeur si on a une rotation à 90°
        int sourceHeight = sourceImage.getHeight()

        // Comparaison des ratios
        float initialRatio = sourceWidth / sourceHeight
        float finalRatio = width / height

        int dxSubimage = 0
        int dySubimage = 0
        int subimageWidth = sourceWidth
        int subimageHeight = sourceHeight

        if (initialRatio >= finalRatio) {
            // Crop left and right
            subimageWidth = Math.round(finalRatio * sourceHeight) // sera inférieur ou égal à sourceWidth
            dxSubimage = Math.floor((sourceWidth - subimageWidth) / 2)
        } else {
            // Crop top and bottom
            subimageHeight = Math.round(sourceWidth / finalRatio) // sera inférieur ou égal à sourceHeight
            dySubimage = Math.floor((sourceHeight - subimageHeight) / 2)
        }

        // L'image croppée aura la même orientation que l'image initialement décodée par ImageIO
        BufferedImage croppedImage = sourceImage.getSubimage(dxSubimage, dySubimage, subimageWidth, subimageHeight)

        // L'image retaillée aura la même orientation que l'image initialement décodée par ImageIO
        BufferedImage scaledImage = Scalr.resize(croppedImage, Scalr.Method.ULTRA_QUALITY, width, height, Scalr.OP_ANTIALIAS)

        // Si on a des rotations à effectuer, alors on les applique maintenant, sur l'image qui est déjà plus petite
        if (orientationInfo.rotations != null) {
            for (Scalr.Rotation rotation in orientationInfo.rotations) {
                scaledImage = Scalr.rotate(scaledImage, rotation)
            }
        }

        // Une fois que les rotations contenues dans les données EXIF auront été prises en compte,
        // les paramètres width et height seront à nouveau réinversés

        // L'image servie est maintenant retaillée, avec l'orientation telle que définie dans le EXIF
        // Les données EXIF auront disparu.
        return scaledImage
    }

    OrientationInfo getOrientationInformation(byte[] data) {
        OrientationInfo orientationInfo = new OrientationInfo()

        // On cherche dans les données EXIF si on a une rotation enregistrée
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)
        Metadata metadata = ImageMetadataReader.readMetadata(byteArrayInputStream)
        if (metadata != null) {
            ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class)
            if (exifIFD0Directory != null) {
                try {
                    int orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION)
                    switch (orientation) {
                        case 1:
                            break
                        case 2: // Flip X
                            orientationInfo.rotations = [Scalr.Rotation.FLIP_HORZ]
                            break
                        case 3: // PI rotation
                            orientationInfo.rotations = [Scalr.Rotation.CW_180]
                            break
                        case 4: // Flip Y
                            orientationInfo.rotations = [Scalr.Rotation.FLIP_VERT]
                            break
                        case 5: // - PI/2 and Flip X
                            orientationInfo.invertWidthAndHeight = true
                            orientationInfo.rotations = [Scalr.Rotation.CW_90, Scalr.Rotation.FLIP_HORZ]
                            break
                        case 6: // -PI/2 and -width
                            orientationInfo.invertWidthAndHeight = true
                            orientationInfo.rotations = [Scalr.Rotation.CW_90]
                            break
                        case 7: // PI/2 and Flip
                            orientationInfo.invertWidthAndHeight = true
                            orientationInfo.rotations = [Scalr.Rotation.CW_90, Scalr.Rotation.FLIP_VERT]
                            break
                        case 8: // PI / 2
                            orientationInfo.invertWidthAndHeight = true
                            orientationInfo.rotations = [Scalr.Rotation.CW_270]
                            break
                        default:
                            break
                    }
                } catch (Exception ex) {
                    log.debug "No EXIF information found for image", ex
                }
            }
        }

        return orientationInfo
    }
}

class OrientationInfo {
    boolean invertWidthAndHeight = false
    Scalr.Rotation[] rotations = null
}
