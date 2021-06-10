package utn.tacs.dto.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerModelResponse {


    private String id;
    private String name;

    public PlayerModelResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
