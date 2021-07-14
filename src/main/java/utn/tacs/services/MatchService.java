package utn.tacs.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.dto.match.*;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchesRepository matchesRepository;
    private final DecksRepository decksRepository;
    private final StatsService statsService;

    public MatchService(MatchesRepository matchesRepository, DecksRepository decksRepository, StatsService statsService) {
        this.matchesRepository = matchesRepository;
        this.decksRepository = decksRepository;
        this.statsService = statsService;
    }

    public MatchModelResponse create(MatchCreateRequest matchCreateRequest) throws Exception {
        final Deck deck = decksRepository.find(matchCreateRequest.getDeck()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.shuffle();

        List<String> playersId = new ArrayList<>();
        playersId.add(matchCreateRequest.getHost());

        switch (matchCreateRequest.getType()){
            case RANKED:
                playersId.add(matchCreateRequest.getOpponent());
                break;
            case TRAINING:
                playersId.add("~~~ALBERTO-BOT~~~");
                break;
            default:
                throw new Exception("No existe el tipo" + matchCreateRequest.getType());
        }

        final List<Queue<CardId>> split = deck.split(playersId.size());

        final Map<String, Queue<CardId>> players = new HashMap<>();
        for (int i = 0; i < playersId.size(); i++){
            players.put(playersId.get(i), split.get(i));
        }

        final Match match = new Match(players, deck.getId(), matchCreateRequest.getType());
        matchesRepository.save(match);
        statsService.process_create(matchCreateRequest, playersId);
        return MatchModelResponse.toMatchModel(match, true);
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        final Match match = matchesRepository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        final MatchModelResponse matchModelResponse = MatchModelResponse.toMatchModel(match, matchFindRequest.isBattle());

        if (!match.isTerminated() && match.isPlayer(matchFindRequest.getPlayer())){
            matchModelResponse.setPlayerStatus(new PlayerStatus(match, matchFindRequest.getPlayer()));
        }
        matchModelResponse.setTurn(match.turn());
        matchModelResponse.setCardsLeft(match.cardLeft());
        return matchModelResponse;
    }

    public ListMatchModelResponse findAll(MatchPagingRequest req) throws SortingException {
        final Pageable pageable = PageRequest.of(req.getPage(),req.getSize());
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String player = auth.getName();
        final List<Match> matches = matchesRepository .findAllById(pageable,  new Sort(req.getField(), req.getSortDirection()), player);
        final int total = matchesRepository.getTotal(player);
        final ListMatchModelResponse listMatchModelResponse = new ListMatchModelResponse();
        listMatchModelResponse.setMatchModelResponses(
                matches.stream()
                        .map(match -> MatchModelResponse.toMatchModel(match, req.isBattle()))
                        .collect(Collectors.toList()
                        )
        );
        listMatchModelResponse.setPage("" + pageable.getPageNumber());
        listMatchModelResponse.setPageSize("" + pageable.getPageNumber());
        listMatchModelResponse.setTotal_count("" + total);
        listMatchModelResponse.setPage_count("" + total/pageable.getPageSize());

        return listMatchModelResponse;
    }

    public MatchModelResponse update(MatchUpdateRequest matchUpdateRequest) throws Exception {
        final Match match = matchesRepository.find(matchUpdateRequest.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe match con ese id"));

        if (matchUpdateRequest.getStatus() != null)
            surrender(matchUpdateRequest, match);
        else if (matchUpdateRequest.getAttribute() != null)
            fight(matchUpdateRequest, match);


        MatchModelResponse response = MatchModelResponse.toMatchModel(match, true);
        if (!match.isTerminated() && match.isPlayer(matchUpdateRequest.getPlayer())){
            response.setPlayerStatus(new PlayerStatus(match, matchUpdateRequest.getPlayer()));
        }
        response.setTurn(match.turn());
        response.setCardsLeft(match.cardLeft());
        return response;
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
