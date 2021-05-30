package utn.tacs.controllers.exceptions;

public class CannotFoundPowerStats extends RuntimeException {

    public CannotFoundPowerStats(String id) {
        super(String.format("cannot found powerstats for id %s", id));
    }
}
