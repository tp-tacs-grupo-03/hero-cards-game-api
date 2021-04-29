package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksDeleteControllerTest extends RequestTestCase {
    @Test
    void delete_deck() throws Exception{
        assertRequest("DELETE", "/api/decks/1", 200);
    }

}