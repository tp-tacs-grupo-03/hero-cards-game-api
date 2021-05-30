package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Paging {

    private int limit;
    private int offSet;
    private String sortDirection;

    public Paging(int offSet, int limit, String sortDirection) {
        this.offSet = offSet;
        this.limit = limit;
        this.sortDirection = sortDirection;
    }
}
