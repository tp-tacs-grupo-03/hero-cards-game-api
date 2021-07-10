package utn.tacs.domain.repositories;

import utn.tacs.domain.PlayerStats;
import utn.tacs.sorting.Sort;

import java.util.List;


public interface UsersRepository {

    void save(PlayerStats userId);

    PlayerStats find(String userId);

    PlayerStats upsert(PlayerStats playerStats);

    void update(PlayerStats player);

    List<PlayerStats> findAll();

    List<PlayerStats> findByName(String name, Sort sort);
}
