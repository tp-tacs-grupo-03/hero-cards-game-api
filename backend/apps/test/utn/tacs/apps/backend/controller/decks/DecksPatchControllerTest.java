package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;
import utn.tacs.apps.backend.controller.RequestTestCase;
import utn.tacs.model.requestModel.DeckModelRequest;
import utn.tacs.model.responseModel.DeckModelResponse;

import java.util.ArrayList;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksPatchControllerTest extends RequestTestCase {
    @Test
    void modify_deck_specific_deck() throws Exception {
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
