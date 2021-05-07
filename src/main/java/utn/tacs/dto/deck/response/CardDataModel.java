package utn.tacs.dto.deck.response;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class CardDataModel {

    @NonNull
    private float height;
    @NonNull
    private float weight;
    @NonNull
    private float intelligence;
    @NonNull
    private float speed;
    @NonNull
    private float power;
    @NonNull
    private float combat;
    @NonNull
    private float strength;

    public CardDataModel(float height, float weight, float intelligence, float speed, float power, float combat, float strength) {
        this.height = height;
        this.weight = weight;
        this.intelligence = intelligence;
        this.speed = speed;
        this.power = power;
        this.combat = combat;
        this.strength = strength;
    }
}
