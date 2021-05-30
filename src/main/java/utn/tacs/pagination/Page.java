package utn.tacs.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.pagination.exceptions.PaginationException;

@Getter
@Setter
@NoArgsConstructor
public class Page {

    private int limit;
    private int offSet;

    public Page(int offSet, int limit) throws PaginationException {
        if (offSet < 0) throw new PaginationException("Invalid offset value");
        if (limit < 0) throw new PaginationException("Invalid limit value");
        this.offSet = offSet;
        this.limit = limit;
    }
}
