package utn.tacs.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CardIdCannotFoundException extends Exception {

    public CardIdCannotFoundException(String cardId) {
        super(String.format("CardId cannot found %s", cardId));
    }
}
