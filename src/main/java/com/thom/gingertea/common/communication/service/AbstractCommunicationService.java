package com.thom.gingertea.common.communication.service;

import com.thom.gingertea.common.communication.dto.WebClientDto;
import com.thom.gingertea.config.properties.WebClientProperties;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public abstract class AbstractCommunicationService implements CommunicationService {

    private final WebClient webClient;
    private final WebClientProperties webClientProperties;
    private final WebClientProperties.ExternalProperties externalProperties;

    public AbstractCommunicationService(WebClient webClient, WebClientProperties webClientProperties, WebClientProperties.ExternalProperties externalProperties) {
        this.webClient = webClient.mutate().baseUrl(externalProperties.getBaseUrl()).build();
        this.webClientProperties = webClientProperties;
        this.externalProperties = externalProperties;
    }

    @Override
    public <T> T sendRequest(WebClientDto.Request<T> request) {
        return webClient
                .method(request.getHttpMethod())
                .uri(request.getUri())
                .body(BodyInserters.fromValue(request))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(request.getResponseType());
                    } else {
                        return clientResponse.createException()
                                .flatMap(throwable -> Mono.error(new RuntimeException(throwable)));
                    }
                })
                .block();
    }
}
