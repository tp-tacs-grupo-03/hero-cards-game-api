package utn.tacs.common.client.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor
public class Character implements Serializable {

    private String id;
    private String name;
    private Powerstats powerstats;
    private Image image;

}
