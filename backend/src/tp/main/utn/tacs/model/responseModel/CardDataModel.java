package utn.tacs.model.responseModel;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class CardDataModel {
    @NonNull
    public float height;
    @NonNull
    public float weight;
    @NonNull
    public float intelligence;
    @NonNull
    public float speed;
    @NonNull
    public float power;
    @NonNull
    public float combat;
    @NonNull
    public float strength;

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
