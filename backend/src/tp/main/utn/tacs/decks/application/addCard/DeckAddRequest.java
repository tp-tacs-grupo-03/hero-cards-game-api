package utn.tacs.decks.application.addCard;

import org.springframework.stereotype.Service;
import utn.tacs.cards.domain.Card;

import java.util.List;

@Service
public class DeckAddRequest {
    private String deckId;
    private List<Card> cards;

    public DeckAddRequest(String deckId, List<Card> cards) {
        this.deckId = deckId;
        this.cards = cards;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public List<Card> getCard() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
