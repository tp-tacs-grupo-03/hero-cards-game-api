package utn.tacs.common.client.auth0;

import java.util.HashMap;
import java.util.Map;

public class Auth0Token {

    static Auth0Token instance;
    Map<String, String> map;

    private Auth0Token() {
        map = new HashMap<>();
    }

    public static Auth0Token getInstance() {
        if (instance == null )
            instance = new Auth0Token();

        return instance;
    }

    public String getToken(){
        return map.get("token");
    }

    public void setToken(String token){
        map.put("token", token);
    }
}
