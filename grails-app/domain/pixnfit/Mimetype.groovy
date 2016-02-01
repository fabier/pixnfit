package pixnfit

class Mimetype extends BaseEntity {

    /**
     * Mimetype
     * Ex : image/png, image/jpeg, image/gif
     */
    String mimetype

    /**
     * The associated ImageType
     */
    ImageType imageType

    static belongsTo = [imageType: ImageType]

    static hasOne = [imageType: ImageType]

    static constraints = {
        name blank: false, unique: true
        mimetype blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
