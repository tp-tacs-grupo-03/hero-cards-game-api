package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;
import utn.tacs.apps.backend.controller.RequestTestCase;
import utn.tacs.model.requestModel.DeckModelRequest;
import utn.tacs.model.responseModel.DeckModelResponse;

import java.util.ArrayList;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksGetControllerTest extends RequestTestCase {
    @Test
    void get_all_decks_with_no_decks() throws Exception{
        assertRequest("GET","/api/decks", 200);
    }

    @Test
    void get_all_decks() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setNombre("Arena");
        doRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
        assertRequest("GET","/api/decks", 200);
    }

    @Test
    void get_specific_id() throws Exception{
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>());
        deckModelRequest.setNombre("Arena");
        String result = doRequestWithBody(
                "POST",
                "/api/decks",
                mapper.writeValueAsString(deckModelRequest),
                200);
        DeckModelResponse deckModelResponse = mapper.readValue(result, DeckModelResponse.class);
        assertRequest("GET", "/api/decks/" + deckModelResponse.getId(), 200);
    }

}