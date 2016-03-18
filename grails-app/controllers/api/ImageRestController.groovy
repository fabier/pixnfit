package api

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.multipart.commons.CommonsMultipartFile
import pixnfit.*

@Secured("hasRole('ROLE_USER')")
class ImageRestController extends DynamicDataRestfulController {

    SpringSecurityService springSecurityService
    ImageTypeService imageTypeService

    ImageRestController() {
        super(Image)
    }

    @Override
    @Transactional
    def save() {
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

            if (image.validate()) {
                image.save(flush: true)
                respond image, [status: HttpStatus.CREATED]
            } else {
                respond image, [status: HttpStatus.UNPROCESSABLE_ENTITY]
            }
        } else {
            respond "Uploaded file must be identified as \'data\'", [status: HttpStatus.BAD_REQUEST]
        }
    }

    @Override
    @Transactional
    def update() {
        def json = request.JSON
        Image image = Post.get(params.id)
        bindData(image, json, [include: ['name', 'description']])
        image.save()
        respond image
    }

    def data() {
        Image image = Image.get(params.imageRestId)
        respond image.imageData
    }
}
