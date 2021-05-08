package utn.tacs.domain;

import utn.tacs.dto.deck.response.Attribute;

import java.util.HashMap;
import java.util.Map;

public class PowerStats {
    private final Map<Attribute, Integer> attributes = new HashMap<>();

    public PowerStats addAttribute(Attribute attribute, Integer value){
        attributes.put(attribute, value);
        return this;
    }

    public Integer getAttribute(Attribute attribute){
        return attributes.get(attribute);
    }

    public Map<Attribute, Integer> getAttributes() {
        return attributes;
    }
}
