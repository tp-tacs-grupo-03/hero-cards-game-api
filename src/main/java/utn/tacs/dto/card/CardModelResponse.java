package utn.tacs.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class CardModelResponse {

    private String name;
    private String id;
    private Powerstats powerstats;
    private Image image;

    public CardModelResponse(String cardId) {
        this.id = cardId;
    }

    public static CardModelResponse toCardModelResponse(Character character){
        CardModelResponse cardModelResponse = new CardModelResponse(character.getId());
        cardModelResponse.setPowerstats(character.getPowerstats());
        cardModelResponse.setImage(character.getImage());
        cardModelResponse.setName(character.getName());
        return cardModelResponse;
    }
}
