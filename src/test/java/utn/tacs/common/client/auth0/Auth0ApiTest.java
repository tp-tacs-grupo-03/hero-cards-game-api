package utn.tacs.common.client.auth0;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import utn.tacs.common.client.auth0.exceptions.CannotFoundToken;
import utn.tacs.common.client.auth0.exceptions.CannotFoundUsers;
import utn.tacs.common.client.auth0.model.GetUserRequest;
import utn.tacs.common.client.auth0.model.Token;
import utn.tacs.common.client.auth0.model.User;
import utn.tacs.services.exceptions.CannotGetUser;
import utn.tacs.sorting.exceptions.SortingException;

class Auth0ApiTest {

    @Test
    void getToken() throws CannotFoundToken {
        final Auth0Api client = new Auth0Api().setClientId("hmiOl6U2PyonXcjGX0VVr3qCTAe1TCmh")
                .setClientSecret("JF0ZF571Jyau0-qrZlSdDqTjbCRPzjQl0QRcW3Pr8syvuWJEyYnRY5ZighMK2RCP")
                .setAudience("https://dev-jx8fysvq.us.auth0.com/api/v2/");
        final Token response = client.getToken().orElseThrow(()-> new CannotFoundToken("1"));
        Assert.notNull(response, "cannot get token");
    }

    @Test
    void getUsers() throws SortingException, CannotGetUser, CannotFoundUsers {
        final Auth0Api client = new Auth0Api().setClientId("hmiOl6U2PyonXcjGX0VVr3qCTAe1TCmh")
                .setClientSecret("JF0ZF571Jyau0-qrZlSdDqTjbCRPzjQl0QRcW3Pr8syvuWJEyYnRY5ZighMK2RCP")
                .setAudience("https://dev-jx8fysvq.us.auth0.com/api/v2/");
        final User[] response = client.getUsers(new GetUserRequest(1, "name", "asc")).orElseThrow(() -> new CannotFoundUsers("1"));
        Assert.notNull(response, "cannot get users");
    }
}
