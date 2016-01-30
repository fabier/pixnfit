package pixnfit

import grails.transaction.Transactional

@Transactional
class DownloadService {

    byte[] download(String url) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        byteArrayOutputStream << new URL(url).openConnection()?.inputStream;
        byteArrayOutputStream.toByteArray()
    }
}
