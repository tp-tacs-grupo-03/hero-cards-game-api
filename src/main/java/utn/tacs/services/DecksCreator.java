package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckCreateRequest;
import utn.tacs.dto.deck.DeckModelResponse;
import utn.tacs.repositories.DecksRepository;

@Service
public class DecksCreator {

    private DecksRepository repository;

    public DecksCreator(DecksRepository repository) {
        this.repository = repository;
    }

    public DeckModelResponse create(DeckCreateRequest deckCreateRequest){
        Deck deck = new Deck(deckCreateRequest.getCards(), deckCreateRequest.getName());
        repository.save(deck);
        return new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName());
    }
}
