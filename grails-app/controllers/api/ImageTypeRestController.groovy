package api

import org.springframework.security.access.annotation.Secured
import pixnfit.ImageType
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class ImageTypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    ImageTypeRestController() {
        super(ImageType)
    }
}
