package pixnfit

import grails.transaction.Transactional

@Transactional
class UserService {

    def grailsApplication

    User admin() {
        if (grailsApplication.config.admin?.email) {
            User.findByEmail(grailsApplication.config.admin.email)
        } else {
            null
        }
    }

    /**
     * Liste les utilisateurs qui suivent l'utilisateur passé en paramètre
     * @param user
     * @return
     */
    List<User> getFollowers(User user) {
        User.executeQuery("SELECT uf.followingUser FROM UserFollow uf WHERE uf.followedUser = :user", [user: user])
    }

    /**
     * Liste les utilisateurs qui sont suivis par le user passé en paramètre
     * @param user
     * @return
     */
    List<User> getUsersFollowedBy(User user) {
        User.executeQuery("SELECT uf.followedUser FROM UserFollow uf WHERE uf.followingUser = :user", [user: user])
    }

    /**
     * Retourne la liste des utilisateurs qui ont blacklisté l'utilisateur passé en paramètre
     * @param user
     * @return
     */
    List<User> getBlacklisters(User user) {
        User.executeQuery("SELECT ub.blacklistingUser FROM UserBlacklist ub WHERE ub.blacklistedUser = :user", [user: user])
    }

    /**
     * Retourne la liste des utilisateurs blacklistés par l'utilisateur passé en paramètre
     * @param user
     * @return
     */
    List<User> getUsersBlacklistedBy(User user) {
        User.executeQuery("SELECT ub.blacklistedUser FROM UserBlacklist WHERE ub.blacklistingUser = :user", [user: user])
    }
}
