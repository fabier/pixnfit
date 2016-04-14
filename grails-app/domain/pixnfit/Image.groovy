package pixnfit

class Image extends BaseEntity {

    /**
     * Posts where this image appears (at least once)
     */
    Set<Post> posts

    /**
     * Master image data
     * Data, externalized to avoid a "big" table, and easy way to do "lazy loading".
     */
    ImageData imageData

    static belongsTo = [Post]

    static hasMany = [
            // All posts using this image
            posts          : Post,
            // All thumbnails and differents formats are in this set
            imageDataCaches: ImageDataCache
    ]

    static constraints = {
        imageData nullable: true // unique: true
    }
}
