package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.dto.deck.DeckModelResponse;

import java.util.ArrayList;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksGetControllerTest extends RequestTestCase {
    @Test
    void getAllDecksWithNoDecks() throws Exception{
        assertRequest("GET","/api/decks", 200);
    }

    @Test
    void getAllDecks() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setName("Arena");
        doRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
        assertRequest("GET","/api/decks", 200);
    }

    @Test
    void getSpecificId() throws Exception{
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setName("Arena");
        String result = doRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
        DeckModelResponse deckModelResponse = mapper.readValue(result, DeckModelResponse.class);
        assertRequest("GET", "/api/decks/" + deckModelResponse.getId(), 200);
    }

}
