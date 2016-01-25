package pixnista

class VoteReason extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }



    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
