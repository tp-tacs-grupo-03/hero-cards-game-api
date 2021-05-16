package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.Match;
@Getter @Setter @NoArgsConstructor
public class PlayerStatus {
    String nextCard;

    public PlayerStatus(Match match, String player) {
        nextCard = match.getNextCard(player).getId();
    }

}
