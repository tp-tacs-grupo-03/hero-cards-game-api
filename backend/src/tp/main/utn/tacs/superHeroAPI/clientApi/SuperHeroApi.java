package utn.tacs.superHeroAPI.clientApi;

import org.springframework.http.ResponseEntity;
import utn.tacs.superHeroAPI.clientApi.model.Character;
import utn.tacs.superHeroAPI.clientApi.model.Image;
import utn.tacs.superHeroAPI.clientApi.model.Powerstats;


public class SuperHeroApi extends ApiClient {

        public ResponseEntity<Character> getCharacter(String id) {
            return run("/" + id, Character.class);
        }

        public ResponseEntity<Image> getImage(String id) {
            return run("/" + id + "/image", Image.class);
        }

        public ResponseEntity<Powerstats> getPowerstats(String id) {
            return run("/" + id + "/powerstats", Powerstats.class);
        }

}
