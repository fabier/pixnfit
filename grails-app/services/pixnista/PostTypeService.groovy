package pixnista

import grails.transaction.Transactional

@Transactional
class PostTypeService {

    PostType help() {
        PostType.findByName("help")
    }

    PostType dressing() {
        PostType.findByName("dressing")
    }
}
