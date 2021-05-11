package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Paging {

    private int page;
    private int pageSize;
    private String sortDirection;

    public Paging(int page, int pageSize, String sortDirection) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortDirection = sortDirection;
    }
}
