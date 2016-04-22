package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.Country
import pixnfit.StaticDataRestfulController

@Secured("permitAll")
class CountryRestController extends StaticDataRestfulController {
    CountryRestController() {
        super(Country, true)
    }
}
