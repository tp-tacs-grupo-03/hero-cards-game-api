package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.dto.deck.DeckModelResponse;

import java.util.ArrayList;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksPatchControllerTest extends RequestTestCase {
    @Test
    void modifyDeckSpecificDeck() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setName("Arena");
        String result = doRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
        DeckModelResponse deckModelResponse = mapper.readValue(result, DeckModelResponse.class);
        assertRequestWithBody("PATCH", "/api/decks/" + deckModelResponse.getId(), "Nuevo", 200);
    }
}
