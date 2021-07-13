package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import utn.tacs.TacsApplication;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.deck.response.MatchTypeEnum;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TacsApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsControllerTest extends RequestTestCase{

    @MockBean
    MatchesRepository matchesRepository;
    @MockBean
    UsersRepository usersRepository;

    @Test
    void getMatches() throws Exception {
        List<CardId> cardIds = new ArrayList<>(Arrays.asList(new CardId("1"), new CardId("2")));
        Deck arena = new Deck(cardIds, "Arena");
        arena.setId("1");

        List<Queue<CardId>> split = arena.split(2);
        final Map<String, Queue<CardId>> players = new HashMap<>();
        players.put("Test", split.get(0));
        players.put("z1234", split.get(1));
        LocalDateTime date = LocalDateTime.now();
        Match match = new Match(players, "1", MatchTypeEnum.RANKED);
        List<Match> matches = new ArrayList<>();
        matches.add(match);

        when(matchesRepository.findAll()).thenReturn(matches);


        assertRequest("GET"
                        ,"/api/stats/matches?initDate=" + localDateToString(date.minusDays(1)) + "&finishDate=" + localDateToString(date.plusDays(1))
                        ,200);
    }

    private String localDateToString(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(dateTimeFormatter);
    }

    @Test
    void getLeaderboard() throws Exception {
        when(usersRepository.findAll(PageRequest.of(0, 10), new Sort("createdMatches", "ASC"))).thenReturn(new ArrayList<>(Collections.singletonList(new PlayerStats("1"))));
        assertRequest("GET", "/api/stats/leadderboard", 200);
    }

    @Test
    void getRecord() throws Exception {
        when(usersRepository.find("1")).thenReturn(new PlayerStats("1"));
        assertRequest("GET", "/api/stats/users/1", 200);
    }
}