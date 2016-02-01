package api

import org.springframework.security.access.annotation.Secured
import pixnfit.BodyType
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class BodyTypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    BodyTypeRestController() {
        super(BodyType)
    }
}
