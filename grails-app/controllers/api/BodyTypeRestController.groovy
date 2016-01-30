package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.BodyType

@Secured("hasRole('ROLE_USER')")
class BodyTypeRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    BodyTypeRestController() {
        super(BodyType)
    }
}
