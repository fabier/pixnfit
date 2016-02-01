package api

import org.springframework.security.access.annotation.Secured
import pixnfit.State
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class StateRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    StateRestController() {
        super(State)
    }
}
