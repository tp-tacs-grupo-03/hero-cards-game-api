package utn.tacs.controllers.exceptions;

public class SomePowerStatsWithoutValueException extends Exception {

    public SomePowerStatsWithoutValueException() {
        super("There is one or more cards without attributes values");
    }
}
