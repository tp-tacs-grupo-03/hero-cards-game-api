package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;
import utn.tacs.dto.deck.DeckModelRequest;

import java.util.ArrayList;
@SpringBootTest(classes = {TacsApplication.class})
final class DecksPostControllerTest extends RequestTestCase {

    @Test
    void new_deck() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setName("Arena");
        assertRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
    }

}
