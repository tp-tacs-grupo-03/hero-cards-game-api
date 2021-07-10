package utn.tacs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import utn.tacs.domain.repositories.UsersRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class RequestTestCase {
    private String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ink4TUprbHhzOVg4Y3dGZEp5OVZHNiJ9.eyJodHRwczpyb2xlcyI6WyJhZG1pbiIsInVzZXIiXSwiaHR0cHM6cGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzYwMzMwNTI5MDcwNzIyNGUwMTA5NjUxOGRkZDA4M2M4P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGZGUucG5nIiwiaHR0cHM6dXNlcm5hbWUiOiJkZXYiLCJpc3MiOiJodHRwczovL2Rldi1qeDhmeXN2cS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjAxMzBjYWM5ZGJkMWEwMDY4ZjA2YTdhIiwiYXVkIjpbImh0dHBzOi8vdGFjcy4yMDIxLmNvbSIsImh0dHBzOi8vZGV2LWp4OGZ5c3ZxLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2MjU4NTY5NzYsImV4cCI6MTYyNTk0MzM3NiwiYXpwIjoiSDhFWDAwRGVndE5ic3AyUGRVQ1p4dk51dTFSSTZ2ZFoiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwicGVybWlzc2lvbnMiOlsiY3JlYXRlOmRlY2tzIiwiY3JlYXRlOm1hdGNoZXMiLCJkZWxldGU6ZGVja3MiLCJyZWFkOmNhcmRzIiwicmVhZDpkZWNrcyIsInJlYWQ6bWF0Y2hlcyIsInJlYWQ6c3RhdHMiLCJ1cGRhdGU6ZGVja3MiLCJ1cGRhdGU6bWF0Y2hlcyJdfQ.ALGEsO8YiOJNRdUmgTlchhCXNABdhDaJzV7QFb17oNuwMLsvCXXCAtQ2Gf5PGoZz6YQKCnhoup06AlgxhAQLsloSVlpKbiyGTgt73gNzdWiXw_XsUCtTWIrpnfHR1oG4cNpa_JuU8k9r2BBn_Fh1Az_PuJERV6Om8VTlSuhpR-o5nrzORku_5CswIB_JWuPlbPmK-m6F8MzmjFSQGlMBTGUuuYfntwUTUkhjwfTRE0rVERWpzbe35KQJS0xOZPw0XUbfk-d6bMRXFq-fYaNXeJY03Oz5OVPIS2bisvybyNsQKKosdX6i20MrN8pK0raxbHL4hilizhns0h0_f1L3OQ";
    public ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private UsersRepository usersRepository;

    @Autowired
    private MockMvc mockMvc;

    protected void assertRequestWithBody(
            String method,
            String endpoint,
            String body,
            Integer expectedStatusCode
    ) throws Exception {
        mockMvc
                .perform(request(HttpMethod.valueOf(method), endpoint)
                        .header("Authorization", "Bearer " + token)
                        .content(body)
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(expectedStatusCode));
    }

    protected void assertRequest(
            String method,
            String endpoint,
            Integer expectedStatusCode
    ) throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method), endpoint).header("Authorization", "Bearer " + token))
                .andExpect(status().is(expectedStatusCode));
    }

    protected void assertRequestWithContent(String method,
                                            String endpoint,
                                            Integer expectedStatusCode,
                                            String expectedResponse
    ) throws Exception{
        ResultMatcher response = expectedResponse.isEmpty()
                ? content().string("")
                : content().json(expectedResponse);
        mockMvc
                .perform(request(HttpMethod.valueOf(method), endpoint)
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().is(expectedStatusCode)).andExpect(response);
    }

    protected void doRequest(
            String method,
            String endpoint,
            Integer expectedStatusCode,
            String expectedContent
    ) throws Exception {
        mockMvc
                .perform(request(HttpMethod.valueOf(method), endpoint)
                        .header("Authorization", "Bearer " + token)
                );
    }

    protected String doRequestWithBody(
            String method,
            String endpoint,
            String body,
            Integer expectedStatusCode
    ) throws Exception {
        return mockMvc
                .perform(request(HttpMethod.valueOf(method), endpoint)
                        .header("Authorization", "Bearer " + token)
                        .content(body)
                        .contentType(APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }


}
