package utn.tacs.domain.repositories;

import org.springframework.data.domain.Pageable;
import utn.tacs.dto.player.PlayerStats;

import java.util.List;
import java.util.Optional;


public interface UsersStatsRepository {

    void save(PlayerStats userId);

    Optional<PlayerStats> find(String userId);

    void update(PlayerStats player);

    List<PlayerStats> findAll(Pageable pageable);
}
