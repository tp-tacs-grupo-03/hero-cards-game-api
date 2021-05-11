package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.deck.response.Attribute;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PowerStats {

    private final Map<Attribute, Integer> attributes = new HashMap<>();

    public PowerStats addAttribute(Attribute attribute, Integer value){
        attributes.put(attribute, value);
        return this;
    }

    public Integer getAttribute(Attribute attribute){
        return attributes.get(attribute);
    }
}
