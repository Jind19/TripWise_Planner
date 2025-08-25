// external/TripProfileClient.java
package com.tripwise.tripplanner.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tripwise.tripplanner.util.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TripProfileClient {
    private final RestClient http;
    private final String base;

    public TripProfileClient(@Value("${triphub.base-url}") String hub,
                             @Value("${tripprofile.base-path:/tripprofile}") String basePath) {
        this.http = RestClient.create();
        this.base = hub + basePath;
    }

    public UserProfile me() {
        String token = SecurityUtils.currentTokenValue();
        return http.get()
                .uri(base + "/users/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(UserProfile.class);
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserProfile {
        private String id;
        private String email;
        private String displayName;
    }
}
