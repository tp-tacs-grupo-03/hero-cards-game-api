package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListDeckModelResponse {
    private String page;
    private String pageSize;
    private String page_count;
    private String total_count;
    private List<DeckModelResponse> deckModelResponses;
}
