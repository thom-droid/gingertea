package com.thom.gingertea.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CustomBusinessExceptionCode {

    @Getter
    @RequiredArgsConstructor
    public enum User implements CustomBusinessException {
        DUPLICATED_USERNAME("-0001", "username is already being used."),
        NO_SUCH_USER("-0002", "no user found."),
        INVALID_INFORMATION("-0003","invalid id or password."),
        INVALID_PASSWORD("-0004", "password must be at least 8 characters long."),
        ;

        private final String code;
        private final String message;
    }
}
