package utn.tacs.model.responseModel;

public class Request {

    Attribute attribute;

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
