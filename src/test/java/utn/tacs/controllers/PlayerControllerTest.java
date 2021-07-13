package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import utn.tacs.TacsApplication;
import utn.tacs.common.security.Authenticator;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.UsersRepository;

import java.util.ArrayList;

@SpringBootTest(classes = {TacsApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final public class PlayerControllerTest extends RequestTestCase{

    @MockBean
    UsersRepository usersRepository;
    @MockBean
    Authenticator authenticator;

    @Test
    void getAllPlayers() throws Exception {
        Mockito.when(authenticator.getHost()).thenReturn("test");
        Mockito.when(usersRepository.findByName( PageRequest.of(0, 100), "") ).thenReturn(new ArrayList<>());
        assertRequest("GET", "/api/players", 200);
    }

    @Test
    void getUsernameByID() throws Exception {
        Mockito.when(authenticator.getHost()).thenReturn("test");
        Mockito.when(usersRepository.find("1234")).thenReturn(new PlayerStats("1234"));
        assertRequest("GET", "/api/players/1234", 200);
    }
}