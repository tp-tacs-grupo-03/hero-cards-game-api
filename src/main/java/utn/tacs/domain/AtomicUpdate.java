package utn.tacs.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AtomicUpdate {
    private int wonMatches;
    private int lostMatches;
    private int surrenderedMatches;
    private int inProgressMatches;
    private int createdMatches;
}
