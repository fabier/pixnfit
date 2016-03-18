package api

import admin.PrivateBetaRegistrationEmail
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegistrationCode
import groovy.text.SimpleTemplateEngine
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.multipart.commons.CommonsMultipartFile
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

    /**
     * Cette méthode permet de réaliser la première partie de l'inscription de l'utilisateur :
     * Un utilisateur doit être ensuite finalisé (avec la méthode createProfile) pour que l'envoi de mail s'effectue
     */
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
                // Création du compte utilisateur (pas encore sauvegardé en base)
                user = lookupUserClass().newInstance(email: command.email, username: command.username,
                        accountLocked: true, enabled: true)
            }

            // Création du registrationCode
            String salt = saltSource instanceof NullSaltSource ? null : command."$usernameFieldName"
            RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)

            if (registrationCode == null || registrationCode.hasErrors()) {
                respond((Object) [error: message(code: 'spring.security.ui.register.miscError')])
            } else {
                // Création du code d'enregistrement OK
                if (user.validate()) {
                    user.save(flush: true)
                    respond user, [status: HttpStatus.CREATED]
                } else {
                    respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
                }
            }
        }
    }

    private boolean sendConfirmationEmail(User user) throws Exception {
        String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
        RegistrationCode registrationCode = RegistrationCode.findByUsername(user."$usernameFieldName")
        if (registrationCode != null) {
            // Préparation et envoi du mail
            // Envoi du mail à destination de l'utilisateur
            String url = generateLink('verifyRegistration', [t: registrationCode.token])
            def conf = SpringSecurityUtils.securityConfig
            def emailFrom = conf.ui.register.emailFrom // noreply
            def emailTo = user.email
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

            return true // Email has been successfully sent
        } else {
            return false // Email has not been sent
        }
    }

    @Secured("permitAll")
    def initUserImage() {
        User user = User.get(params.userRestId)
        if (user != null) {
            if (user.accountLocked) {
                // TODO : factoriser avec updateUserImage() et ImageRestController.save()
                byte[] data = null
                String originalFilename = null
                if (params.data != null) {
                    if (params.data instanceof CommonsMultipartFile) {
                        CommonsMultipartFile file = params.data
                        data = file.getBytes()
                        originalFilename = file.originalFilename
                    }
                }

                if (data != null && originalFilename != null) {
                    ImageData imageData = new ImageData(
                            name: originalFilename,
                            filename: originalFilename,
                            data: data
                    )
                    imageData.setCreator(user)
                    imageData.updateAutoCalculatedFields()

                    Image image = new Image(
                            imageData: imageData,
                            name: originalFilename
                    )
                    // On ne doit pas le mettre dans le constructeur, sinon user.image se met à jour avec cette valeur !!!
                    image.setCreator(user)

                    // On met à jour l'image de l'utilisateur
                    user.image = image

                    if (image.validate() && user.validate()) {
                        image.save()
                        user.save()
                        respond user
                    } else {
                        respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
                    }
                } else {
                    respond((Object) [error: "Uploaded file must be identified as \'data\'"], [status: HttpStatus.BAD_REQUEST])
                }
            } else {
                respond((Object) [error: "User account is not locked anymore. Please use 'POST /user/:id/image' to update user image"], [status: HttpStatus.BAD_REQUEST])
            }
        } else {
            respond((Object) [error: "No user with id : ${params.userRestId}"], [status: HttpStatus.BAD_REQUEST])
        }
    }

    @Secured("permitAll")
    def initUserProfile() {
        User user = User.get(params.userRestId)
        if (user != null) {
            if (user.accountLocked) {
                // On a le droit d'appeler cette méthode que si le compte utilisateur n'a pas encore été validé
                def json = request.JSON
                foreignKeyBindDataIfNotNull(user, json, [bodyType: BodyType, gender: Gender, country: Country, language: Language])
                bindData(command, json, [include: ['description', "birthdate", "height", "weight"]])
                if (user.validate()) {
                    user.save()
                    // Send email to finalize user account creation
                    try {
                        if (sendConfirmationEmail(user)) {
                            // On a bien mis à jour le profil utilisateur et on a envoyé l'email
                            respond user
                        } else {
                            log.warn "Impossible to confirmation email to user : ${user.email}"
                            respond((Object) [error: message(code: 'registerCommand.email.errorDuringSend')])
                        }
                    } catch (Exception e) {
                        // Le mail n'est pas parti, on affiche une erreur
                        log.warn "Impossible to confirmation email to user : ${user.email}", e
                        respond((Object) [error: message(code: 'registerCommand.email.errorDuringSend')])
                    }
                } else {
                    respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
                }
            } else {
                respond((Object) [error: "User account is not locked anymore. Please use 'PUT /user/:id' to update profile"], [status: HttpStatus.BAD_REQUEST])
            }
        } else {
            respond((Object) [error: "No user with id : ${params.userRestId}"], [status: HttpStatus.BAD_REQUEST])
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

    def updateUserImage() {
        User user = (User) springSecurityService.currentUser
        byte[] data = null
        String originalFilename = null
        if (params.data != null) {
            if (params.data instanceof CommonsMultipartFile) {
                CommonsMultipartFile file = params.data
                data = file.getBytes()
                originalFilename = file.originalFilename
            }
        }

        if (data != null && originalFilename != null) {
            ImageData imageData = new ImageData(
                    name: originalFilename,
                    filename: originalFilename,
                    data: data
            )
            imageData.setCreator(user)
            imageData.updateAutoCalculatedFields()

            Image image = new Image(
                    imageData: imageData,
                    name: originalFilename
            )
            // On ne doit pas le mettre dans le constructeur, sinon user.image se met à jour avec cette valeur !!!
            image.setCreator(user)

            // On met à jour l'image de l'utilisateur
            user.image = image

            if (image.validate() && user.validate()) {
                image.save()
                user.save()
                respond user
            } else {
                respond user, [status: HttpStatus.UNPROCESSABLE_ENTITY]
            }
        } else {
            respond((Object) [error: "Uploaded file must be identified as \'data\'"], [status: HttpStatus.BAD_REQUEST])
        }
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
        email blank: false, email: true, validator: { val, obj ->
            if (PrivateBetaRegistrationEmail.findByEmail(val) == null) {
                return 'user.email.privateBetaRegistrationEmail'
            }
        }
        password blank: false, validator: RegisterController.passwordValidator
    }
}
