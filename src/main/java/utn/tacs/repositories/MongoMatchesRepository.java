package utn.tacs.repositories;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.pagination.Page;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

public class MongoMatchesRepository implements MatchesRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Matches";

    public MongoMatchesRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void save(Match match) {
        mongoOperations.save(match, collectionName);
    }

    @Override
    public Optional<Match> find(String id) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(id)), Match.class, collectionName));
    }

    @Override
    public List<Match> findAll(Page page, Sort sort) {
        return mongoOperations.findAll(Match.class, collectionName);
    }

    @Override
    public void update(Match match) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(match.getId()));
        Update update = new Update();
        update.set("players", match.getPlayers());
        update.set("deck", match.getDeck());
        update.set("status", match.getStatus());
        update.set("endDate", match.getEndDate());
        update.set("winnerID", match.getWinnerID());
        update.set("battles", match.getBattles());
        mongoOperations.updateFirst(query, update, Match.class, collectionName);
    }
}
