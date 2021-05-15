package utn.tacs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.controllers.exceptions.CardIdCannotFoundException;
import utn.tacs.controllers.exceptions.SomePowerStatsWithoutValueException;

import java.util.List;
import java.util.Optional;

@Service
public class CardAttributesValidator {

    private SuperHeroApi apiClient;

    public CardAttributesValidator(SuperHeroApi apiClient) {
        this.apiClient = apiClient;
    }

    public void validate(List<String> cards) throws CardIdCannotFoundException, SomePowerStatsWithoutValueException {
        for (String cardId : cards){
            final Optional<ResponseEntity<Powerstats>> maybeResponse = apiClient.getPowerstats(cardId);
            if (maybeResponse.isEmpty()) {
                throw new CardIdCannotFoundException(cardId);
            }
            final Powerstats powerstats = maybeResponse.get().getBody();
            assert powerstats != null;
            if (!powerstats.hasAllPowerStats()) {
                throw new SomePowerStatsWithoutValueException( cardId);
            }
        }
    }
}
