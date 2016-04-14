package util

import grails.transaction.Transactional

@Transactional
class TimeZoneService {

    /**
     * Synonym for utc()
     * @return UTC/GMT TimeZone
     */
    TimeZone gmt() {
        utc()
    }

    /**
     * UTC TimeZone
     * @return UTC/GMT TimeZone
     */
    TimeZone utc() {
        TimeZone.getTimeZone("UTC")
    }
}
