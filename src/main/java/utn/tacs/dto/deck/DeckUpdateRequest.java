package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeckUpdateRequest {

    private String deckId;
    private String newName;
    private List<String> cards;

    public DeckUpdateRequest(String deckId, String newName, List<String> cards) {
        this.deckId = deckId;
        this.newName = newName;
        this.cards = cards;
    }
}
