package api

import org.springframework.security.access.annotation.Secured
import pixnfit.FashionStyle
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class FashionStyleRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    FashionStyleRestController() {
        super(FashionStyle)
    }
}
