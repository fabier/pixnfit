package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.User

@Secured("hasRole('ROLE_USER')")
class UserRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    UserRestController(){
        super(User)
    }
}
