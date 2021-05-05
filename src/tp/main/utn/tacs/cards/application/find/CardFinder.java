package utn.tacs.cards.application.find;

import org.springframework.stereotype.Service;
import utn.tacs.cards.CardModelResponse;
import utn.tacs.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.superHeroAPI.clientApi.model.Character;

@Service
public class CardFinder {

    SuperHeroApi apiClient;

    public CardFinder(SuperHeroApi apiClient) {
        this.apiClient = apiClient;
    }

    public CardModelResponse find(CardFindRequest cardFindRequest){
        Character character = apiClient.getCharacter(cardFindRequest.getCardId().getId()).getBody();
        CardModelResponse cardModelResponse = new CardModelResponse(cardFindRequest.getCardId().getId());
        cardModelResponse.setImage(character.getImage());
        cardModelResponse.setPowerStats(character.getPowerstats());
        cardModelResponse.setName(character.getName());
        return cardModelResponse;
    }
}
