package com.sonmat.file.common.exception;

import com.sonmat.file.common.exception.response.ErrorResponse;
import lombok.Getter;

@Getter
public class ApiErrorMessage implements ErrorResponse {
    private int code;
    private String message;

    public static ApiErrorMessage create(int code, String message) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.code = code;
        apiErrorMessage.message = message;
        return apiErrorMessage;
    }
}