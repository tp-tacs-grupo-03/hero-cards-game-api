package utn.tacs.common.client.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

    private String id;
    private String name;
    private Powerstats powerstats;
    private Image image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public Image getImage() {
        return image;
    }
}
