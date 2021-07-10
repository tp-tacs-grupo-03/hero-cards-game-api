package utn.tacs.domain.repositories;

import org.springframework.data.domain.Pageable;
import utn.tacs.domain.Match;
import utn.tacs.domain.Player;
import utn.tacs.sorting.Sort;

import java.util.List;

public interface PlayerRepository {

    List<Player> findAll(Pageable pageable, Sort sort);

}
