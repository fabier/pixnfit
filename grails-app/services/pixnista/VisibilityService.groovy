package pixnista

import grails.transaction.Transactional

@Transactional
class VisibilityService {

    Visibility 'public'() {
        Visibility.findByName("public")
    }

    Visibility followers() {
        Visibility.findByName("followers")
    }

    Visibility 'private'() {
        Visibility.findByName("private")
    }
}
