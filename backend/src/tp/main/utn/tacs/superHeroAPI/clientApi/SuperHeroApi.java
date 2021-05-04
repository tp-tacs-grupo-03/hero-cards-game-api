package utn.tacs.superHeroAPI.clientApi;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.superHeroAPI.clientApi.model.Character;
import utn.tacs.superHeroAPI.clientApi.model.Image;
import utn.tacs.superHeroAPI.clientApi.model.Powerstats;

import java.util.Optional;

@Service
public class SuperHeroApi extends ApiClient {

        public ResponseEntity<Character> getCharacter(String id) {
            return run("/" + id, Character.class);
        }

        public ResponseEntity<Image> getImage(String id) {
            return run("/" + id + "/image", Image.class);
        }

        public Optional<ResponseEntity<Powerstats>> getPowerstats(String id) {
            return Optional.ofNullable(run("/" + id + "/powerstats", Powerstats.class));
        }

}
