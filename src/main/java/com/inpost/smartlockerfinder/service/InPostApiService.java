package com.inpost.smartlockerfinder.service;

import com.inpost.smartlockerfinder.dto.InPostPointResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InPostApiService {

    private final RestClient restClient;
    private final String baseUrl;

    public InPostApiService(@Value("${inpost.api.base-url}") String baseUrl) {
        this.restClient = RestClient.create();
        this.baseUrl = baseUrl;
    }

    public InPostPointResponse fetchPoints() {
        return restClient
                .get()
                .uri(baseUrl + "/points")
                .retrieve()
                .body(InPostPointResponse.class);
    }
}