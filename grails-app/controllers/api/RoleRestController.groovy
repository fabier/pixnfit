package api

import grails.rest.RestfulController
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.Role
import pixnfit.User

/**
 * Il n'est pas autorisé de modifier, éditer ou supprimer un role,
 * mais on peut voir les informations de role
 */
@Secured("hasRole('ROLE_USER')")
class RoleRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RoleRestController() {
        super(User)
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
