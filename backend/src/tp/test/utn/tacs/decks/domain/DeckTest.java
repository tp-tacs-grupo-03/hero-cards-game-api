package utn.tacs.decks.domain;

import org.junit.jupiter.api.Test;
import utn.tacs.cards.domain.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class DeckTest {

    @Test
    void split() {
        List<Card> cards = new ArrayList<>();
        Card card;
        for (int i = 0; i < 40; i++){
            card = new Card();
            card.setId("" + i);
            cards.add(card);
        }

        Deck deck = new Deck(cards, "deck");
        List<Queue<Card>> cardsPart = deck.split(3);
        System.out.println(cardsPart);
        for (Queue<Card> list: cardsPart
             ) {
            System.out.println(list.size());
        }

    }
}