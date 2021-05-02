package utn.tacs.decks.application.create;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;
import utn.tacs.model.responseModel.DeckModelResponse;

@Service
public class DecksCreator {

    DecksRepository repository;

    public DecksCreator(DecksRepository repository) {
        this.repository = repository;
    }

    public DeckModelResponse create(DeckCreateRequest deckCreateRequest){
        Deck deck = new Deck(deckCreateRequest.getCards(), deckCreateRequest.getName());
        repository.save(deck);
        return new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName());
    }
}
