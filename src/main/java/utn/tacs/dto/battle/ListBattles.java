package utn.tacs.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.Battle;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListBattles {
    private List<Battle> battles;
}
