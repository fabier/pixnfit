package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Language
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class LanguageRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    LanguageRestController() {
        super(Language)
    }
}
