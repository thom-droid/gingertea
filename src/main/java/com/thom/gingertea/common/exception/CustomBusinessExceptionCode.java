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

    @Getter
    @RequiredArgsConstructor
    public enum Supplier implements CustomBusinessException {
        NO_SUCH_SUPPLIER("-0005", "no supplier found."),
        DUPLICATED_SUPPLIER_NAME("-0006", "supplier name is already being used."),
        NOT_QUALIFIED_BUSINESS_NUMBER("-0007", "business number is not qualified."),
        ;

        private final String code;
        private final String message;
    }
}
