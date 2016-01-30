package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.FashionStyle

@Secured("hasRole('ROLE_USER')")
class FashionStyleRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    FashionStyleRestController(){
        super(FashionStyle)
    }
}
