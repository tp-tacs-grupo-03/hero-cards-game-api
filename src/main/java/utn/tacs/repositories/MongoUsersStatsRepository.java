package utn.tacs.repositories;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import utn.tacs.domain.repositories.UsersStatsRepository;
import utn.tacs.dto.player.PlayerStats;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoUsersStatsRepository implements UsersStatsRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Matches";

    public MongoUsersStatsRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    @Override
    public void save(PlayerStats playerStats) {
        find(playerStats.getId()).ifPresent(aPlayerStats -> mongoOperations.save(aPlayerStats, collectionName));
    }

    @Override
    public Optional<PlayerStats> find(String userId) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(userId)), PlayerStats.class, collectionName));
    }

    @Override
    public void saveAll(List<PlayerStats> players) {
        players.forEach(playerStats -> mongoOperations.save(playerStats, collectionName));
    }

    @Override
    public void update(PlayerStats player) {

    }

    @Override
    public List<PlayerStats> findAll() {
        return mongoOperations.findAll(PlayerStats.class, collectionName);
    }
}
