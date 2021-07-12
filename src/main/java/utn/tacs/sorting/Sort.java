package utn.tacs.sorting;

import lombok.Getter;
import lombok.Setter;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
@Setter
public class Sort {

    private SortField sortField;
    private boolean asc;
    private boolean defined = false;

    public Sort(String field, String sortDirection) throws SortingException {
        if (!field.equals("")) {
            long count = Arrays.stream(SortField.values()).filter(x -> x.name().equals(field.toUpperCase())).count();
            if (count == 0) throw new SortingException("Invalid field value");
            if (Stream.of("asc", "desc").noneMatch(x -> x.equals(sortDirection.toLowerCase())))
                throw new SortingException("Invalid sort Direction value");
            this.sortField = SortField.valueOf(field.toUpperCase());
            this.asc = sortDirection.toLowerCase().equals("asc");
            this.defined = true;
        }
    }

    public org.springframework.data.domain.Sort getSortData() {
        var direction = org.springframework.data.domain.Sort.Direction.fromString(this.asc ? "ASC": "DESC");
        return org.springframework.data.domain.Sort.by(direction, this.getSortField().name());
    }


}
