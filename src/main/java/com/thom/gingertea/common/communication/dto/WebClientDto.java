package com.thom.gingertea.common.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class WebClientDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Request<T> {
        private String uri;
        private Object body;
        private HttpMethod httpMethod;
        private Class<T> responseType;
        @Builder.Default
        private final List<String> attributes = new ArrayList<>();
    }

    @Data
    public static class Response {
        private HttpStatus httpStatus;
        private Object body;

    }

}
