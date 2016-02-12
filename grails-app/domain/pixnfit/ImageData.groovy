package pixnfit

import org.apache.commons.codec.digest.DigestUtils

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream
import java.awt.image.BufferedImage

/**
 * One-to-One relationship with Image, containing only the image's data (plus a md5 hash)
 */
class ImageData extends BaseEntity {

    /**
     * Raw data
     */
    byte[] data

    /**
     * MD5 Hash for the data
     * Always 32 characters long
     */
    String md5

    /**
     * Original filename
     * Ex : DSC_OOO1.JPG
     */
    String filename

    /**
     * Image Width
     */
    Integer width

    /**
     * Image Height
     */
    Integer height

    /**
     * ImageType, PNG/JPEG/GIF
     */
    ImageType imageType

    /**
     * The image using this data
     */
    Image image

    static belongsTo = [image: Image]

    static constraints = {
        md5 blank: false, size: 32..32
        filename blank: false
        width nullable: false, validator: { val, obj -> val >= 0 }
        height nullable: false, validator: { val, obj -> val >= 0 }
    }

    def beforeValidate() {
        if (md5 == null || isDirty("data")) {
            updateAutoCalculatedFields()
        }
    }

    def updateAutoCalculatedFields() {
        // Update MD5 Hash
        if (data != null) {
            md5 = DigestUtils.md5Hex(data)
        } else {
            md5 = null
        }

        // Update ImageType
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(data))
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream)
        List<String> javaFormatNames = new ArrayList<String>()
        ImageType imageTypeByJavaName = null
        while (imageTypeByJavaName == null && imageReaders.hasNext()) {
            ImageReader imageReader = imageReaders.next()
            String formatName = imageReader.getFormatName()
            javaFormatNames.add(formatName)
            imageTypeByJavaName = ImageType.findByJavaFormatName(formatName)
        }
        if (!javaFormatNames.isEmpty()) {
            if (imageTypeByJavaName == null) {
                log.warn "Impossible to find an ImageType for JavaFormatNames : ${Arrays.deepToString(javaFormatNames)}"
                return false
            } else {
                imageType = imageTypeByJavaName

                // Update width & height
                BufferedImage bufferedImage = ImageIO.read(imageInputStream)
                width = bufferedImage.getWidth()
                height = bufferedImage.getHeight()
                return true
            }
        } else {
            log.warn "Impossible to find an ImageReader for ImageData id=${this.id}"
            return false
        }
    }
}
