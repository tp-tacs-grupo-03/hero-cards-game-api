package utn.tacs.domain.repositories;

import utn.tacs.dto.player.PlayerStats;

import java.util.List;
import java.util.Optional;


public interface UsersStatsRepository {

    void save(PlayerStats userId);
    Optional<PlayerStats> find(String userId);
    void saveAll(List<PlayerStats> players);
    void update(PlayerStats player);
}
