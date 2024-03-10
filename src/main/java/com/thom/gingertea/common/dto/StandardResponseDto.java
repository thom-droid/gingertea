package com.thom.gingertea.common.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class StandardResponseDto<T> {

    private final String code;
    private final String message;
    private final T data;

    public static <T> StandardResponseDto<T> of(String code, String message, T data) {
        return new StandardResponseDto<>(code, message, data);
    }

    public static <T> StandardResponseDto<T> success() {
        return new StandardResponseDto<>(HttpStatus.OK.toString(), "success", null);
    }

    public static <T> StandardResponseDto<T> success(T data) {
        return new StandardResponseDto<>(HttpStatus.OK.toString(), "success", data);
    }

    public static <T> StandardResponseDto<T> fail(String code, String message) {
        return new StandardResponseDto<>(code, "fail", null);
    }

    public static <T> StandardResponseDto<T> fail(String code, String message, T data) {
        return new StandardResponseDto<>(code, "fail", data);
    }
}
