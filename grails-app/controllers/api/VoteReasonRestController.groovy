package api

import org.springframework.security.access.annotation.Secured
import pixnfit.StaticDataRestfulController
import pixnfit.VoteReason

@Secured("hasRole('ROLE_USER')")
class VoteReasonRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    VoteReasonRestController() {
        super(VoteReason)
    }
}
