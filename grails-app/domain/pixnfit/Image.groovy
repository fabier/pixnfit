package pixnfit

class Image extends BaseEntity {

    /**
     * Posts where this image appears (at least once)
     */
    Set<Post> posts

    /**
     * Data, externalized to avoid a "big" table, and easy way to do "lazy loading".
     */
    static hasOne = [imageData: ImageData]

    static belongsTo = [Post]

    static hasMany = [
            posts          : Post,
            imageDataCaches: ImageDataCache
    ]

    static constraints = {
        imageData unique: true
    }
}
