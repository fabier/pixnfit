package api

import org.springframework.security.access.annotation.Secured
import pixnfit.PostType
import pixnfit.StaticDataRestfulController

@Secured("hasRole('ROLE_USER')")
class PostTypeRestController extends StaticDataRestfulController {
    static responseFormats = ['json', 'xml']

    PostTypeRestController() {
        super(PostType)
    }
}
