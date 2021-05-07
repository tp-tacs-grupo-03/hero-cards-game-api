package controller.cards;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class CardsGetControllerTest extends RequestTestCase {
    @Test
    void find_card_by_id() throws Exception{
        assertRequest("GET","/api/cards/1", 200);
    }
}
