package utn.tacs.common.client.superHeroAPI.clientApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    private String url;

    public String getUrl() {
        return url;
    }
}
