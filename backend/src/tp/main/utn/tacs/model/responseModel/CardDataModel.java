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
}
