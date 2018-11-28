package com.sonmat.file.common.exception;


import com.sonmat.file.common.exception.response.ErrorResponse;
import com.sonmat.file.common.exception.response.ServerErrorResponse;
import com.sonmat.file.common.exception.response.file.FileUploadErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    public RestControllerExceptionHandler() {
        super();
    }

    /**
     * 500 error
     *
     * @param ex
     * @return error message json
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity<ServerErrorResponse> handleInvalidRequest(Exception ex) {

        logger.error("System Exception", ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity(new ServerErrorResponse(ex.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    /**
     * application error
     *
     * @param ex
     * @return error message json
     */
    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInvalidRequestForApplication(ApiException ex) {

        logger.error("ApiException", ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity(ex.getErrorResponse(), headers, HttpStatus.SERVICE_UNAVAILABLE);
    }



    @ExceptionHandler({MultipartException.class,FileUploadBase.FileSizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<FileUploadErrorResponse> handleSizeExceededException(final WebRequest request, final MultipartException ex) {

        logger.error("handleSizeExceededException", ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity(FileUploadErrorResponse.createError(ex.getMessage()), headers, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}