package utn.tacs.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.domain.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardModelResponse {

    String name;
    String id;
    Powerstats powerstats;
    Image image;

    public CardModelResponse(String id) {
        this.id = id;
    }

    public static CardModelResponse toCardModelResponse(Character character){
        CardModelResponse cardModelResponse = new CardModelResponse(character.getId());
        cardModelResponse.setPowerstats(character.getPowerstats());
        cardModelResponse.setImage(character.getImage());
        cardModelResponse.setName(character.getName());
        return cardModelResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(Powerstats powerstats) {
        this.powerstats = powerstats;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
