package com.thom.gingertea.common.exception;

import lombok.RequiredArgsConstructor;

public class BusinessException extends RuntimeException {

    private final CustomBusinessException customBusinessException;

    public BusinessException(CustomBusinessException customBusinessException) {
        super(customBusinessException.getMessage());
        this.customBusinessException = customBusinessException;
    }

}
