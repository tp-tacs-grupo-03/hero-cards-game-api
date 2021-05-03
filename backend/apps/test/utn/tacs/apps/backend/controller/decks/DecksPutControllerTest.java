package utn.tacs.apps.backend.controller.decks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.tacs.apps.TacsApplication;
import utn.tacs.apps.backend.controller.RequestTestCase;
import utn.tacs.cards.domain.Card;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;
import utn.tacs.model.requestModel.DeckModelRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {TacsApplication.class})
final class DecksPutControllerTest extends RequestTestCase {

    @MockBean
    DecksRepository repository;

    @Test
    void modifyDecksUsingPUTMethod() throws Exception {
        List<Card> cards = new ArrayList<>(Arrays.asList(new Card("1"), new Card("2")));
        Mockito.when(repository.find("1"))
                .thenReturn(Optional.of(new Deck(cards, "Arena")));
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>(Arrays.asList("1","2","3")));
        deckModelRequest.setName("Arena");
        doRequestWithBody(
                "PUT",
                "/api/decks/1/cards",
                mapper.writeValueAsString(deckModelRequest),
                202);
        String json = mapper.writeValueAsString(deckModelRequest);
        assertRequestWithBody("PUT", "/api/decks/1/cards", json, 202);
    }
}
