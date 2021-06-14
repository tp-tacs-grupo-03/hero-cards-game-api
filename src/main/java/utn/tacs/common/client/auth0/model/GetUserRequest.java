package utn.tacs.common.client.auth0.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.sorting.SortField;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class GetUserRequest {

    private String page;
    private String field;
    private String sortDirection;
    private String key;
    private String filterName;

    public GetUserRequest(int page, String asc, String field, String filterName) {
        this.page = "" + page;
        this.sortDirection = asc.toUpperCase().equals("ASC") ? ":1" : ":-1";
        this.field = field.toUpperCase().equals(SortField.NAME.name()) ? "nickname" :  "user_id";
        this.key = this.page + "-" + this.field + "-" + this.sortDirection;
        this.filterName = filterName;
    }

    public String getSortQueryParam() {
        return this.field + sortDirection;
    }

}
