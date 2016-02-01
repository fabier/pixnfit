package api

import org.springframework.security.access.annotation.Secured
import pixnfit.FileExtension
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class FileExtensionRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    FileExtensionRestController() {
        super(FileExtension)
    }
}
