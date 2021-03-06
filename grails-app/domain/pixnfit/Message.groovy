package pixnfit

/**
 * Message content is stored in "description" field, from BaseEntity
 * Message sender is "creator" from class BaseEntity
 */
class Message extends BaseEntity {

    /**
     * Message recipient
     */
    User recipient

    static belongsTo = [recipient: User, creator: User]

    static constraints = {
        name nullable: true
    }
}
