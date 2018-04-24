package http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Response extends ResponseEntity<String> {

    public Response(String body, HttpStatus status) {
        super(body, getCommonHeaders(), status);
    }

    public static HttpHeaders getCommonHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        responseHeaders.add("X-XSS-Protection", "0");
        responseHeaders.set("P3P", "CP=\"IDC DSP COR ADM DEVi TAIi PSA` PSD IVAi IVDi CONi HIS OUR IND CNT\"");
        responseHeaders.set("Cache-Control", "no-cache, no-store, must-revalidate");
        responseHeaders.set("Pragma", "no-cache");
        responseHeaders.set("Expires", "0");
        return responseHeaders;
    }

}
