package pixnfit

class Country extends BaseEntity {

    /**
     * Language name in native language
     */
    String nativeName

    /**
     * ISO Code on 2 characters for this country
     */
    String isoCode31661

    static constraints = {
        name blank: false, unique: true
        nativeName blank: false, unique: true
        isoCode31661 blank: false, unique: true
    }

    /**
     * Gets users from this country
     */
    List<User> getUsers() {
        User.findAllByCountry(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
