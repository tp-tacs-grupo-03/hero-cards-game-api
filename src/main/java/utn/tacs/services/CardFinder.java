package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;

@Service
public class CardFinder {

    private SuperHeroApi apiClient;

    public CardFinder(SuperHeroApi apiClient) {
        this.apiClient = apiClient;
    }

    public CardModelResponse find(CardFindRequest cardFindRequest){
        final Character character = apiClient.getCharacter(cardFindRequest.getCardId().getId()).getBody();
        CardModelResponse cardModelResponse = new CardModelResponse(cardFindRequest.getCardId().getId());
        cardModelResponse.setImage(character.getImage());
        cardModelResponse.setPowerStats(character.getPowerstats());
        cardModelResponse.setName(character.getName());
        return cardModelResponse;
    }
}
