package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksGetControllerTest extends RequestTestCase {
    @Test
    void get_all_decks() throws Exception{
        assertResponse("/api/decks", 200, "");
    }

    @Test
    void get_specific_id() throws Exception{
        assertRequest("GET", "/api/decks/1", 200);
    }

}