package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeckCreateRequest {

    private List<CardId> cardIds;
    private String name;

    public DeckCreateRequest(List<CardId> cardIds, String name) {
        this.cardIds = cardIds;
        this.name = name;
    }
}
