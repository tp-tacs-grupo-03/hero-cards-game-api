package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeckDeleteRequest {

    private String deckId;

    public DeckDeleteRequest(String deckId) {
        this.deckId = deckId;
    }
}
