package pixnista

class ImageType extends BaseEntity {

    /**
     * Default file extension for this ImageType
     */
    FileExtension defaultFileExtension

    /**
     * Default Mimetype for this ImageType
     */
    Mimetype defaultMimeType

    static hasMany = [
            /**
             * Valid FileExtensions for this ImageType
             */
            fileExtensions: FileExtension,
            /**
             * Valid MimeTypes for this ImageType
             */
            mimetypes: Mimetype
    ]

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Images with this ImageType
     */
    List<Image> getImages() {
        Image.findAllByImageType(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
