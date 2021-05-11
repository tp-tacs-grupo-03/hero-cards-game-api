package utn.tacs.dto.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchPagingRequest extends Paging {

    private String field;

    public MatchPagingRequest(String field, int page, int pageSize, String sortDirection) {
        super(page, pageSize, sortDirection);
        this.field = field;
    }
}
