package pixnfit

import org.apache.commons.lang.builder.HashCodeBuilder

class ImageDataCache extends BaseDomain {

    /**
     * Image
     */
    Image image

    /**
     * Cached image data
     */
    ImageData imageData

    static belongsTo = [image: Image, imageData: ImageData]

    static hasOne = [image: Image, imageData: ImageData]

    static constraints = {
    }

    static mapping = {
        id composite: ['image', 'imageData']
    }

    boolean equals(other) {
        if (!(other instanceof ImageDataCache)) {
            return false
        }

        other.image?.id == image?.id &&
                other.imageData?.id == imageData?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (image) builder.append(image.id)
        if (imageData) builder.append(imageData.id)
        builder.toHashCode()
    }
}
