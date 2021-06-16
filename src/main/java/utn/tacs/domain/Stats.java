package utn.tacs.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.tacs.domain.repositories.UsersStatsRepository;
import utn.tacs.dto.match.MatchCreateRequest;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.dto.player.PlayerStats;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class Stats {

    @Autowired
    private UsersStatsRepository usersStatsRepository;



}
