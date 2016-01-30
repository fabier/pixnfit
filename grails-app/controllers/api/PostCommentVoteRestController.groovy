package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.PostCommentVote

@Secured("hasRole('ROLE_USER')")
class PostCommentVoteRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    PostCommentVoteRestController(){
        super(PostCommentVote)
    }
}
