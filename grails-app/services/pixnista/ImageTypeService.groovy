package pixnista

import grails.transaction.Transactional

@Transactional
class ImageTypeService {

    ImageType jpeg() {
        ImageType.findByName("JPEG")
    }

    ImageType png() {
        ImageType.findByName("PNG")
    }

    ImageType gif() {
        ImageType.findByName("GIF")
    }
}
