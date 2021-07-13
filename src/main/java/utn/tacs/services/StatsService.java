package utn.tacs.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Match;
import utn.tacs.domain.repositories.MatchesRepository;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.match.MatchCreateRequest;
import utn.tacs.dto.match.MatchStatsModel;
import utn.tacs.dto.match.MatchStatusEnum;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.domain.PlayerStats;
import utn.tacs.dto.player.ListPlayerStatsModel;
import utn.tacs.dto.player.PlayerStatsModel;
import utn.tacs.sorting.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final UsersRepository usersRepository;
    private final MatchesRepository matchesRepository;

    public StatsService(UsersRepository usersRepository, MatchesRepository matchesRepository) {
        this.usersRepository = usersRepository;
        this.matchesRepository = matchesRepository;
    }

    public PlayerStatsModel find(String userId) {
        return PlayerStatsModel.toPlayerStatsModel(usersRepository.find(userId));
    }

    public ListPlayerStatsModel findAll(Pageable pageable, Sort sort) {

        final ListPlayerStatsModel players = new ListPlayerStatsModel();
        final List<PlayerStats> playerStatsModel = usersRepository.findAll(pageable, sort);
        final int total = usersRepository.getTotal();

        players.setPlayerStats(playerStatsModel);

        players.setPage(pageable.getPageNumber());
        players.setPageSize(pageable.getPageSize());
        players.setTotal_count(total);
        players.setPage_count(total/pageable.getPageSize());

        return players;
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
        PlayerStats hostPlayer = usersRepository.find(matchCreateRequest.getHost()).incrementcreate();
        usersRepository.save(hostPlayer);
        matchCreateRequest.getPlayers()
                .stream()
                .filter(player -> !player.equals(matchCreateRequest.getHost()))
                .forEach(player -> usersRepository
                        .save(usersRepository.find(player))
                );
    }

    void process_surrender(MatchUpdateRequest matchUpdateRequest, Match match) {
        usersRepository.find(matchUpdateRequest.getPlayer()).incrementSurrender();
        match.getPlayers()
                .keySet()
                .stream()
                .filter(player -> !player.equals(matchUpdateRequest.getPlayer()))
                .forEach(player -> usersRepository
                        .find(player)
                        .incrementSurrender()
                );
    }

    void process_win(String winnerID, Map<String, Queue<CardId>> players) {
        usersRepository.find(winnerID).incrementWin();
        players.keySet()
                .stream()
                .filter(player -> !player.equals(winnerID))
                .forEach(player -> usersRepository
                        .find(player)
                        .incrementWin()
                );
    }
}
