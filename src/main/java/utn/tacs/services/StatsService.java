package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.domain.repositories.MatchesStatsRepository;
import utn.tacs.domain.repositories.UsersStatsRepository;
import utn.tacs.dto.match.MatchCreateRequest;
import utn.tacs.dto.match.MatchStatsModel;
import utn.tacs.dto.match.MatchStatusEnum;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.dto.player.PlayerStats;
import utn.tacs.dto.player.PlayerStatsModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final UsersStatsRepository usersStatsRepository;
    private final MatchesRepository matchesRepository;

    public StatsService(UsersStatsRepository usersStatsRepository, MatchesRepository matchesRepository) {
        this.usersStatsRepository = usersStatsRepository;
        this.matchesRepository = matchesRepository;
    }

    public PlayerStatsModel find(String userId) {
        return PlayerStatsModel.toPlayerStatsModel(usersStatsRepository.find(userId).orElse(new PlayerStats(userId)));
    }

    public List<PlayerStatsModel> findAll() {
        return usersStatsRepository.findAll().stream().map(PlayerStatsModel::toPlayerStatsModel).collect(Collectors.toList());
    }

    public MatchStatsModel findMatches(String initDate, String finishDate) throws Exception {
        List<Match> matches = matchesRepository.findAll();
        Date init = new SimpleDateFormat("yyyy/MM/dd").parse(initDate);
        Date finish = new SimpleDateFormat("yyyy/MM/dd").parse(finishDate);
        MatchStatsModel matchStatsModel = new MatchStatsModel();
        List<Match> matchesInDate = matches.stream().filter(match -> match.getCreationDate().after(init) && match.getCreationDate().before(finish)).collect(Collectors.toList());
        matchStatsModel.setCreatedMatches(matchesInDate.size());
        matchStatsModel.setCanceledMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.CANCELED)).count());
        matchStatsModel.setEndedMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.FINISHED)).count());
        matchStatsModel.setInProgressMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.IN_PROGRESS)).count());

        return matchStatsModel;
    }

    void process_create(MatchCreateRequest matchCreateRequest){
        PlayerStats hostPlayer = usersStatsRepository.find(matchCreateRequest.getHost()).orElse(new PlayerStats(matchCreateRequest.getHost())).incrementcreate();
        usersStatsRepository.save(hostPlayer);
        List<PlayerStats> players = matchCreateRequest.getPlayers()
                .stream()
                .filter(player -> !player.equals(matchCreateRequest.getHost()))
                .map(player -> usersStatsRepository.find(player).orElse(new PlayerStats(player)).incrementProgress())
                .collect(Collectors.toList());
        usersStatsRepository.saveAll(players);
    }

    void process_surrender(MatchUpdateRequest matchUpdateRequest, Match match) {
        usersStatsRepository.find(matchUpdateRequest.getPlayer()).ifPresent(playerStats -> usersStatsRepository.update(playerStats.incrementSurrender()));
        match.getPlayers()
                .keySet()
                .stream()
                .filter(player -> !player.equals(matchUpdateRequest.getPlayer()))
                .map(player -> usersStatsRepository.find(player).orElseThrow())
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
