package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import utn.tacs.common.security.Authenticator;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.deck.response.Attribute;
import utn.tacs.dto.deck.response.MatchModel;
import utn.tacs.dto.deck.response.MatchTypeEnum;
import utn.tacs.dto.match.MatchRequest;
import utn.tacs.dto.player.PlayerStatusEnum;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MatchControllerTest extends RequestTestCase{

    @MockBean
    UsersRepository usersRepository;
    @MockBean
    DecksRepository repository;
    @MockBean
    Authenticator authenticator;
    @MockBean
    MatchesRepository matchesRepository;



    @Test
    void postMatch() throws Exception {
        when(authenticator.getHost()).thenReturn("Test");
        when(usersRepository.find("Test")).thenReturn(new PlayerStats("Test"));
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        when(repository.find("1"))
                .thenReturn(Optional.of(arena));

        MatchModel matchModel = new MatchModel();
        matchModel.setDeckId("1");
        matchModel.setOpponentId("12");
        matchModel.setType(MatchTypeEnum.RANKED);
        String json = mapper.writeValueAsString(matchModel);


        assertRequestWithBody("POST", "/api/matches", json,200);
    }

    @Test
    void getAllMatches_nonMatchResponse() throws Exception {
        when(matchesRepository.findAllById(PageRequest.of(0,10), new Sort("name", "asc"), "")).thenReturn(new ArrayList<>());
        when(matchesRepository.getTotal("1")).thenReturn(0);

        assertRequest("GET", "/api/matches", 200);
    }

    @Test
    void getAllMatches_withMatches() throws Exception {
        when(authenticator.getHost()).thenReturn("Test");
        when(matchesRepository.getTotal("1")).thenReturn(0);

        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        List<Queue<CardId>> split = arena.split(2);
        final Map<String, Queue<CardId>> players = new HashMap<>();
        players.put("Test", split.get(0));
        players.put("z1234", split.get(1));

        Match match = new Match(players, "1", MatchTypeEnum.RANKED);
        List<Match> matches = new ArrayList<>();
        matches.add(match);
        when(matchesRepository.findAllById(PageRequest.of(0,10), new Sort("name", "asc"), "")).thenReturn(matches);

        assertRequest("GET", "/api/matches", 200);
    }

    @Test
    void getMatch_empty() throws Exception {
        when(matchesRepository.find("1")).thenReturn(Optional.empty());
        assertRequest("GET", "/api/matches/1", 204);
    }

    @Test
    void getMatch_withMatch() throws Exception {
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        List<Queue<CardId>> split = arena.split(2);
        final Map<String, Queue<CardId>> players = new HashMap<>();
        players.put("Test", split.get(0));
        players.put("z1234", split.get(1));

        Match match = new Match(players, "1", MatchTypeEnum.RANKED);

        when(matchesRepository.find("1")).thenReturn(Optional.of(match));
        assertRequest("GET", "/api/matches/1", 200);
    }


    @Test
    void update_bad() throws Exception {
        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setAttribute(Attribute.INTELLIGENCE);
        matchRequest.setStatus(PlayerStatusEnum.SURRENDERED);

        String json = mapper.writeValueAsString(matchRequest);

        assertRequestWithBody("PATCH", "/api/matches/1",json, 400);
    }

    @Test
    void update_surrender() throws Exception {
        when(authenticator.getHost()).thenReturn("Test");
        when(usersRepository.find("Test")).thenReturn(new PlayerStats("Test"));
        when(usersRepository.find("z1234")).thenReturn(new PlayerStats("z1234"));

        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setStatus(PlayerStatusEnum.SURRENDERED);
        String json = mapper.writeValueAsString(matchRequest);

        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        List<Queue<CardId>> split = arena.split(2);
        final Map<String, Queue<CardId>> players = new HashMap<>();
        players.put("Test", split.get(0));
        players.put("z1234", split.get(1));

        Match match = new Match(players, "1", MatchTypeEnum.RANKED);

        when(matchesRepository.find("1")).thenReturn(Optional.of(match));

        assertRequestWithBody("PATCH", "/api/matches/1", json, 200);
    }

    @Test
    void update_match() throws Exception {
        when(authenticator.getHost()).thenReturn("Test");
        when(usersRepository.find("Test")).thenReturn(new PlayerStats("Test"));
        when(usersRepository.find("z1234")).thenReturn(new PlayerStats("z1234"));

        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setAttribute(Attribute.INTELLIGENCE);
        String json = mapper.writeValueAsString(matchRequest);

        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        List<Queue<CardId>> split = arena.split(2);
        final Map<String, Queue<CardId>> players = new HashMap<>();
        players.put("Test", split.get(0));
        players.put("z1234", split.get(1));

        Match match = new Match(players, "1", MatchTypeEnum.RANKED);


        when(matchesRepository.find("1")).thenReturn(Optional.of(match));

        assertRequestWithBody("PATCH", "/api/matches/1", json, 200);
    }

    @Test
    void update_surrender_noMatch() throws Exception {
        when(authenticator.getHost()).thenReturn("Test");
        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setStatus(PlayerStatusEnum.SURRENDERED);
        String json = mapper.writeValueAsString(matchRequest);

        when(matchesRepository.find("1")).thenReturn(Optional.empty());

        assertRequestWithBody("PATCH", "/api/matches/1", json, 400);
    }
}