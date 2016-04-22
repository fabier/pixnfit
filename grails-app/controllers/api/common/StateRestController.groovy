package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.State
import pixnfit.StaticDataRestfulController

@Secured("permitAll")
class StateRestController extends StaticDataRestfulController {
    StateRestController() {
        super(State, true)
    }
}
