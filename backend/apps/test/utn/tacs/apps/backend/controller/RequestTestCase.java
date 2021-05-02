package utn.tacs.apps.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class RequestTestCase {
    private String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ink4TUprbHhzOVg4Y3dGZEp5OVZHNiJ9.eyJpc3MiOiJodHRwczovL2Rldi1qeDhmeXN2cS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjAxMzBjYWM5ZGJkMWEwMDY4ZjA2YTdhIiwiYXVkIjpbImh0dHBzOi8vdGFjcy4yMDIxLmNvbSIsImh0dHBzOi8vZGV2LWp4OGZ5c3ZxLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2MTk5MDM5MDEsImV4cCI6MTYxOTk5MDMwMSwiYXpwIjoiSDhFWDAwRGVndE5ic3AyUGRVQ1p4dk51dTFSSTZ2ZFoiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.XmVJv3miGJw01yhmx87RGXEktBonrYflFiUt6hNr3NkRFO_DXyRLpxMormI99sTXGss9xmMrOqaKMVomfJv9bq89r1gm4BlU1mEfM-uFhVR6aZG8m-WlZATNr7jSt_yq2l-jjuyqQB0KL6fz9Q3DMLvixREPTEW0hTTX5oYnVb-VrmLspa6EhUBxSXkheSy4Bjg6aFzdYU5sZfZfDmqyHwYEDzAdatyg6d5AqfHDjV0-DtxEpp3MqzQeiDJtWIFTuLN_pPNwEe1Ls8-U6IldyTIvXa7qnLdGj68y4Fqilvg0ABSyjQOvPq2yoVjEsdUMPh9DU_Ot5_QrhQSlhKBGAw";
    public ObjectMapper mapper = new ObjectMapper();
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
        mockMvc
                .perform(request(HttpMethod.valueOf(method), endpoint)
                        .header("Authorization", "Bearer " + token)
                )
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
