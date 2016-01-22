import starter.Role
import starter.User
import starter.UserRole

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        // Création des rôles utilisateurs
        if (grailsApplication.config.role) {
            def adminRole = Role.findByAuthority(grailsApplication.config.role.admin) ?: new Role(authority: grailsApplication.config.role.admin).save(failOnError: true, flush: true)
            log.info("ADMIN Role : ${adminRole.authority}")

            def userRole = Role.findByAuthority(grailsApplication.config.role.user) ?: new Role(authority: grailsApplication.config.role.user).save(failOnError: true, flush: true)
            log.info("USER Role : ${userRole.authority}")

            if (grailsApplication.config.admin) {
                def adminUser = User.findByEmail(grailsApplication.config.admin.email) ?:
                        new User(
                                username: grailsApplication.config.admin.username,
                                password: grailsApplication.config.admin.password,
                                email: grailsApplication.config.admin.email,
                                enabled: true
                        ).save(failOnError: true, flush: true)

                log.info("Admin User : ${adminUser.username}")
                if (!UserRole.findByUserAndRole(adminUser, adminRole)) {
                    UserRole.create adminUser, adminRole, true
                }
                if (!UserRole.findByUserAndRole(adminUser, userRole)) {
                    UserRole.create adminUser, userRole, true
                }
            }
        }
    }

    def destroy = {
    }

    static def generatePassword() {

    }
}
