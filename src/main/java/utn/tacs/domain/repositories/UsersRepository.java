package utn.tacs.domain.repositories;

import utn.tacs.domain.PlayerStats;

import java.util.List;
import java.util.Optional;


public interface UsersRepository {

    void save(PlayerStats userId);

    PlayerStats find(String userId);

    void saveAll(List<PlayerStats> players);

    PlayerStats upsert(PlayerStats playerStats);

    void update(PlayerStats player);

    List<PlayerStats> findAll();
}
