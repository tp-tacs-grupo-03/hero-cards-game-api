package utn.tacs.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardModelResponse {

    String name;
    String cardId;
    Powerstats powerStats;
    Image image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardModelResponse(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Powerstats getPowerStats() {
        return powerStats;
    }

    public void setPowerStats(Powerstats powerStats) {
        this.powerStats = powerStats;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
