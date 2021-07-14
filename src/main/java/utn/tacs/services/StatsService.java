package utn.tacs.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utn.tacs.domain.AtomicUpdate;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public MatchStatsModel findMatches(String initDate, String finishDate) {
        List<Match> matches = matchesRepository.findAll();
        LocalDateTime init = getTime(initDate);
        LocalDateTime finish = getTime(finishDate);
        MatchStatsModel matchStatsModel = new MatchStatsModel();
        List<Match> matchesInDate = matches.stream().filter(match -> match.getCreationDate().isAfter(init) && match.getCreationDate().isBefore(finish)).collect(Collectors.toList());

        matchStatsModel.setCreatedMatches(matchesInDate.size());
        matchStatsModel.setCanceledMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.CANCELED)).count());
        matchStatsModel.setEndedMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.FINISHED)).count());
        matchStatsModel.setInProgressMatches((int) matchesInDate.stream().filter(match -> match.getStatus().equals(MatchStatusEnum.IN_PROGRESS)).count());

        return matchStatsModel;
    }

    private LocalDateTime getTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, formatter).atStartOfDay();
    }

    void process_create(MatchCreateRequest matchCreateRequest, List<String> playersId){
        usersRepository.atomicUpdate(matchCreateRequest.getHost(), AtomicUpdate.builder().createdMatches(1).inProgressMatches(1).build());
        playersId
                .stream()
                .filter(player -> !player.equals(matchCreateRequest.getHost()))
                .forEach(player -> usersRepository.atomicUpdate(player, AtomicUpdate.builder().inProgressMatches(1).build())
                );
    }

    void process_surrender(MatchUpdateRequest matchUpdateRequest, Match match) {
        usersRepository.atomicUpdate(matchUpdateRequest.getPlayer(), AtomicUpdate.builder().surrenderedMatches(1).inProgressMatches(-1).build());
        match.getPlayers()
                .keySet()
                .stream()
                .filter(player -> !player.equals(matchUpdateRequest.getPlayer()))
                .forEach(player -> usersRepository.atomicUpdate(player, AtomicUpdate.builder().wonMatches(1).inProgressMatches(-1).build())
                );
    }

    void process_win(String winnerID, Map<String, Queue<CardId>> players) {
        usersRepository.atomicUpdate(winnerID, AtomicUpdate.builder().wonMatches(1).inProgressMatches(-1).build());
        players.keySet()
                .stream()
                .filter(player -> !player.equals(winnerID))
                .forEach(player -> usersRepository.atomicUpdate(player, AtomicUpdate.builder().lostMatches(1).inProgressMatches(-1).build()));
    }
}
