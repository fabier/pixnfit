package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Gender
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class PostTypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    PostTypeRestController() {
        super(Gender)
    }
}
