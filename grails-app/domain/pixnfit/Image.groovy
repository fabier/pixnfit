package pixnfit

class Image extends BaseEntity {
    /**
     * Original filename
     * Ex : DSC_OOO1.JPG
     */
    String filename

    /**
     * Image Width
     */
    Integer width

    /**
     * Image Height
     */
    Integer height

    /**
     * ImageType, PNG/JPEG/GIF
     */
    ImageType imageType

    /**
     * Posts where this image appears (at least once)
     */
    Set<Post> posts

    /**
     * Data, externalized to avoid a "big" table, and easy way to do "lazy loading".
     */
    static hasOne = [imageData: ImageData]

    static belongsTo = [Post]

    static hasMany = [posts: Post]

    static constraints = {
        filename blank: false
        width nullable: false, validator: { val, obj -> val >= 0 }
        height nullable: false, validator: { val, obj -> val >= 0 }
        imageData unique: true
    }

    def beforeValidate() {
        if (imageData?.md5 == null || isDirty("imageData")) {
            imageData.updateMD5()
        }
    }

    def beforeInsert() {
        imageData.updateMD5()
    }

    def beforeUpdate() {
        if (isDirty("imageData")) {
            imageData.updateMD5()
        }
    }
}
