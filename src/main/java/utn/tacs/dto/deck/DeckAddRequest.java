package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeckAddRequest {

    private String deckId;
    private List<CardId> cardIds;

    public DeckAddRequest(String deckId, List<CardId> cardIds) {
        this.deckId = deckId;
        this.cardIds = cardIds;
    }
}
