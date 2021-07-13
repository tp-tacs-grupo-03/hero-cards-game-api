package utn.tacs.repositories;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.match.MatchPersistModel;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoUsersRepository implements UsersRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Stats";

    public MongoUsersRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    @Override
    public void save(PlayerStats playerStats) {
        mongoOperations.save(playerStats, collectionName);
    }

    @Override
    @Cacheable(value = "playerCache", key = "#userId")
    public PlayerStats find(String userId) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(userId)), PlayerStats.class, collectionName)).orElseThrow(() -> new RuntimeException("Not player id found"));
    }

    @Override
    @Transactional
    @CachePut(value = "playerCache", key = "#playerStats.id")
    public PlayerStats upsert(PlayerStats playerStats) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(playerStats.getId()));
        Update update = new Update();
        update.set("name", playerStats.getName());
        update.set("image", playerStats.getImage());
        mongoOperations.upsert(query, update, PlayerStats.class, collectionName);

        return mongoOperations.findOne(query, PlayerStats.class, collectionName);
    }

    @Override
    public void update(PlayerStats player) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(player.getId()));
        Update update = new Update();
        update.set("wonMatches", player.getWonMatches());
        update.set("lostMatches", player.getLostMatches());
        update.set("surrenderedMatches", player.getSurrenderedMatches());
        update.set("inProgressMatches", player.getInProgressMatches());
        update.set("createdMatches", player.getCreatedMatches());

        mongoOperations.updateFirst(query, update, MatchPersistModel.class, collectionName);
    }

    @Override
    public List<PlayerStats> findAll(Pageable pageable, Sort sort) {
        final Query query = new Query()
                .with(sort.getSortData())
                .with(pageable)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());
        return mongoOperations.find(query, PlayerStats.class, collectionName);
    }

    @Override
    public List<PlayerStats> findByName(Pageable pageable, String name) {
        Query query = new Query();
        query.with(pageable)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());
        query.addCriteria(Criteria.where("name").regex(".*" + name + ".*"));

        return mongoOperations.find(query, PlayerStats.class, collectionName);
    }
}
