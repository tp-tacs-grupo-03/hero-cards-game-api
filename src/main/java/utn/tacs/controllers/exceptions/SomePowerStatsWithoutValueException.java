package utn.tacs.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SomePowerStatsWithoutValueException extends Exception {

    public SomePowerStatsWithoutValueException(String cardId) {
        super(String.format("There is some or more attribute without value for cardId %s", cardId));
    }
}
