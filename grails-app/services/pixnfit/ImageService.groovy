package pixnfit

import com.sun.image.codec.jpeg.JPEGCodec
import com.sun.image.codec.jpeg.JPEGEncodeParam
import com.sun.image.codec.jpeg.JPEGImageEncoder
import grails.transaction.Transactional
import pixnfit.Image

import java.awt.*
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
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int sourceWidth = source.getWidth()
        int sourceHeight = source.getHeight()
        float initialRatio = sourceWidth / sourceHeight
        float finalRatio = width / height
        int dx = 0
        int dy = 0

        if (initialRatio >= finalRatio) {
            // Crop left and right
            dx = Math.round((initialRatio - finalRatio) * sourceHeight)
        } else {
            // Crop top and bottom
            dy = Math.round(sourceWidth / (finalRatio - initialRatio))
        }

        BufferedImage croppedImage = source.getSubimage((int) (dx / 2), (int) (dy / 2), source.getWidth() - dx, source.getHeight() - dy)

//        g2d.drawImage(source, (int) (dx / 2), (int) (dy / 2), source.getWidth() - dx, source.getHeight() - dy, null);
        g2d.drawImage(croppedImage, 0, 0, width, height, null)
        g2d.dispose();

        return resized
    }

    byte[] getDataAsJPEG(BufferedImage bufferedImage, float quality = 0.95) {
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
