package utn.tacs.repositories;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.pagination.Page;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDecksRepository implements DecksRepository {
    private final MongoOperations mongoOperations;

    public MongoDecksRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Deck> findAll(Page page, Sort sort) {
        return mongoOperations.findAll(Deck.class, "test");
    }

    @Override
    public Optional<Deck> find(String id) {
        return Optional.ofNullable(mongoOperations.findOne(new Query(Criteria.where("id").is(id)), Deck.class, "test"));
    }

    @Override
    public void delete(String id) {
        mongoOperations.remove(new Query(Criteria.where("id").is(id)), Deck.class, "test");
    }

    @Override
    public Deck save(Deck deck) {
        System.out.println("Hola");
        return mongoOperations.save(deck, "test");
    }

    @Override
    public void update(Deck deck) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Alex"));
        Update update = new Update();
        update.set("cardIds", deck.getCardIds());
        update.set("name", deck.getName());
        mongoOperations.updateFirst(query, update, Deck.class);
    }
}
