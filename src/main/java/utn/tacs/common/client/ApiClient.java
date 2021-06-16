package utn.tacs.common.client;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class ApiClient {

    private static final Logger log = LoggerFactory.getLogger(ApiClient.class);

    public ApiClient() {}

    public <T> ResponseEntity<T> run(String path, Class<T> classReturn, HttpHeaders headers) throws URISyntaxException {
        log.debug("ApiClient.run path: " + path);
        path = path.replaceFirst("\\|","%7C");
        final URI uri = new URI(path);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<T> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, classReturn);
    }

    public <T> ResponseEntity<T> runWithBody(String path, Class<T> classReturn, JSONObject body) {
        log.debug("ApiClient.run with body path: " + path);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);
        return restTemplate.postForEntity(path, request, classReturn);
    }

}
