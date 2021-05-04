package utn.tacs.cards;

import com.fasterxml.jackson.annotation.JsonInclude;
import utn.tacs.superHeroAPI.clientApi.model.Image;
import utn.tacs.superHeroAPI.clientApi.model.Powerstats;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardModelResponse {
    String cardId;
    Powerstats powerStats;
    Image image;

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
