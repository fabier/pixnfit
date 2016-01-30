package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.State

@Secured("hasRole('ROLE_USER')")
class StateRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    StateRestController(){
        super(State)
    }
}
