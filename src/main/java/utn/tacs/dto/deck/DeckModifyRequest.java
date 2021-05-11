package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeckModifyRequest {

    private String deckId;
    private String newName;

    public DeckModifyRequest(String deckId, String newName) {
        this.deckId = deckId;
        this.newName = newName;
    }
}
