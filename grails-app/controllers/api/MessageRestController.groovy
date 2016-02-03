package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.Message

@Secured("hasRole('ROLE_USER')")
class MessageRestController extends DynamicDataRestfulController {

    // On ne peut qu'ajouter des messages, pas de mise à jour ou de suppression
    static allowedMethods = [save: "POST"]

    MessageRestController() {
        super(Message)
    }
}
