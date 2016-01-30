package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.VoteReason

@Secured("hasRole('ROLE_USER')")
class VoteReasonRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    VoteReasonRestController() {
        super(VoteReason)
    }
}
