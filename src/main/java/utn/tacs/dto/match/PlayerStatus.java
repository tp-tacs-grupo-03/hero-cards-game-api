package utn.tacs.dto.match;

import lombok.Getter;
import lombok.Setter;
import utn.tacs.domain.Match;

public class PlayerStatus {
    @Getter @Setter String nextCard;
    @Getter @Setter boolean turn;
    @Getter @Setter int cardsLeft;

    public PlayerStatus(Match match, String player) {
        nextCard = match.getNextCard(player).getId();
        turn = match.turn(player);
        cardsLeft = match.cardLeft(player);
    }

}
