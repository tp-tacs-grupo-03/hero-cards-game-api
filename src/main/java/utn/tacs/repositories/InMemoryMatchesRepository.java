package utn.tacs.repositories;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.Pageable;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.Sortable;

import java.util.*;

@Service
public class InMemoryMatchesRepository implements MatchesRepository , Pageable<Match>, Sortable {

    private HashMap<String, Match> matches = new HashMap<>();

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

    @Override
    public List<Match> findAll(Page page, Sort sort) {
        final List<Match> sorted=  new ArrayList<>(matches.values());
        switch (sort.getSortField()) {
            case ID: sorted.sort(sort.isAsc() ? getComparatorById(): getComparatorById().reversed());
                break;
            case DATE: sorted.sort(sort.isAsc() ? getComparatorByDate(): getComparatorByDate().reversed());
                break;
            case STATUS: sorted.sort(sort.isAsc() ? getComparatorByStatus(): getComparatorByStatus().reversed());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sort.getSortField());
        }
        return getPage(page, sorted);
    }

    @Override
    public void update(Match match) {
        matches.replace(match.getId(), match);
    }


}
