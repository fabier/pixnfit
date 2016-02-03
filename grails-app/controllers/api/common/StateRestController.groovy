package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.State
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class StateRestController extends StaticDataRestfulController {
    StateRestController() {
        super(State, true)
    }
}
