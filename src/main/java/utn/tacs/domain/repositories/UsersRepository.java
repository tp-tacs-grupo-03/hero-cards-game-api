package utn.tacs.domain.repositories;

import org.springframework.data.domain.Pageable;
import utn.tacs.domain.PlayerStats;
import utn.tacs.sorting.Sort;

import java.util.List;


public interface UsersRepository {

    void save(PlayerStats userId);

    PlayerStats find(String userId);

    PlayerStats upsert(PlayerStats playerStats);

    void update(PlayerStats player);

    List<PlayerStats> findAll(Pageable pageable);

    List<PlayerStats> findByName(String name, Sort sort);
}
