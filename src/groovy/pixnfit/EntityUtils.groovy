package pixnfit

/**
 * Created by fabier on 15/02/16.
 */
class EntityUtils {

    static Closure dateCreatedComparatorAsc = { a, b ->
        if (a?.dateCreated == null) {
            -1 // a va à la fin (a > b)
        } else if (b?.dateCreated == null) {
            1 // b va à la fin (b > a)
        } else {
            // sens croissant
            a.dateCreated.compareTo(b.dateCreated)
        }
    }

    static Closure dateCreatedComparatorDesc = { a, b ->
        if (a?.dateCreated == null) {
            -1 // a va à la fin (a > b)
        } else if (b?.dateCreated == null) {
            1 // b va à la fin (b > a)
        } else {
            // sens décroissant
            b.dateCreated.compareTo(a.dateCreated)
        }
    }

    static Closure lastUpdatedComparatorAsc = { a, b ->
        if (a?.lastUpdated == null) {
            -1 // a va à la fin (a > b)
        } else if (b?.lastUpdated == null) {
            1 // b va à la fin (b > a)
        } else {
            // sens croissant
            a.lastUpdated.compareTo(b.lastUpdated)
        }
    }

    static Closure lastUpdatedComparatorDesc = { a, b ->
        if (a?.lastUpdated == null) {
            -1 // a va à la fin (a > b)
        } else if (b?.lastUpdated == null) {
            1 // b va à la fin (b > a)
        } else {
            // sens décroissant
            b.lastUpdated.compareTo(a.lastUpdated)
        }
    }
}
