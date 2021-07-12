package utn.tacs.dto.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchPagingRequest extends Paging {

    private String field;
    private boolean battle;

    public MatchPagingRequest(String field, int page, int pageSize, String sortDirection, boolean battle) {
        super(page, pageSize, sortDirection);
        this.field = field;
        this.battle = battle;
    }
}
