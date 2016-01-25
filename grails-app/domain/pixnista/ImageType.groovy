package pixnista

class ImageType extends BaseEntity {

    /**
     * Default file extension for this ImageType
     */
    FileExtension defaultFileExtension

    /**
     * Default MimeType for this ImageType
     */
    MimeType defaultMimeType

    static hasMany = [fileExtensions: FileExtension, mimeTypes: MimeType, images: Image]

    static constraints = {
        name blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
