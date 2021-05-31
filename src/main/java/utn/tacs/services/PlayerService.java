package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.domain.Player;
import utn.tacs.dto.deck.*;
import utn.tacs.pagination.Page;
import utn.tacs.repositories.DecksRepository;
import utn.tacs.repositories.PlayerRepository;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public ListPlayerModelResponse findAll(Page page, Sort sort) {
        final List<Player> players = repository.findAll(page, sort);
        final ListPlayerModelResponse list = new ListPlayerModelResponse();
        list.setPlayers(players.stream()
                .map(deck -> new PlayerModelResponse(deck.getId(), deck.getName()))
                .collect(Collectors.toList()));
        return list;
    }

    public PlayerModelResponse create(PlayerCreateRequest req) {
        final Player player = new Player(req.getId(), req.getName());
        repository.save(player);
        return new PlayerModelResponse(player.getId(), player.getName());
    }

}
