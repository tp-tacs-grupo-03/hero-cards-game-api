package utn.tacs.apps.backend.controller.cards;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class CardsGetControllerTest extends RequestTestCase {
    @Test
    void find_card_by_id() throws Exception{
        assertRequest("GET","/api/cards/1", 200);
    }
}