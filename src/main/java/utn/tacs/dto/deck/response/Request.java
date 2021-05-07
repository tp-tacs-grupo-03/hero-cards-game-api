package utn.tacs.dto.deck.response;

public class Request {

    private Attribute attribute;

    public Request(){}

    public Request(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
