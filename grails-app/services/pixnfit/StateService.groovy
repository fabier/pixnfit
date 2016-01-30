package pixnfit

import grails.transaction.Transactional

@Transactional
class StateService {

    State active() {
        State.findByName("active")
    }

    State inactive() {
        State.findByName("inactive")
    }

    State deleted() {
        State.findByName("deleted")
    }
}
