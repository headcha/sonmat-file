package com.sonmat.file.common.exception;

import com.sonmat.file.common.exception.response.ErrorResponse;
import lombok.Getter;

public class ApiException extends RuntimeException {
    @Getter
    private final ErrorResponse errorResponse;

    public ApiException(ErrorResponse errorResponse , Throwable throwable) {
        super(throwable);
        this.errorResponse = errorResponse;
    }

    public ApiException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
