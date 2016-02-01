package api

import grails.rest.RestfulController
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.Role
import pixnfit.User

/**
 * Il n'est pas autorisé de lister les roles, mais on peut voir pour un identifiant de role donné les informations de ce role
 */
@Secured("hasRole('ROLE_USER')")
class RoleRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RoleRestController() {
        super(User)
    }

    def index(Integer max) {
        // Interdit de lister les roles depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def show(Role role) {
        respond role
    }

    def save() {
        // Interdit de créer les roles depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def update() {
        // Interdit de mettre à jour les roles depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def delete() {
        // Interdit de supprimer les roles depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }
}
