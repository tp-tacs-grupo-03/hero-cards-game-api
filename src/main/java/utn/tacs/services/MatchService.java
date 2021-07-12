package utn.tacs.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Battle;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.dto.battle.BattleModelResponse;
import utn.tacs.dto.match.*;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private MatchesRepository matchesRepository;
    private DecksRepository decksRepository;
    private final StatsService statsService;

    public MatchService(MatchesRepository matchesRepository, DecksRepository decksRepository, StatsService statsService) {
        this.matchesRepository = matchesRepository;
        this.decksRepository = decksRepository;
        this.statsService = statsService;
    }

    public MatchModelResponse create(MatchCreateRequest matchCreateRequest) throws Exception {
        final Deck deck = decksRepository.find(matchCreateRequest.getDeck()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.shuffle();
        final List<Queue<CardId>> split = deck.split(matchCreateRequest.getPlayers().size());

        final Map<String, Queue<CardId>> players = new HashMap<>();
        for (int i = 0; i < matchCreateRequest.getPlayers().size(); i++){
            players.put(matchCreateRequest.getPlayers().get(i), split.get(i));
        }

        final Match match = new Match(players, matchCreateRequest.getDeck(), new Date());
        matchesRepository.save(match);
        statsService.process_create(matchCreateRequest);
        return MatchModelResponse.toMatchModel(match, false);
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        final Match match = matchesRepository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        final MatchModelResponse matchModelResponse = MatchModelResponse.toMatchModel(match, matchFindRequest.isBattle());
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String player = auth.getName();
        if (!match.isTerminated() && match.isPlayer(player)){
            matchModelResponse.setPlayerStatus(new PlayerStatus(match, player));
        }
        matchModelResponse.setTurn(match.turn());
        matchModelResponse.setCardsLeft(match.cardLeft());
        return matchModelResponse;
    }

    public List<Battle> findBattles(MatchFindRequest matchFindRequest) throws Exception {
        return matchesRepository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id")).getBattles();
    }

    public ListMatchModelResponse findAll(MatchPagingRequest req) throws SortingException {
        final Pageable pageable = PageRequest.of(req.getPage(),req.getSize());
        final List<Match> matches = matchesRepository.findAll(pageable,  new Sort(req.getField(), req.getSortDirection()));
        final int total = matchesRepository.getTotal();
        final ListMatchModelResponse listMatchModelResponse = new ListMatchModelResponse();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String player = auth.getName();
        listMatchModelResponse.setMatchModelResponses(
                matches.stream()
                        .map(match -> MatchModelResponse.toMatchModel(match, req.isBattle()))
                        .filter(match -> match.getPlayers().contains(player))
                        .collect(Collectors.toList()
                        )
        );
        listMatchModelResponse.setPage("" + pageable.getPageNumber());
        listMatchModelResponse.setPageSize("" + pageable.getPageNumber());
        listMatchModelResponse.setTotal_count("" + total);
        listMatchModelResponse.setPage_count("" + total/pageable.getPageSize());

        return listMatchModelResponse;
    }

    public Match update(MatchUpdateRequest matchUpdateRequest) throws Exception {
        final Match match = matchesRepository.find(matchUpdateRequest.getId()).orElseThrow(()-> new Exception("No hay match con ese id"));

        if (matchUpdateRequest.getStatus() != null)
            surrender(matchUpdateRequest, match);
        else if (matchUpdateRequest.getAttribute() != null)
            fight(matchUpdateRequest, match);

        return match;
    }

    private void fight(MatchUpdateRequest matchBattleRequest, Match match) throws Exception {
        if (match.isTerminated())
            throw new Exception("Match finalized");

        match.battle(matchBattleRequest);
        matchesRepository.update(match);

        if (match.isTerminated())
            statsService.process_win(match.getWinnerID(), match.getPlayers());
    }

    private void surrender(MatchUpdateRequest matchUpdateRequest, Match match) throws Exception {
        if (match.getStatus().equals(MatchStatusEnum.CANCELED))
            throw new Exception("Match surrendered");

        match.surrender(matchUpdateRequest.getPlayer());
        matchesRepository.update(match);
        statsService.process_surrender(matchUpdateRequest, match);
    }
}
