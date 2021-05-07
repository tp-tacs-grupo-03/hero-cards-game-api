package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import utn.tacs.dto.deck.DeckModelRequest;

import java.util.ArrayList;

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
