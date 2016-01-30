package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.Visibility

@Secured("hasRole('ROLE_USER')")
class VisibilityRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    VisibilityRestController(){
        super(Visibility)
    }
}
