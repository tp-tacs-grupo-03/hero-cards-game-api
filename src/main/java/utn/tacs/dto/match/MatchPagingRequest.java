package utn.tacs.dto.match;

public class MatchPagingRequest extends Paging {

    private String field;

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
