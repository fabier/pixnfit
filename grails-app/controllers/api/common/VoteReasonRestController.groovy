package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.StaticDataRestfulController
import pixnfit.VoteReason

@Secured("permitAll")
class VoteReasonRestController extends StaticDataRestfulController {
    VoteReasonRestController() {
        super(VoteReason, true)
    }
}
