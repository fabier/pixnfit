package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.Language

@Secured("hasRole('ROLE_USER')")
class LanguageRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    LanguageRestController(){
        super(Language)
    }
}
