package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Attr;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.domain.exceptions.NotFoundCharacter;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.Attribute;

import java.util.Map;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor
class Card {

    private CardId cardId;
    private PowerStats powerstats;

    Card(CardId value, Powerstats powerstats) throws Exception {
        if (powerstats == null) {
            throw new Exception("No se pudo conseguir las stats de la carta " +
                    value.getId());
        }
        this.cardId = value;
        this.powerstats = new PowerStats()
        .addAttribute(Attribute.COMBAT, powerstats.getCombat())
        .addAttribute(Attribute.SPEED, powerstats.getSpeed())
        .addAttribute(Attribute.INTELLIGENCE, powerstats.getIntelligence())
        .addAttribute(Attribute.POWER, powerstats.getPower())
        .addAttribute(Attribute.DURABILITY, powerstats.getDurability())
        .addAttribute(Attribute.STRENGTH, powerstats.getStrength());
    }

    int getValueOf(Attribute attribute){
        return powerstats.getAttribute(attribute);
    }

    public Attribute getBestAttribute(){
        Attribute bestAttr = null;
        int bestValue = 0;
        for (Map.Entry<Attribute, Integer> element : powerstats.getAttributes().entrySet()) {
            if(element.getValue() > bestValue){
                bestAttr = element.getKey();
                bestValue = element.getValue();
            }
        }
        return bestAttr;
    }

}
