package utn.tacs.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utn.tacs.common.security.Authenticator;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.player.ListPlayerModelResponse;
import utn.tacs.dto.player.User;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {

    private final UsersRepository usersRepository;
    private final Authenticator authenticator;


    public User findUserById(String id) {
        return User.toUser(usersRepository.find(id));
    }

    public ListPlayerModelResponse findAll(int page, Sort sort, String filterName) {
        List<PlayerStats> users = usersRepository.findByName(filterName, sort);
        Objects.requireNonNull(users);
        final String hostId = authenticator.getHost();

        ListPlayerModelResponse listPlayerModelResponse = new ListPlayerModelResponse();
        listPlayerModelResponse.setUsers(users
                .stream()
                .filter(playerStats -> !playerStats.getId().equals(hostId))
                .map(User::toUser)
                .collect(Collectors.toList())
        );

        return listPlayerModelResponse;
    }
}
