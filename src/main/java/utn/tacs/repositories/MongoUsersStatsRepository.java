package utn.tacs.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import utn.tacs.domain.repositories.UsersStatsRepository;
import utn.tacs.dto.match.MatchPersistModel;
import utn.tacs.dto.player.PlayerStats;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoUsersStatsRepository implements UsersStatsRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Stats";

    public MongoUsersStatsRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    @Override
    public void save(PlayerStats playerStats) {
        mongoOperations.save(playerStats, collectionName);
    }

    @Override
    public Optional<PlayerStats> find(String userId) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(userId)), PlayerStats.class, collectionName));
    }

    @Override
    public void update(PlayerStats player) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(player.getId()));
        Update update = new Update();
        update.set("wonMatches", player.getWonMatches());
        update.set("lostMatches", player.getLostMatches());
        update.set("surrenderedMatches", player.getSurrenderedMatches());
        update.set("inProgressMatch", player.getInProgressMatch());
        update.set("createdMatches", player.getCreatedMatches());

        mongoOperations.updateFirst(query, update, MatchPersistModel.class, collectionName);
        find(player.getId()).ifPresent(aPlayerStats -> mongoOperations.save(aPlayerStats, collectionName));
    }

    @Override
    public List<PlayerStats> findAll(Pageable pageable) {
        final Query query = new Query();
        query.with(pageable)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());
        final List<PlayerStats> playerStats = mongoOperations.find(query, PlayerStats.class, collectionName);
        return playerStats.stream()
                .sorted(Comparator.comparing(PlayerStats::getWonMatches, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
