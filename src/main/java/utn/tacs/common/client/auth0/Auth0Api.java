package utn.tacs.common.client.auth0;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.tacs.common.client.ApiClient;
import utn.tacs.common.client.auth0.model.GetUserRequest;
import utn.tacs.common.client.auth0.model.Token;
import utn.tacs.common.client.auth0.model.User;
import utn.tacs.services.exceptions.CannotGetUser;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
@Scope("singleton")
public class Auth0Api extends ApiClient implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(Auth0Api.class);

    public Auth0Api() {}
    private String clientId;


    private String getClientId() {
        return clientId != null ? clientId: System.getenv("auth0_client_id");
    }
    private String clientSecret;


    private String getClientSecret() {
        return clientSecret != null ? clientSecret: System.getenv("auth0_client_secret");
    }
    private String audience;


    private String getAudience() {
        return audience != null ? audience: System.getenv("auth0_audience");
    }

    private String getBaseURL() {
        return "https://dev-jx8fysvq.us.auth0.com";
    }


    public Optional<Token> getToken() {
        final JSONObject body = new JSONObject();
        body.put("client_id", getClientId());
        body.put("client_secret", getClientSecret());
        body.put("audience", getAudience());
        body.put("grant_type", "client_credentials");
        final ResponseEntity<Token> result = runWithBody(url("/oauth/token"), Token.class, body);
        return Optional.ofNullable(result.getBody());
    }

    public Optional<User[]> getUsers(GetUserRequest req) throws CannotGetUser {
        final String sortQueryParam = req.getSortQueryParam();
        final String filterName = "*" + req.getFilterName() + "*";
        final String url = url("/api/v2/users?page=" + req.getPage() + "&" + sortQueryParam + "&q=nickname:" + filterName);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional<Token> token = getToken();
        if(token.isEmpty()) {
           throw new CannotGetUser("Cannot get token to get users");
        }
        headers.setBearerAuth(token.get().getAccess_token());
        final ResponseEntity<User[]> result;
        try {
            result = run(url, User[].class, headers);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new CannotGetUser("Cannot get users");
        }
        return Optional.ofNullable(result.getBody());
    }


    public Optional<User> getUserInfo(String id) throws CannotGetUser {
        final String url = url("/api/v2/users/" + id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional<Token> token = getToken();
        if(token.isEmpty()) {
            throw new CannotGetUser("Cannot get token to get users");
        }
        headers.setBearerAuth(token.get().getAccess_token());
        final ResponseEntity<User> result;
        try {
            result = run(url, User.class, headers);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new CannotGetUser("Cannot get users");
        }
        return Optional.ofNullable(result.getBody());
    }

    private String url(String path) {
        return getBaseURL() + path;
    }

    public Auth0Api setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public Auth0Api setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public Auth0Api setAudience(String audience) {
        this.audience = audience;
        return this;
    }
}
