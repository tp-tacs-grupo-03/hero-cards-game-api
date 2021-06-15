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


    public void process_create(MatchCreateRequest matchCreateRequest){
        PlayerStats hostPlayer = new PlayerStats(matchCreateRequest.getHost()).incrementcreate();
        usersStatsRepository.save(hostPlayer);
        List<PlayerStats> players = matchCreateRequest.getPlayers()
                .stream()
                .filter(player -> !player.equals(matchCreateRequest.getHost()))
                .map(player -> new PlayerStats(player).incrementProgress())
                .collect(Collectors.toList());
        usersStatsRepository.saveAll(players);
    }

    public void process_surrender(MatchUpdateRequest matchUpdateRequest, Match match) {
        usersStatsRepository.find(matchUpdateRequest.getPlayer()).ifPresent(playerStats -> usersStatsRepository.update(playerStats.incrementSurrender()));
        match.getPlayers()
                .keySet()
                .stream()
                .filter(player -> !player.equals(matchUpdateRequest.getPlayer()))
                .map(PlayerStats::new)
                .map(PlayerStats::incrementWin)
                .forEach(usersStatsRepository::update);
    }

    void process_win(String winnerID, Map<String, Queue<CardId>> players) {
        usersStatsRepository.find(winnerID).ifPresent(playerStats -> usersStatsRepository.update(playerStats.incrementWin()));
        players.keySet()
                .stream()
                .filter(player -> !player.equals(winnerID))
                .map(PlayerStats::new)
                .map(PlayerStats::incrementLost)
                .forEach(usersStatsRepository::update);
    }
}
