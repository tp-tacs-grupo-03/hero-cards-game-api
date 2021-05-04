package utn.tacs.matches;

import utn.tacs.model.responseModel.Attribute;

public class MatchModelRequest {

    Attribute attribute;

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
