package utn.tacs.common.client.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Powerstats {

    private Integer durability;
    private Integer intelligence;
    private Integer speed;
    private Integer power;
    private Integer combat;
    private Integer strength;

}
