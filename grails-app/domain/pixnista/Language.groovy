package pixnista

class Language extends BaseEntity {
    /**
     * Language native name
     * Ex : Francais, English, Deutsch
     */
    String nativeName

    /**
     * Code ISO 6391 on 2 characters
     * Ex : fr, en, de, nl
     */
    String isoCode6391

    /**
     * Users using this language
     */
    static hasMany = [users: User]

    static constraints = {
        name blank: false, unique: true
        nativeName blank: false
        isoCode6391 blank: false, unique: true, size: 2
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
