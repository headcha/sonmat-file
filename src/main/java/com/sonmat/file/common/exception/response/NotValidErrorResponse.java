package com.sonmat.file.common.exception.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "NotValid error" , value = "NotValidErrorResponseDto")
@Getter
public class NotValidErrorResponse {
    @ApiModelProperty(notes = "오류 코드" , example = "value_required" , required = true)
    private String code;

    NotValidErrorResponse(){}

    public static NotValidErrorResponse create(String code) {
        NotValidErrorResponse apiErrorMessage = new NotValidErrorResponse();
        apiErrorMessage.code = code;
        return apiErrorMessage;
    }
}
