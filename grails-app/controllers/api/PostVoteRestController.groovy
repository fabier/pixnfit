package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.PostVote

@Secured("hasRole('ROLE_USER')")
class PostVoteRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    PostVoteRestController(){
        super(PostVote)
    }
}
