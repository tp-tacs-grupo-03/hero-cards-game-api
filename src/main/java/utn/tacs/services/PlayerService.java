package utn.tacs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.auth0.Auth0Api;
import utn.tacs.common.client.auth0.model.GetUserRequest;
import utn.tacs.common.client.auth0.model.User;
import utn.tacs.domain.Player;
import utn.tacs.dto.deck.ListPlayerModelResponse;
import utn.tacs.dto.deck.PlayerModelResponse;
import utn.tacs.services.exceptions.CannotGetPlayers;
import utn.tacs.services.exceptions.CannotGetUser;
import utn.tacs.sorting.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final static Logger log = LoggerFactory.getLogger(PlayerService.class);

    private Auth0Api auth0Api;

    public PlayerService(Auth0Api auth0Api) {
        this.auth0Api = auth0Api;
    }

    public ListPlayerModelResponse findAll(int page, Sort sort, String filterName) throws CannotGetPlayers {
        User[] users;
        try {
            final GetUserRequest getUserRequest = new GetUserRequest(page, sort.isAsc() ? "asc": "desc", sort.getSortField().name(), filterName);
            users = auth0Api.getUsers(getUserRequest).orElseThrow(() -> new CannotGetUser("Cannot get users"));
        } catch (CannotGetUser e) {
            log.debug(e.getMessage());
            throw new CannotGetPlayers("Cannot get players");
        }
        final List<Player> players = Arrays.stream(users)
                .map(u -> new Player(u.getUser_id(), u.getNickname())).collect(Collectors.toList());
         final ListPlayerModelResponse list = new ListPlayerModelResponse();
        list.setPlayers(players.stream()
                .map(deck -> new PlayerModelResponse(deck.getId(), deck.getName()))
                .collect(Collectors.toList()));
        return list;
    }

}
