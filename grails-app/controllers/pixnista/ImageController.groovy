package pixnista

import org.springframework.security.access.annotation.Secured

@Secured("permitAll")
class ImageController {

    def show(long id) {
        Image image = Image.get(id)
        if (image != null) {
            render file: image.imageData?.data, contentType: image.imageType?.defaultMimeType?.mimetype
        } else {
            response.sendError(404)
        }
    }
}
