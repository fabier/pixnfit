package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.Image

@Secured("hasRole('ROLE_USER')")
class ImageRestController extends DynamicDataRestfulController {

    ImageRestController() {
        super(Image)
    }

    def data() {
        Image image = Image.get(params.imageRestId)
        respond image.imageData
    }
}
