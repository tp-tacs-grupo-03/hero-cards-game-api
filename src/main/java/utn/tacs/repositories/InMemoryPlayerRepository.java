package utn.tacs.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Player;
import utn.tacs.domain.repositories.PlayerRepository;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.Sortable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class InMemoryPlayerRepository implements PlayerRepository, Sortable {

    private HashMap<String, Player> players = new HashMap<>();

    @Override
    public List<Player> findAll(Pageable pageable, Sort sort) {
        final List<Player> sorted=  new ArrayList<>(players.values());
        switch (sort.getSortField()) {
            case ID: sorted.sort(sort.isAsc() ? getComparatorById(): getComparatorById().reversed());
                break;
            case NAME: sorted.sort(sort.isAsc() ? getComparatorByName(): getComparatorByName().reversed());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sort.getSortField());
        }
        return sorted;
    }

}
