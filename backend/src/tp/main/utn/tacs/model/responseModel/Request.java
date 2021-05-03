package utn.tacs.model.responseModel;

public class Request {

    Attribute attribute;

    public Request(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
