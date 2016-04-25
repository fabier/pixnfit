package util

import grails.transaction.Transactional
import org.apache.commons.lang3.StringUtils

import java.sql.Date as SQLDate
import java.text.ParseException
import java.text.SimpleDateFormat

@Transactional
class JsonFormatService {

    public Date parseDate(String sDate) {
        if (StringUtils.isBlank(sDate) || "null".equals(sDate)) {
            return null
        } else {
            // NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
            // things a bit. Before we go on we have to repair this.
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.ENGLISH)
                // this is zero time so we need to add that TZ indicator for
                if (sDate.endsWith("Z")) {
                    sDate = sDate.substring(0, sDate.length() - 1) + "GMT-00:00"
                } else {
                    int inset = 6
                    String s0 = sDate.substring(0, sDate.length() - inset)
                    String s1 = sDate.substring(sDate.length() - inset, sDate.length())
                    sDate = s0 + "GMT" + s1
                }
                return df.parse(sDate)
            } catch (ParseException pe) {
                log.error "parseDate: failed", pe
                return null
            }
        }
    }

    SQLDate parseSQLDate(String sDate) {
        Date date = parseDate(sDate)
        if (date == null) {
            return null;
        } else {
            return new SQLDate(date.getTime())
        }
    }
}
