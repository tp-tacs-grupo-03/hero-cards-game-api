package utn.tacs.sorting;

import java.util.Comparator;

public interface Sortable {

    default Comparator<DateComparable> getComparatorByDate() {
        return Comparator.comparing(DateComparable::getDate);
    }

    default Comparator<IdComparable> getComparatorById() {
        return Comparator.comparing(IdComparable::getId);
    }

    default Comparator<NameComparable> getComparatorByName() {
        return Comparator.comparing(NameComparable::getName);
    }

    default Comparator<StatusComparable> getComparatorByStatus() { return Comparator.comparing(StatusComparable::getStatus); }

}
