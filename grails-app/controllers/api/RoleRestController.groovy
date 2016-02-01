package api

import grails.rest.RestfulController
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.User

@Secured("hasRole('ROLE_USER')")
class RoleRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    static allowedMethods = [create: 'POST']

    RoleRestController() {
        super(User)
    }

    def index(Integer max) {
        // Interdit de lister les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def show() {
        // Interdit de lister les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def create() {

    }
}
