package api

import grails.rest.RestfulController
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.User

/**
 * On n'a que le droit que de voir la fiche pour un utilisateur donné,
 * ou de mettre à jour la fiche d'un utilisateur,
 * à condition d'être loggué en tant que cet utilisateur
 */
@Secured("hasRole('ROLE_USER')")
class UserRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    UserRestController() {
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

    def save() {
        // Interdit de créer les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def update() {
        // Interdit de mettre à jour les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def delete() {
        // Interdit de supprimer les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }
}
