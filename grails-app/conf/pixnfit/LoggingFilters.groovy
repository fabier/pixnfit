package pixnfit

class LoggingFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                if (request.JSON) {
                    log.info "${request.getForwardURI()} -> $params, JSON:${request.JSON}"
                } else {
                    log.info "${request.getForwardURI()} -> $params"
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
