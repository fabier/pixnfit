package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.Gender
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class GenderRestController extends StaticDataRestfulController {
    GenderRestController() {
        super(Gender, true)
    }
}
