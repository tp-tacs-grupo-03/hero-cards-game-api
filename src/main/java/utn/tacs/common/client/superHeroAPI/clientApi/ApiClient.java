package utn.tacs.common.client.superHeroAPI.clientApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

abstract class ApiClient {

    private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
    private static final String BASE_URL = "https://superheroapi.com/api/access_token";
    private String access_token = System.getenv("super_hero_api_key");

    public ApiClient() {}

    public ApiClient(String token) {
        this.access_token = token;
    }

    public <T> ResponseEntity<T> run(String path, Class<T> classReturn) {
        log.debug("ApiClient.run path: " + path);
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url(path), classReturn);
    }

    private String url(String path) {
        if (access_token == null ){
            final String msg = "SUPER HERO API HAS NO TOKEN in super_hero_api_key variable environment. Please check README file.";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        return BASE_URL.replace("access_token", access_token) + "/" + path;
    }

}
