package pixnista

class FileExtension extends BaseEntity {

    /**
     * File extension, without prefixing dot
     * Ex : "jpg", "jpeg", "gif"
     */
    String extension

    static constraints = {
        name blank: false, unique: true
        extension blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
