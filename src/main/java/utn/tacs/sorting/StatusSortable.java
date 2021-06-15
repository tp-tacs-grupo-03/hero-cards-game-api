package utn.tacs.sorting;

import utn.tacs.dto.match.MatchStatusEnum;

public interface StatusSortable {

    MatchStatusEnum getStatus();

    default int compareByStatus(StatusSortable lhs, StatusSortable rhs) {
        return lhs.getStatus().compareTo(rhs.getStatus());
    }

}
