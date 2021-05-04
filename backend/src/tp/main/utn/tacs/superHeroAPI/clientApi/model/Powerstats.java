package utn.tacs.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Powerstats {

    private Integer durability;
    private Integer intelligence;
    private Integer speed;
    private Integer power;
    private Integer combat;
    private Integer strength;

    public Integer getDurability() {
        return durability;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getPower(){
        return power;
    }

    public Integer getCombat() {
        return combat;
    }

    public Integer getStrength() {
        return strength;
    }

}
