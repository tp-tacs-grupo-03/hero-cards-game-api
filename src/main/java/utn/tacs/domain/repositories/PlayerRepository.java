package utn.tacs.domain.repositories;

import utn.tacs.domain.Player;
import utn.tacs.pagination.Page;
import utn.tacs.sorting.Sort;

import java.util.List;

public interface PlayerRepository {

    List<Player> findAll(Page page, Sort sort);

}
