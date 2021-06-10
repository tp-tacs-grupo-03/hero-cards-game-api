package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.CardId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlayerCreateRequest {

    private String id;
    private String name;

    public PlayerCreateRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
