package controller.decks;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.tacs.TacsApplication;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.repositories.DecksRepository;

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
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Mockito.when(repository.find("1"))
                .thenReturn(Optional.of(new Deck(cardIds, "Arena")));
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>(Arrays.asList("1","2","3")));
        deckModelRequest.setName("Arena");
        doRequestWithBody(
                "PUT",
                "/api/decks/1",
                mapper.writeValueAsString(deckModelRequest),
                202);
        String json = mapper.writeValueAsString(deckModelRequest);
        assertRequestWithBody("PUT", "/api/decks/1", json, 202);
    }
}
