package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.StaticDataRestfulController
import pixnfit.Visibility

@Secured("hasRole('ROLE_USER')")
class VisibilityRestController extends StaticDataRestfulController {
    VisibilityRestController() {
        super(Visibility, true)
    }
}
