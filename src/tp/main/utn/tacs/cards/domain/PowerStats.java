package utn.tacs.cards.domain;

import utn.tacs.model.responseModel.Attribute;

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
