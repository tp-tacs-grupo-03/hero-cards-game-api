package utn.tacs.common.client.superHeroAPI.clientApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.ApiClient;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
@Scope("singleton")
public class SuperHeroApi extends ApiClient implements Serializable {

    private String token;

    public SuperHeroApi() { }

    public SuperHeroApi setToken(String token) {
        this.token = token;
        return this;
    }

    public String getAccessToken() {
        return token != null ? token: "3848385738531679";
    }

    public String getBaseURL() {
        return "https://superheroapi.com/api/access_token";
    }

    public String getErrorMsg() {
        return "SUPER HERO API HAS NO TOKEN in super_hero_api_key variable environment. Please check README file.";
    }

    private final static Logger log = LoggerFactory.getLogger(SuperHeroApi.class);

    @Cacheable(value = "characterCache",key = "#id")
    public Optional<Character> getCharacter(String id) throws URISyntaxException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final ResponseEntity<Character> result = run(url("/"+ id) , Character.class, headers);
        log.info("character of {} is {}", id, result);
        return Optional.ofNullable(result.getBody());
    }

    public ResponseEntity<Image> getImage(String id) throws URISyntaxException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return run(url("/" + id + "/image"), Image.class, headers);
    }

    @Cacheable(value = "powerStatsCache",key = "#id")
    public Optional<Powerstats> getPowerstats(String id) throws URISyntaxException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final ResponseEntity<Powerstats> result = run(url("/" + id + "/powerstats"), Powerstats.class, headers);
        return Optional.ofNullable(result.getBody());
    }

    private String url(String path) {
        final String t = token == null ? getAccessToken() : token;
        if (t  == null ){
            log.error(getErrorMsg());
            throw new RuntimeException(getErrorMsg());
        }
        return getBaseURL().replace("access_token", getAccessToken()) + "/" + path;
    }

}
