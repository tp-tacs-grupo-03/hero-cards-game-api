package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

@Getter
@Setter
@NoArgsConstructor
public class DeckCardDeleteRequest {

    private String deckId;
    private CardId cardIdToDelete;


    public DeckCardDeleteRequest(String deckId, CardId cardId) {
        this.deckId = deckId;
        this.cardIdToDelete = cardId;
    }
}
