package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.FashionStyle
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class FashionStyleRestController extends StaticDataRestfulController {
    FashionStyleRestController() {
        super(FashionStyle, true)
    }
}
