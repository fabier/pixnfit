package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.StaticDataRestfulController
import pixnfit.Visibility

@Secured("permitAll")
class VisibilityRestController extends StaticDataRestfulController {
    VisibilityRestController() {
        super(Visibility, true)
    }
}
