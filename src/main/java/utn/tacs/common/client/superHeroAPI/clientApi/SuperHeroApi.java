package utn.tacs.common.client.superHeroAPI.clientApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;

import java.util.Optional;

@Service
public class SuperHeroApi extends ApiClient {

        private final static Logger log = LoggerFactory.getLogger(SuperHeroApi.class);

        @Cacheable(value = "characterCache",key = "#id")
        public ResponseEntity<Character> getCharacter(String id) {
            ResponseEntity<Character> result = run("/" + id, Character.class);
            log.info("character of {} is {}", id, result);
            return result;
        }

        public ResponseEntity<Image> getImage(String id) {
            return run("/" + id + "/image", Image.class);
        }

        public Optional<ResponseEntity<Powerstats>> getPowerstats(String id) {
            return Optional.ofNullable(run("/" + id + "/powerstats", Powerstats.class));
        }

}
