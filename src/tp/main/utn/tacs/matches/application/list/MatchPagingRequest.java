package utn.tacs.matches.application.list;

import utn.tacs.shared.application.Paging;

public class MatchPagingRequest extends Paging {
    String field;

    public MatchPagingRequest(String field, int page, int pageSize, String sortDirection) {
        super(page, pageSize, sortDirection);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
