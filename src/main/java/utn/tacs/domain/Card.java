package utn.tacs.domain;

import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.dto.deck.response.Attribute;

public class Card {
    CardId cardId;
    PowerStats powerstats;

    public Card(CardId value, Powerstats powerstats) throws Exception {
        if (powerstats == null)
            throw new Exception("No se pudo conseguir las stats de la carta " +
                    value.getId());
        this.cardId = value;
        this.powerstats = new PowerStats()
        .addAttribute(Attribute.COMBAT, powerstats.getCombat())
        .addAttribute(Attribute.SPEED, powerstats.getSpeed())
        .addAttribute(Attribute.INTELLIGENCE, powerstats.getIntelligence())
        .addAttribute(Attribute.POWER, powerstats.getPower())
        .addAttribute(Attribute.DURABILITY, powerstats.getDurability())
        .addAttribute(Attribute.STRENGTH, powerstats.getStrength());
    }

    public int getValueOf(Attribute attribute){
        return powerstats.getAttribute(attribute);
    }

    public CardId getCardId() {
        return cardId;
    }

    public void setCardId(CardId cardId) {
        this.cardId = cardId;
    }

    public PowerStats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(PowerStats powerstats) {
        this.powerstats = powerstats;
    }
}
