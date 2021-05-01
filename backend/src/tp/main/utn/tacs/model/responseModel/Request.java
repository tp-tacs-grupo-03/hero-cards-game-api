package utn.tacs.model.responseModel;

public class Request {

    PlayerStatusEnum playerStatusEnum;
    Attribute attribute;

    public Request(PlayerStatusEnum playerStatusEnum, Attribute attribute) {
        this.playerStatusEnum = playerStatusEnum;
        this.attribute = attribute;
    }

    public PlayerStatusEnum getPlayerStatusEnum() {
        return playerStatusEnum;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
