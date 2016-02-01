package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Mimetype
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class MimetypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    MimetypeRestController() {
        super(Mimetype)
    }
}
