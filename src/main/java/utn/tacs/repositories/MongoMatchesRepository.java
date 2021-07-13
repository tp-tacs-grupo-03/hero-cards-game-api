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
        MatchPersistModel save = mongoOperations.save(MatchPersistModel.toMatchPersistModel(match), collectionName);
        match.setId(save.getId());
    }

    @Override
    public Optional<Match> find(String id) {
        return Optional.ofNullable(MatchPersistModel.toMatch(mongoOperations.findOne(new Query(Criteria.where("id").is(id)), MatchPersistModel.class, collectionName)));
    }

    @Override
    public List<Match> findAllById(Pageable pageable, Sort sort, String playerID) {
        final Query query = new Query(Criteria.where("playersId").in(playerID));
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
    public int getTotal(String playerId) {
        return (int) mongoOperations.count(new Query(Criteria.where("playersId").in(playerId)), collectionName);
    }
}
