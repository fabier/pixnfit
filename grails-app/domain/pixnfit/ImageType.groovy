package pixnfit

class ImageType extends BaseEntity {

    /**
     * Default mime type for this ImageType
     */
    Mimetype defaultMimeType

    /**
     * Default file extension for this ImageType
     */
    FileExtension defaultFileExtension

    /**
     * Format name as known by ImageIO (Java)
     */
    String javaFormatName

    /**
     * Valid file extensions for this ImageType
     */
    Set<FileExtension> fileExtensions

    /**
     * Valid mimetypes for this ImageType
     */
    Set<Mimetype> mimetypes

    static hasMany = [
            mimetypes     : Mimetype,
            fileExtensions: FileExtension
    ]

    static constraints = {
        name blank: false, unique: true
        defaultMimeType nullable: true
        defaultFileExtension nullable: true
    }

    /**
     * Images with this ImageType
     */
    List<ImageData> getImageDatas() {
        ImageData.findAllByImageType(this)
    }
    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
