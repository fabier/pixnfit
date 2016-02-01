package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Country
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class MimetypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    MimetypeRestController() {
        super(Country)
    }
}
