package utn.tacs.dto.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.PlayerStats;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private String nickname;
    private String user_id;
    private String picture;


    public static User toUser(PlayerStats playerStats){
        User user = new User();
        user.setNickname(playerStats.getName());
        user.setPicture(playerStats.getImage());
        user.setUser_id(playerStats.getId());
        return user;
    }
}