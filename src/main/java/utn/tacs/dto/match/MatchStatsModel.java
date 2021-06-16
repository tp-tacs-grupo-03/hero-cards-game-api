package utn.tacs.dto.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchStatsModel {
    private int inProgressMatches;
    private int endedMatches;
    private int canceledMatches;
    private int createdMatches;
}
