package api

import org.springframework.security.access.annotation.Secured
import pixnfit.StaticDataRestfulController
import pixnfit.Visibility

@Secured("hasRole('ROLE_USER')")
class VisibilityRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    VisibilityRestController() {
        super(Visibility)
    }
}
