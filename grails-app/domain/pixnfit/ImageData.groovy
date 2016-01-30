package pixnfit

import org.apache.commons.codec.digest.DigestUtils

/**
 * One-to-One relationship with Image, containing only the image's data (plus a md5 hash)
 */
class ImageData extends BaseEntity {

    /**
     * Raw data
     */
    byte[] data

    /**
     * MD5 Hash for the data
     * Always 32 characters long
     */
    String md5

    /**
     * The image using this data
     */
    Image image

    static belongsTo = [image: Image]

    static constraints = {
        md5 blank: false, size: 32..32
    }

    def beforeValidate() {
        if (md5 == null || isDirty("data")) {
            updateMD5()
        }
    }

    def beforeInsert() {
        updateMD5()
    }

    def beforeUpdate() {
        if (isDirty("data")) {
            updateMD5()
        }
    }

    def updateMD5() {
        // Update MD5 Hash
        md5 = DigestUtils.md5Hex(data)
    }
}
