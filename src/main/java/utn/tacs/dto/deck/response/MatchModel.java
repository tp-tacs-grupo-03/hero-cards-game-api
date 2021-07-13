package utn.tacs.dto.deck.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
public class MatchModel {

    private String opponentId;
    private String deckId;
    private MatchTypeEnum type;
}
