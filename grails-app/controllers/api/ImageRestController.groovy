package api

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
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
    Object save() {
        def json = request.JSON
        User user = springSecurityService.currentUser
        Image image = new Image(
                creator: user,
                imageType: imageTypeService.jpeg()
        )
        bindData(image, json, [include: ['name', 'description', 'filename', 'height', 'width']])
        foreignKeyBindDataIfNotNull(image, json, [imageType: ImageType])

        if (image.validate()) {
            image.save()
            respond image, [status: HttpStatus.CREATED]
        } else {
            respond image, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    @Override
    @Transactional
    def update() {
        def json = request.JSON
        Image image = Post.get(params.id)
        bindData(image, json, [include: ['name', 'description', 'filename', 'height', 'width']])
        foreignKeyBindDataIfNotNull(image, json, [imageType: ImageType])
        image.save()
        respond image
    }

    def data() {
        Image image = Image.get(params.imageRestId)
        respond image.imageData
    }
}
