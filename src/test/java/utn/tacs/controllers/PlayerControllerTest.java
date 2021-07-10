package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.tacs.TacsApplication;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.sorting.Sort;

import java.util.ArrayList;


@SpringBootTest(classes = {TacsApplication.class})
class PlayerControllerTest extends RequestTestCase{

    @MockBean
    UsersRepository usersRepository;

    @Test
    void getAllPlayers() throws Exception {
        Mockito.when(usersRepository.findByName("", new Sort("name", "asc"))).thenReturn(new ArrayList<>());
        assertRequest("GET", "/api/players", 200);
    }

    @Test
    void getUsernameByID() {
    }
}