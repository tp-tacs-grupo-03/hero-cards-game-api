package utn.tacs.controllers.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import utn.tacs.common.cache.CacheEventLogger;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.controllers.exceptions.CannotFoundPowerStats;
import utn.tacs.controllers.exceptions.SomePowerStatsWithoutValueException;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class CardAttributesValidator {

    private SuperHeroApi apiClient;

    public CardAttributesValidator(SuperHeroApi apiClient) {
        this.apiClient = apiClient;
    }

    public void validate(List<String> cards) throws SomePowerStatsWithoutValueException {
        Supplier<Stream<Powerstats>> streamSupplier  = () ->  cards.parallelStream().map(cardId -> {
            try {
                return apiClient.getPowerstats(cardId).orElseThrow(() -> new CannotFoundPowerStats(cardId));
            } catch(CannotFoundPowerStats e){
                return new Powerstats();
            }
        });
        final long countTrue = streamSupplier.get().filter(Powerstats::hasAllPowerStats).count();
        if  (countTrue < cards.size()) {
            throw new SomePowerStatsWithoutValueException();
        }
    }
}
