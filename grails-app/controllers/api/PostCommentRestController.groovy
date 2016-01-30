package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.PostComment

@Secured("hasRole('ROLE_USER')")
class PostCommentRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    PostCommentRestController() {
        super(PostComment)
    }
}
