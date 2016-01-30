package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.Country

@Secured("hasRole('ROLE_USER')")
class CountryRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    CountryRestController() {
        super(Country)
    }
}
