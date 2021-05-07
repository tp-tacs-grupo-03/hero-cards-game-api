package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckModelResponse;
import utn.tacs.repositories.DecksRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecksLister {

    private DecksRepository repository;

    public DecksLister(DecksRepository repository) {
        this.repository = repository;
    }

    public List<DeckModelResponse> list(){
        final List<Deck> decks = repository.findAll();
        return decks.stream().map(deck -> new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName())).collect(Collectors.toList());
    }
}
