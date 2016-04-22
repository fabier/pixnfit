package api.common

import org.springframework.security.access.annotation.Secured
import pixnfit.PostType
import pixnfit.StaticDataRestfulController

@Secured("permitAll")
class PostTypeRestController extends StaticDataRestfulController {
    PostTypeRestController() {
        super(PostType, true)
    }
}
