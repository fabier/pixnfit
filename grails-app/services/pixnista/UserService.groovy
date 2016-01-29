package pixnista

import grails.transaction.Transactional

@Transactional
class UserService {

    def grailsApplication

    User admin() {
        if (grailsApplication.config.admin?.email) {
            User.findByEmail(grailsApplication.config.admin.email)
        } else {
            null
        }
    }
}
