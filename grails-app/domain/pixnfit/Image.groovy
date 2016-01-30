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
     * Data, externalized to avoid a "big" table, and easy way to do "lazy loading".
     */
    static hasOne = [imageData: ImageData]

    static constraints = {
        filename blank: false
        width nullable: false, validator: { val, obj -> val >= 0 }
        height nullable: false, validator: { val, obj -> val >= 0 }
        imageData unique: true
    }

    /**
     * Posts where this image appears (at least once)
     */
    List<Post> getPosts() {
        Post.findAll("from Post where ? in elements(images)", [this])
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
