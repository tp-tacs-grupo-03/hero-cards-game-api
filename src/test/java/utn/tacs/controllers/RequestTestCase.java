package utn.tacs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class RequestTestCase {
    private String token = "";
    public ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    public RequestTestCase() {
        getToken();
    }

    private void getToken(){
        try {
            HttpResponse<String> stringHttpResponse = Unirest.post("https://dev-jx8fysvq.us.auth0.com/oauth/token")
                    .header("content-type", "application/json")
                    .body("{\"client_id\":\"smXhNQvNPYBjbkh4NAwAHLvd8faz4bLP\",\"client_secret\":\"q7OsrJWHEJZEBpZihmBLbE6hletU_iEmQ80erD4N13TwxRqGOLLILieXo_aIZOx0\",\"audience\":\"https://tacs.2021.com\",\"grant_type\":\"client_credentials\"}")
                    .asString();

            String a = Arrays.asList(stringHttpResponse.getBody().split(",")).get(0).substring(2);
            Map<String, String> properties = Splitter.on(",").withKeyValueSeparator(":").split(a);
            token = properties.get("access_token\"");
            token = token.substring(1, token.length() - 1);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

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
