package pixnfit

import org.springframework.security.access.annotation.Secured

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Secured("permitAll")
class ImageController {

    ImageService imageService

    def show(long id) {
        Image image = Image.get(id)
        if (image != null) {
            ImageData imageData = image.imageData
            if (imageData != null) {
                byte[] data = imageData.data
                if (data != null) {
                    Integer width = params.width ? Integer.parseInt(params.width) : null
                    Integer height = params.height ? Integer.parseInt(params.height) : null
                    if (width != null && height != null) {
                        // Rescale image
                        BufferedImage bufferedImage = ImageIO.read(ImageIO.createImageInputStream(new ByteArrayInputStream(data)))
                        BufferedImage rescaled = imageService.getScaledImageCropCentered(bufferedImage, width, height)
                        render file: imageService.getDataAsJPEG(rescaled), contentType: "image/jpeg"
                    } else {
                        render file: data, contentType: imageData?.imageType?.defaultMimeType?.mimetype
                    }
                } else {
                    // Dans tous les autres cas, erreur 404
                    response.sendError(404)
                }
            } else {
                // Dans tous les autres cas, erreur 404
                response.sendError(404)
            }
        } else {
            // Dans tous les autres cas, erreur 404
            response.sendError(404)
        }
    }
}
