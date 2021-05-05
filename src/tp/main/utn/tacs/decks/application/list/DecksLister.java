package utn.tacs.decks.application.list;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;
import utn.tacs.decks.application.DeckModelResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecksLister {

    DecksRepository repository;

    public DecksLister(DecksRepository repository) {
        this.repository = repository;
    }

    public List<DeckModelResponse> list(){
        List<Deck> decks = repository.findAll();
        return decks.stream().map(deck -> new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName())).collect(Collectors.toList());
    }
}
