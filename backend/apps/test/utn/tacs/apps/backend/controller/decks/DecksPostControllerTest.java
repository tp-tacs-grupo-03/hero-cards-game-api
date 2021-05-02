package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import utn.tacs.model.requestModel.DeckModelRequest;

import java.util.ArrayList;

final class DecksPostControllerTest extends RequestTestCase {

    @Test
    void new_deck() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setNombre("Arena");
        assertRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
    }

}