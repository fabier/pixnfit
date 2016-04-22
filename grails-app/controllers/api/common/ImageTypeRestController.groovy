package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.ImageType
import pixnfit.StaticDataRestfulController

@Secured("permitAll")
class ImageTypeRestController extends StaticDataRestfulController {
    ImageTypeRestController() {
        super(ImageType, true)
    }
}
