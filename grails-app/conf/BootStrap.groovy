import pixnfit.Role
import pixnfit.User
import pixnfit.UserRole

class BootStrap {

    def grailsApplication

    def bootstrapInitialDataService

    def init = { servletContext ->
        addMetaMethods()

        // Création des rôles utilisateurs
        if (grailsApplication.config.role) {
            def adminRole = Role.findByAuthority(grailsApplication.config.role.admin) ?: new Role(authority: grailsApplication.config.role.admin).save(failOnError: true, flush: true)
            log.info("ADMIN Role : ${adminRole.authority}")

            def userRole = Role.findByAuthority(grailsApplication.config.role.user) ?: new Role(authority: grailsApplication.config.role.user).save(failOnError: true, flush: true)
            log.info("USER Role : ${userRole.authority}")

            if (grailsApplication.config.admin
                    && grailsApplication.config.admin.username
                    && grailsApplication.config.admin.password
                    && grailsApplication.config.admin.email) {
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

                // On dit que le créateur des rôles ROLE_ADMIN et ROLE_USER est "ADMIN"
                adminRole.setCreator(adminUser)
                adminRole.save()

                userRole.setCreator(adminUser)
                userRole.save()
            }
        }

        bootstrapInitialDataService.initStaticData()
//        bootstrapInitialDataService.initRandomData(true)
    }

    def addMetaMethods() {
        grailsApplication.domainClasses.each { dc ->
            dc.metaClass.'static'.random << {
                delegate.withCriteria(uniqueResult: true) {
                    maxResults 1
                    sqlRestriction " 1=1 order by random()"
                }
            }
        }
    }

    def destroy = {
    }
}
