package utn.tacs.sorting;

import utn.tacs.dto.deck.response.MatchStatusEnum;

public interface StatusSortable {

    MatchStatusEnum getStatus();

    default int compareByStatus(StatusSortable lhs, StatusSortable rhs) {
        return lhs.getStatus().compareTo(rhs.getStatus());
    }

}
