package com.eScheduler.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAuthService {
    private static final String GOOGLE_TOKEN_URL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

    public String validateGoogleToken(String idToken) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(GOOGLE_TOKEN_URL + idToken, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            if (root.has("email")) {
                return root.get("email").asText();
            }

            throw new RuntimeException("Invalid token");

        } catch (Exception e) {
            throw new RuntimeException("Failed to validate token", e);
        }
    }
}
