package utn.tacs.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListPlayerModelResponse {
    List<User> users;
}
