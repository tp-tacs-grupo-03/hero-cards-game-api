package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeckModelResponse {


    private List<CardId> cardIds;
    private String id;
    private String name;

    public DeckModelResponse(List<CardId> cardIds, String id, String name) {
        this.cardIds = cardIds;
        this.id = id;
        this.name = name;
    }

}
