package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.sorting.IdComparable;
import utn.tacs.sorting.NameComparable;

@Getter
@Setter
@NoArgsConstructor
public class Player implements IdComparable, NameComparable {

    private String id;
    private String name;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
