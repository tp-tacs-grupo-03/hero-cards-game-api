package utn.tacs.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Battle;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.dto.battle.BattleModelResponse;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.match.*;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.exceptions.PaginationException;
import utn.tacs.repositories.DecksRepository;
import utn.tacs.repositories.MatchesRepository;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private MatchesRepository matchesRepository;
    private DecksRepository decksRepository;

    public MatchService(MatchesRepository matchesRepository, DecksRepository decksRepository) {
        this.matchesRepository = matchesRepository;
        this.decksRepository = decksRepository;
    }

    public BattleModelResponse begin(MatchBattleRequest matchBattleRequest) throws Exception {
        final Match match = matchesRepository.find(matchBattleRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        if (match.isTerminated())
            throw new Exception("Match finalizado");
        final Battle battle = match.battle(matchBattleRequest);
        matchesRepository.update(match);
        return new BattleModelResponse(battle.getAttribute(), battle.getPlayers(), battle.getWinner());
    }

    public CardModelResponse draw(MatchDrawRequest matchDrawRequest) throws Exception {
        final Match match = matchesRepository.find(matchDrawRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        final CardId cardId = match.getNextCard(matchDrawRequest.getPlayerId());
        return new CardModelResponse(cardId.getId());
    }

    public MatchModelResponse create(MatchCreateRequest matchCreateRequest) throws Exception {
        final Deck deck = decksRepository.find(matchCreateRequest.getDeck()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.shuffle();
        final List<Queue<CardId>> split = deck.split(matchCreateRequest.getPlayers().size());

        final Map<String, Queue<CardId>> players = new HashMap<>();
        for (int i =0; i < matchCreateRequest.getPlayers().size(); i++){
            players.put(matchCreateRequest.getPlayers().get(i), split.get(i));
        }

        final Match match = new Match(players, matchCreateRequest.getDeck(), new Date());
        matchesRepository.save(match);
        return MatchModelResponse.toMatchModel(match);
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        final Match match = matchesRepository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        final MatchModelResponse matchModelResponse = MatchModelResponse.toMatchModel(match);
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

    public ListMatchModelResponse findAll(MatchPagingRequest req) throws PaginationException, SortingException {
        final List<Match> matches = matchesRepository.findAll(new Page(req.getOffSet(), req.getLimit()),  new Sort(req.getField(), req.getSortDirection()));
        final ListMatchModelResponse listMatchModelResponse = new ListMatchModelResponse();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String player = auth.getName();
        listMatchModelResponse.setMatchModelResponses(matches.stream().map(MatchModelResponse::toMatchModel).filter(match -> match.getPlayers().contains(player)).collect(Collectors.toList()));

        return listMatchModelResponse;
    }

    public void update(MatchUpdateRequest matchUpdateRequest) throws Exception {
        final Match match = matchesRepository.find(matchUpdateRequest.getId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        match.surrender(matchUpdateRequest.getPlayer());
        matchesRepository.update(match);
    }
}
