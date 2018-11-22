package com.sonmat.file.common.exception.response.file;

import com.sonmat.file.common.exception.response.ErrorResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "파일 에러 코드" , value = "FileErrorResponseDto")
@Getter
public class FileUploadErrorResponse implements ErrorResponse {
    @ApiModelProperty(notes = "not_allow_mime_type , 허용할수 없는 mime 타입 <br> file_size_over , 파일 업로드 사이즈 초과 <br> , file_size_is_zero , 파일 사이즈 0"  , required = true)
    private FileUploadErrorCode code;
    @ApiModelProperty(notes = "디버깅 메세지" , example = "파일 사이즈 제한")
    private String message;

    public static FileUploadErrorResponse createNotAllowMimeType(String message) {
        FileUploadErrorResponse response = new FileUploadErrorResponse();
        response.code = FileUploadErrorCode.not_allow_mime_type;
        response.message = message;
        return response;
    }

    public static FileUploadErrorResponse createSizeZero(String message) {
        FileUploadErrorResponse response = new FileUploadErrorResponse();
        response.code = FileUploadErrorCode.file_size_is_zero;
        response.message = message;
        return response;
    }

    public static FileUploadErrorResponse createSizeOver(String message) {
        FileUploadErrorResponse response = new FileUploadErrorResponse();
        response.code = FileUploadErrorCode.file_size_over;
        response.message = message;
        return response;
    }
}
