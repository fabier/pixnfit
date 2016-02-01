package api

import org.springframework.security.access.annotation.Secured
import pixnfit.Country
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class FileExtensionRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    FileExtensionRestController() {
        super(Country)
    }
}
