package utn.tacs.apps.backend.controller.cards;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;


final class CardsTest extends RequestTestCase {
    @Test
    void get_all_cards() throws Exception{
        assertResponse("/api/cards", 200, "");
    }
}