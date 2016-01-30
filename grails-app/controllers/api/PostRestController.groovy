package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.Post

@Secured("hasRole('ROLE_USER')")
class PostRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    PostRestController() {
        super(Post)
    }
}
