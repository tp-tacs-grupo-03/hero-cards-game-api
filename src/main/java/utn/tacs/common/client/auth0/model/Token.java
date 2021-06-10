package utn.tacs.common.client.auth0.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Token implements Serializable {

    private String access_token;
    private String scope;
    private Long expires_in;
    private String token_type;

}
