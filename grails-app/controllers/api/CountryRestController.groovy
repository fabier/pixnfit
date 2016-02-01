package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Country
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class CountryRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    CountryRestController() {
        super(Country)
    }
}
