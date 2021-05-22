package utn.tacs.domain.exceptions;

public class NotFoundCharacter extends RuntimeException {

    public NotFoundCharacter(String id) {
        super(String.format("cannot found character id %s", id));
    }
}
