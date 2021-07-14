package utn.tacs.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public class PlayerStats implements Serializable {

    @Id
    private String id;
    private int wonMatches;
    private int lostMatches;
    private int surrenderedMatches;
    private int inProgressMatches;
    private int createdMatches;
    private String image;
    private String name;

    public PlayerStats(String id) {
        this.id = id;
        this.wonMatches = 0;
        this.lostMatches = 0;
        this.surrenderedMatches = 0;
        this.createdMatches = 0;
        this.inProgressMatches = 0;
    }

    public int getTotalMatches(){
        return wonMatches + lostMatches + surrenderedMatches + inProgressMatches;
    }

}
