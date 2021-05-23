package utn.tacs.common.client.superHeroAPI.clientApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;

import java.io.Serializable;
import java.util.Optional;

@Service
@Scope("singleton")
public class SuperHeroApi extends ApiClient implements Serializable {

        public SuperHeroApi() {}

        public SuperHeroApi(String token) {
            super(token);
        }

        private final static Logger log = LoggerFactory.getLogger(SuperHeroApi.class);

        @Cacheable(value = "characterCache",key = "#id")
        public Optional<Character> getCharacter(String id) {
            final ResponseEntity<Character> result = run("/" + id, Character.class);
            log.info("character of {} is {}", id, result);
            return Optional.ofNullable(result.getBody());
        }

        public ResponseEntity<Image> getImage(String id) {
            return run("/" + id + "/image", Image.class);
        }

        @Cacheable(value = "powerStatsCache",key = "#id")
        public Optional<Powerstats> getPowerstats(String id) {
            final ResponseEntity<Powerstats> result = run("/" + id + "/powerstats", Powerstats.class);
            return Optional.ofNullable(result.getBody());
        }

}
