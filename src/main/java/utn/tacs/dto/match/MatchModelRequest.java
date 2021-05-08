package utn.tacs.dto.match;

import utn.tacs.dto.deck.response.Attribute;

public class MatchModelRequest {

    private Attribute attribute;

    public MatchModelRequest(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
