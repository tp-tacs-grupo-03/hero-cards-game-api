package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;


final class DecksDeleteControllerTest extends RequestTestCase {
    @Test
    void delete_deck() throws Exception{
        assertRequest("DELETE", "/api/decks/1", 200);
    }

}