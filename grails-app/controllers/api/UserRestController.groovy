package api

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.User

/**
 * On n'a que le droit que de voir la fiche pour un utilisateur donné,
 * ou de mettre à jour la fiche d'un utilisateur,
 * à condition d'être loggué en tant que cet utilisateur
 */
@Secured("hasRole('ROLE_USER')")
class UserRestController extends DynamicDataRestfulController {

    SpringSecurityService springSecurityService

    UserRestController() {
        super(User)
    }

    def index(Integer max) {
        // Interdit de lister les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def save() {
        // Interdit de créer les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def update() {
        // Interdit de mettre à jour les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def delete() {
        // Interdit de supprimer les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    def incomingMessages() {
        User user = User.get(params.userRestId)
        respond user.incomingMessages.toArray()
    }

    def outgoingMessages() {
        User user = User.get(params.userRestId)
        respond user.outgoingMessages.toArray()
    }

    def posts() {
        User user = User.get(params.userRestId)
        respond user.posts.toArray()
    }

    def postComments() {
        User user = User.get(params.userRestId)
        respond user.postComments.toArray()
    }

    def postVotes() {
        User user = User.get(params.userRestId)
        respond user.postVotes.toArray()
    }

    def followers() {
        User user = User.get(params.userRestId)
        respond user.getFollowersAsUserSet().toArray()
    }

    def follow() {
        User user = User.get(params.userRestId)
        user.addToFollowers(springSecurityService.currentUser as User)
        user.save()
        respond user.getFollowersAsUserSet().toArray()
    }

    def unfollow() {
        User user = User.get(params.userRestId)
        user.removeFromFollowers(springSecurityService.currentUser as User)
        user.save()
        respond user.getFollowersAsUserSet().toArray()
    }

    def followedUsers() {
        User user = User.get(params.userRestId)
        respond user.getFollowedUsersAsUserSet().toArray()
    }

    def blacklistedUsers() {
        User user = User.get(params.userRestId)
        respond user.getBlacklistedUsersAsUserSet().toArray()
    }

    def blacklistedBy() {
        User user = User.get(params.userRestId)
        respond user.getBlacklistingUsersAsUserSet().toArray()
    }

    def blacklist() {
        User user = (User) springSecurityService.currentUser
        User userToBlacklist = User.get(params.userRestId)
        user.addToBlacklistedUsers(userToBlacklist)
        user.save()
        respond user.getBlacklistedUsersAsUserSet().toArray()
    }

    def unblacklist() {
        User user = (User) springSecurityService.currentUser
        User userToUnblacklist = User.get(params.userRestId)
        user.removeFromBlacklistedUsers(userToUnblacklist)
        user.save()
        respond user.getBlacklistedUsersAsUserSet().toArray()
    }
}