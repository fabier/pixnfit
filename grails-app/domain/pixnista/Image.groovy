package pixnista

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
     * Data, externalized to avoid a "big" table, and easy way to do "lazy loading".
     */
    ImageData imageData

    /**
     * ImageType, PNG/JPEG/GIF
     */
    ImageType imageType

    static hasOne = [imageData: ImageData]

    static constraints = {
        filename blank: false
        width nullable: false, validator: { val, obj -> val >= 0 }
        height nullable: false, validator: { val, obj -> val >= 0 }
    }

    /**
     * Posts where this image appears (at least once)
     */
    List<Post> getPosts() {
        Post.findAll("from Post where ? in elements(images)", [this])
    }
}
