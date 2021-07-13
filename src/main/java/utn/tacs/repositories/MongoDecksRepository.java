package utn.tacs.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDecksRepository implements DecksRepository {

    private final MongoOperations mongoOperations;
    private final String collectionName = "Decks";

    public MongoDecksRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Deck> findAll(Pageable pageable, Sort sort, String filterName) {
        final Query query = new Query();
        query.with(pageable)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());
        query.addCriteria(Criteria.where("name").regex(".*" + filterName + ".*"));
        return mongoOperations.find(query, Deck.class, collectionName);
    }

    @Override
    public Optional<Deck> find(String id) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(id)), Deck.class, collectionName));
    }

    @Override
    public void delete(String id) {
        mongoOperations.remove(new Query(Criteria.where("id").is(id)), Deck.class, collectionName);
    }

    @Override
    public Deck save(Deck deck) {
        return mongoOperations.save(deck, collectionName);
    }

    @Override
    public void update(Deck deck) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(deck.getId()));
        Update update = new Update();
        update.set("cardIds", deck.getCardIds());
        update.set("name", deck.getName());
        mongoOperations.updateFirst(query, update, Deck.class, collectionName);
    }

    @Override
    public int getTotal() {
        return Math.toIntExact(mongoOperations.getCollection(collectionName).countDocuments());
    }
}
