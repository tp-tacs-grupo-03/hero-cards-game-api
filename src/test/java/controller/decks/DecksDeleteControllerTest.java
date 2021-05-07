package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksDeleteControllerTest extends RequestTestCase {
    @Test
    void delete_deck() throws Exception{
        assertRequest("DELETE", "/api/decks/1", 200);
    }

}
