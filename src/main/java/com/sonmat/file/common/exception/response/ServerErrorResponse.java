package com.sonmat.file.common.exception.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "System Error" , value = "ServerErrorResponseDto")
@Getter
public class ServerErrorResponse implements ErrorResponse {

    @ApiModelProperty(notes = "에러 메세지 (개발자를 위한)")
    protected String message;
    public ServerErrorResponse(String message) {
        this.message = message;
    }
}
