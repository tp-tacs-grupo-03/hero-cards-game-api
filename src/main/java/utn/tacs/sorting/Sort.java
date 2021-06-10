package utn.tacs.sorting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
public class Sort {

    private SortField sortField;
    private boolean asc;

    public Sort(String field, String sortDirection) throws SortingException {
        long count = Arrays.stream(SortField.values()).filter(x -> x.name().equals(field.toUpperCase())).count();
        if (count == 0) throw new SortingException("Invalid field value");
        if (Stream.of("asc", "desc").noneMatch(x -> x.equals(sortDirection.toLowerCase()))) throw new SortingException("Invalid sort Direction value");
        this.sortField = SortField.valueOf(field.toUpperCase());
        this.asc = sortDirection.toLowerCase().equals("asc");
    }
}
