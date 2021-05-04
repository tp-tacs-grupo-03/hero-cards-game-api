package utn.tacs.matches.application.create;


import org.springframework.stereotype.Service;
import utn.tacs.cards.domain.CardId;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

import java.util.*;

@Service
public class MatchesCreator {

    MatchesRepository repository;
    DecksRepository decks;

    public MatchesCreator(MatchesRepository repository, DecksRepository decks) {
        this.repository = repository;
        this.decks = decks;
    }

    public MatchModelResponse create(MatchCreateRequest matchCreateRequest) throws Exception {
        Deck deck = decks.find(matchCreateRequest.getDeck()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.shuffle();
        List<Queue<CardId>> split = deck.split(matchCreateRequest.getPlayers().size());

        Map<String, Queue<CardId>> players = new HashMap<>();
        for (int i =0; i < matchCreateRequest.getPlayers().size(); i++){
            players.put(matchCreateRequest.getPlayers().get(i), split.get(i));
        }

        Match match = new Match(players, matchCreateRequest.getDeck(), new Date());
        repository.save(match);
        return new MatchModelResponse(match.getId(), matchCreateRequest.getPlayers(), match.getDeck(), match.getStatus(), match.getCreationDate());
    }

}
