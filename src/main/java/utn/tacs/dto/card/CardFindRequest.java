package utn.tacs.dto.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

@Getter
@Setter
@NoArgsConstructor
public class CardFindRequest {

    private CardId cardId;

    public CardFindRequest(CardId cardId) {
        this.cardId = cardId;
    }

}
