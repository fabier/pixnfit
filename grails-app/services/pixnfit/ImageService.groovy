package pixnfit

import com.sun.image.codec.jpeg.JPEGCodec
import com.sun.image.codec.jpeg.JPEGEncodeParam
import com.sun.image.codec.jpeg.JPEGImageEncoder
import grails.transaction.Transactional
import org.imgscalr.Scalr

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
                creator: creator,
                name: name,
                filename: filename,
                data: data
        )
        imageData.updateAutoCalculatedFields()

        Image image = new Image(
                creator: creator,
                name: name,
                imageData: imageData
        )
        image.save(failOnError: true)

        return image
    }

    BufferedImage getScaledImageCropCentered(BufferedImage source, int width, int height) {
        int sourceWidth = source.getWidth()
        int sourceHeight = source.getHeight()
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

        BufferedImage croppedImage = source.getSubimage(dxSubimage, dySubimage, subimageWidth, subimageHeight)

        BufferedImage resized = Scalr.resize(croppedImage, Scalr.Method.ULTRA_QUALITY, width, height, Scalr.OP_ANTIALIAS);

        return resized
    }

    byte[] getDataAsJPEG(BufferedImage bufferedImage, float quality = 0.9) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream)
        JPEGEncodeParam jpegEncodeParam = jpegImageEncoder.getDefaultJPEGEncodeParam(bufferedImage);

        jpegEncodeParam.setQuality(quality, true);
        jpegImageEncoder.setJPEGEncodeParam(jpegEncodeParam);
        jpegImageEncoder.encode(bufferedImage);

        byte[] data = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()

        return data
    }
}
