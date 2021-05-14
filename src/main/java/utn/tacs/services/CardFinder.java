package utn.tacs.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.dto.deck.response.CardDataModel;
import utn.tacs.repositories.CardsRepository;

import java.util.List;

@Slf4j
@Service
public class CardFinder {

    private SuperHeroApi apiClient;

    private CardsRepository repository;

    public CardFinder(SuperHeroApi apiClient, CardsRepository repository) {
        this.apiClient = apiClient;
        this.repository = repository;
    }

    public CardModelResponse find(CardFindRequest cardFindRequest){
        final Character character = apiClient.getCharacter(cardFindRequest.getCardId().getId());
        CardModelResponse cardModelResponse = new CardModelResponse(cardFindRequest.getCardId().getId());
        cardModelResponse.setImage(character.getImage());
        cardModelResponse.setPowerstats(character.getPowerstats());
        cardModelResponse.setName(character.getName());
        return cardModelResponse;
    }

    public List<CardDataModel> findAll(int page, int pageSize, String sortField, String sortDirection) {
        return repository.findAll();
    }
}
