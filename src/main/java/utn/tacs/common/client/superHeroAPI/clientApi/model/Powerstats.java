package utn.tacs.common.client.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Powerstats implements Serializable {

    private Integer durability;
    private Integer intelligence;
    private Integer speed;
    private Integer power;
    private Integer combat;
    private Integer strength;

    public boolean hasAllPowerStats() {
        return durability != null &&
                intelligence != null &&
                speed != null &&
                power != null &&
                combat != null &&
                strength != null;
    }
}
