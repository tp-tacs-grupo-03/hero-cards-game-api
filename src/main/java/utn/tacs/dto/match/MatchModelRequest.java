package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.deck.response.Attribute;

@Getter
@Setter
@NoArgsConstructor
public class MatchModelRequest {

    private Attribute attribute;
}
