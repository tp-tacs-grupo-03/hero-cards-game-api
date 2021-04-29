package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksPatchControllerTest extends RequestTestCase {
    @Test
    void modify_deck_specific_deck(){
        assertTrue(true); //@TODO Nico
    }
}