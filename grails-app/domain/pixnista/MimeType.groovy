package pixnista

class MimeType extends BaseEntity {

    /**
     * MimeType
     * Ex : image/png, image/jpeg, image/gif
     */
    String mimetype

    static constraints = {
        name blank: false, unique: true
        mimetype blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
