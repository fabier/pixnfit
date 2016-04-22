package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.Language
import pixnfit.StaticDataRestfulController

@Secured("permitAll")
class LanguageRestController extends StaticDataRestfulController {
    LanguageRestController() {
        super(Language, true)
    }
}
