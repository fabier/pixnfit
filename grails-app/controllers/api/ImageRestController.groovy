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
    Object save() {
        User user = springSecurityService.currentUser

        byte[] data = null
        String originalFilename = null
        if (params.data != null) {
            if (params.data instanceof CommonsMultipartFile) {
                CommonsMultipartFile file = params.data
                data = file.getBytes()
                originalFilename = file.originalFilename
            }
        }

        ImageData imageData = new ImageData(
                creator: user,
                name: originalFilename,
                filename: originalFilename,
                data: data
        )
        imageData.updateAutoCalculatedFields()

        Image image = new Image(
                creator: user,
                imageData: imageData,
                name: originalFilename
        )

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
        bindData(image, json, [include: ['name', 'description']])
        image.save()
        respond image
    }

    def data() {
        Image image = Image.get(params.imageRestId)
        respond image.imageData
    }
}
