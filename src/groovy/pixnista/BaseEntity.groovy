package pixnista

/**
 * Created by fabier on 25/04/15.
 */
abstract class BaseEntity extends BaseDomain {

    // Nom de l'entité
    String name

    // Description de l'entité
    String description

    // Créateur de cette entrée en base
    User creator

    static constraints = {
        description nullable: true
    }

    static mapping = {
        description type: 'text'
    }
}
