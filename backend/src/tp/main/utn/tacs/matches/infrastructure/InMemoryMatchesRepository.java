package utn.tacs.matches.infrastructure;

import org.springframework.stereotype.Service;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class InMemoryMatchesRepository implements MatchesRepository {

    HashMap<String, Match> matches = new HashMap<>();

    @Override
    public void save(Match match) {
        String id = UUID.randomUUID().toString();
        match.setId(id);
        matches.put(id, match);
    }

    @Override
    public Optional<Match> find(String id) {
        return Optional.ofNullable(matches.get(id));
    }


}
