package api

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegistrationCode
import groovy.text.SimpleTemplateEngine
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.*

/**
 * On n'a que le droit que de voir la fiche pour un utilisateur donné,
 * ou de mettre à jour la fiche d'un utilisateur,
 * à condition d'être loggué en tant que cet utilisateur
 */
@Secured("hasRole('ROLE_USER')")
class UserRestController extends DynamicDataRestfulController {

    SpringSecurityService springSecurityService
    def springSecurityUiService
    def saltSource
    def mailService

    UserRestController() {
        super(User)
    }

    def index(Integer max) {
        // Interdit de lister les utilisateurs depuis le webservice
        render status: HttpStatus.FORBIDDEN
    }

    @Secured("permitAll")
    def save(CreateUserCommand command) {
        bindData(command, request.JSON, [include: ['username', 'email', 'password']])

        if (!command.validate()) {
            respond((Object) [errors: command.errors], [status: HttpStatus.BAD_REQUEST])
        } else {

            def userClass = lookupUserClass()
            String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
            def user = userClass.findWhere((usernameFieldName): command."$usernameFieldName")
            if (user) {
                if (user.accountLocked) {
                    // On va renvoyer le mail de validation automatiquement
                } else {
                    // Le compte existe déjà et il est actif
                    respond user
                    return
                }
            } else {
                // Création du compte utilisateur
                user = lookupUserClass().newInstance(email: command.email, username: command.username,
                        accountLocked: true, enabled: true)
            }

            String salt = saltSource instanceof NullSaltSource ? null : command."$usernameFieldName"
            RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
            if (registrationCode == null || registrationCode.hasErrors()) {
                respond((Object) [error: message(code: 'spring.security.ui.register.miscError')])
            } else {
                // Préparation et envoi du mail
                try {
                    // Envoi du mail à destination de l'utilisateur
                    String url = generateLink('verifyRegistration', [t: registrationCode.token])
                    def conf = SpringSecurityUtils.securityConfig
                    def emailFrom = conf.ui.register.emailFrom // noreply
                    def emailTo = command.email
                    def emailSubject = conf.ui.register.emailSubject // Création de compte

                    def body = conf.ui.register.emailBody
                    if (body.contains('$')) {
                        body = evaluate(body, [user: user, url: url])
                    }
                    mailService.sendMail {
                        to emailTo
                        from emailFrom
                        subject emailSubject
                        html body.toString()
                    }

                    // Email à destination interne : on informe de la création d'un compte utilisateur
                    emailTo = conf.ui.register.emailTo
                    def emailBodyToInternalEmailAccount = conf.ui.register.emailBodyToInternalEmailAccount
                    if (emailBodyToInternalEmailAccount.contains('$')) {
                        emailBodyToInternalEmailAccount = evaluate(emailBodyToInternalEmailAccount, [user: user])
                    }
                    mailService.sendMail {
                        to emailTo
                        from emailFrom
                        subject emailSubject
                        html emailBodyToInternalEmailAccount.toString()
                    }
                } catch (Exception e) {
                    // Le mail n'est pas parti, on affiche une erreur
//                flash.error = message(code: 'registerCommand.email.errorDuringSend')
//                flash.chainedParams = params
//                redirect action: 'index'
                    respond((Object) [error: message(code: 'registerCommand.email.errorDuringSend')])
                }
            }

            if (user.validate()) {
                user.save(flush: true)
                respond user, [status: HttpStatus.CREATED]
            } else {
                respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
            }
        }
    }

    def update() {
        User user = (User) springSecurityService.currentUser
        def json = request.JSON
        bindData(user, json, [include: ['username', 'description', "birthdate", "height", "weight"]])
        foreignKeyBindDataIfNotNull(user, json, [bodyType: BodyType, gender: Gender, country: Country, language: Language])
        if (user.validate()) {
            user.save()
            respond user
        } else {
            respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
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
        user.save(flush: true)
        respond user.getFollowersAsUserSet().toArray()
    }

    def unfollow() {
        User user = User.get(params.userRestId)
        user.removeFromFollowers(springSecurityService.currentUser as User)
        user.save(flush: true)
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
        user.save(flush: true)
        respond user.getBlacklistedUsersAsUserSet().toArray()
    }

    def unblacklist() {
        User user = (User) springSecurityService.currentUser
        User userToUnblacklist = User.get(params.userRestId)
        user.removeFromBlacklistedUsers(userToUnblacklist, true)
        user.save(flush: true)
        respond user.getBlacklistedUsersAsUserSet().toArray()
    }

    protected String lookupUserClassName() {
        SpringSecurityUtils.securityConfig.userLookup.userDomainClassName
    }

    protected Class<?> lookupUserClass() {
        grailsApplication.getDomainClass(lookupUserClassName()).clazz
    }

    protected String generateLink(String action, linkParams) {
        createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
                controller: 'register', action: action,
                params: linkParams)
    }

    protected String evaluate(s, binding) {
        new SimpleTemplateEngine().createTemplate(s).make(binding)
    }
}

class CreateUserCommand {
    String username
    String email
    String password

    static constraints = {
        username blank: false
        email blank: false, email: true
        password blank: false, validator: RegisterController.passwordValidator
    }
}