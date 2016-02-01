package api

import org.springframework.security.access.annotation.Secured
import pixnfit.FileExtension
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class GenderRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    GenderRestController() {
        super(FileExtension)
    }
}
