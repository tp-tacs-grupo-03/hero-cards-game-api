package utn.tacs.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.dto.match.MatchPersistModel;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MongoMatchesRepository implements MatchesRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Matches";

    public MongoMatchesRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void save(Match match) {
        mongoOperations.save(MatchPersistModel.toMatchPersistModel(match), collectionName);
    }

    @Override
    public Optional<Match> find(String id) {
        return Optional.ofNullable(MatchPersistModel.toMatch(mongoOperations.findOne(new Query(Criteria.where("id").is(id)), MatchPersistModel.class)));
    }

    @Override
    public List<Match> findAll(Pageable pageable, Sort sort) {
        final Query query = new Query();
        query.with(pageable)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());
        List<MatchPersistModel> matchPersistModels = mongoOperations.find(query, MatchPersistModel.class, collectionName);
        return matchPersistModels.stream().map(MatchPersistModel::toMatch).collect(Collectors.toList());
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
        mongoOperations.updateFirst(query, update, MatchPersistModel.class, collectionName);
    }

    @Override
    public List<Match> findAll() {
        return mongoOperations.findAll(MatchPersistModel.class, collectionName).stream().map(MatchPersistModel::toMatch).collect(Collectors.toList());
    }

    @Override
    public int getTotal() {
        return Math.toIntExact(mongoOperations.getCollection(collectionName).countDocuments());
    }
}
