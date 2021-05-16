package utn.tacs.services;


import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchCreateRequest;
import utn.tacs.dto.match.MatchModelResponse;
import utn.tacs.repositories.DecksRepository;
import utn.tacs.repositories.MatchesRepository;

import java.util.*;

@Service
public class MatchesCreator {

    private MatchesRepository repository;
    private DecksRepository decks;

    public MatchesCreator(MatchesRepository repository, DecksRepository decks) {
        this.repository = repository;
        this.decks = decks;
    }

    public MatchModelResponse create(MatchCreateRequest matchCreateRequest) throws Exception {
        final Deck deck = decks.find(matchCreateRequest.getDeck()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.shuffle();
        final List<Queue<CardId>> split = deck.split(matchCreateRequest.getPlayers().size());

        final Map<String, Queue<CardId>> players = new HashMap<>();
        for (int i =0; i < matchCreateRequest.getPlayers().size(); i++){
            players.put(matchCreateRequest.getPlayers().get(i), split.get(i));
        }

        final Match match = new Match(players, matchCreateRequest.getDeck(), new Date());
        repository.save(match);
        return MatchModelResponse.toMatchModel(match);
    }

}
