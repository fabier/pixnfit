package starter

import org.springframework.security.access.annotation.Secured

@Secured(['permitAll'])
class PublicController {

    static defaultAction = "index"

    def index() {
        render view: "index"
    }
}
