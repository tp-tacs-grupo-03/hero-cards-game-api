package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import utn.tacs.TacsApplication;
import utn.tacs.common.security.Authenticator;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.sorting.Sort;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TacsApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class DeckControllerTest extends RequestTestCase {

    @MockBean
    DecksRepository repository;

    @Test
    void modifyDecksUsingPUTMethod() throws Exception {
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");


        when(repository.find("1"))
                .thenReturn(Optional.of(arena));

        DeckModelRequest deckModelRequest = new DeckModelRequest();
        deckModelRequest.setCards(new ArrayList<>(Arrays.asList("1","2","3")));
        deckModelRequest.setName("Arena");

        String json = mapper.writeValueAsString(deckModelRequest);
        assertRequestWithBody("PUT", "/api/decks/1", json, 202);
    }

    @Test
    void newDeck() throws Exception {
        DeckModelRequest deckModelRequest = new DeckModelRequest();
        List<String> cardIds = new ArrayList<>(Arrays.asList("1", "2"));
        deckModelRequest.setCards(cardIds);
        deckModelRequest.setName("arena");
        String json = mapper.writeValueAsString(deckModelRequest);

        assertRequestWithBody("POST", "/api/decks",json, 200);
    }

    @Test
    void getDeck() throws Exception {
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");
        when(repository.find("1")).thenReturn(Optional.of(arena));
        assertRequest("GET", "/api/decks/1", 200);
    }

    @Test
    void getAllDecks() throws Exception {
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");
        when(repository.findAll(PageRequest.of(0, 100), new Sort("id", "asc"))).thenReturn(new ArrayList<>(Collections.singletonList(arena)));
        assertRequest("GET", "/api/decks", 200);
    }


    @Test
    void deleteDeck() throws Exception {
        assertRequest("DELETE", "/api/decks/1", 200);
    }
}
