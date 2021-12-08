package com.igortullio.hering.adapter.client;

import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.domain.User;
import com.igortullio.hering.core.port.ClientSendPort;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient implements ClientSendPort {

    private final RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(User user, Integration integration) {
        String url = integration.getDestination();

        restTemplate.exchange(url, HttpMethod.POST, null, String.class);
    }

}
