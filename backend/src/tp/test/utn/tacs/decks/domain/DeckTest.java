package utn.tacs.decks.domain;

import org.junit.jupiter.api.Test;
import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class DeckTest {

    @Test
    void split() {
        List<CardId> cardIds = new ArrayList<>();
        CardId cardId;
        for (int i = 0; i < 40; i++){
            cardId = new CardId();
            cardId.setId("" + i);
            cardIds.add(cardId);
        }

        Deck deck = new Deck(cardIds, "deck");
        List<Queue<CardId>> cardsPart = deck.split(3);
        System.out.println(cardsPart);
        for (Queue<CardId> list: cardsPart
             ) {
            System.out.println(list.size());
        }

    }
}