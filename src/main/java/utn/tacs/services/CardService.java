package utn.tacs.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utn.tacs.domain.exceptions.NotFoundCharacter;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.dto.deck.response.CardDataModel;
import utn.tacs.repositories.CardsRepository;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
public class CardService {

    private SuperHeroApi apiClient;

    private CardsRepository repository;

    public CardService(SuperHeroApi apiClient, CardsRepository repository) {
        this.apiClient = apiClient;
        this.repository = repository;
    }

    public CardModelResponse find(CardFindRequest cardFindRequest) throws URISyntaxException {
        final String id = cardFindRequest.getCardId().getId();
        final Character character = apiClient.getCharacter(id).orElseThrow(()-> new NotFoundCharacter(id));
        final CardModelResponse response = new CardModelResponse(cardFindRequest.getCardId().getId());
        response.setImage(character.getImage());
        response.setPowerstats(character.getPowerstats());
        response.setName(character.getName());
        return response;
    }

    public List<CardDataModel> findAll(int page, int pageSize, String sortField, String sortDirection) {
        return repository.findAll();
    }
}
