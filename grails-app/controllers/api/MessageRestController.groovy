package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.Message

@Secured("hasRole('ROLE_USER')")
class MessageRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    MessageRestController(){
        super(Message)
    }
}
