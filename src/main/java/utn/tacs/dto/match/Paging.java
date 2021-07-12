package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Paging {

    private int page;
    private int size;
    private String sortDirection;

    public Paging(int page, int size, String sortDirection) {
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }
}
