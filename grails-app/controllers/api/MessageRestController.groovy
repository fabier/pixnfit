package api

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.Message
import pixnfit.User

@Secured("hasRole('ROLE_USER')")
class MessageRestController extends DynamicDataRestfulController {

    // On ne peut qu'ajouter des messages, pas de mise à jour ou de suppression
    static allowedMethods = [save: "POST"]

    SpringSecurityService springSecurityService

    MessageRestController() {
        super(Message)
    }

    @Override
    @Transactional
    def save() {
        def json = request.JSON

        // Création du message
        Message message = new Message(
                description: json.description,
                recipient: User.get(json.recipientId),
                creator: springSecurityService.currentUser
        )

        if (message.validate()) {
            message.save()
            respond message, [status: HttpStatus.CREATED]
        } else {
            respond message, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }
}
