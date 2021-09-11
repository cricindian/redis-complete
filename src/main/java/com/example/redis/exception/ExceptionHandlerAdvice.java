package com.example.redis.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice{

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleNotFoundException(
            NotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    public ResponseEntity<ErrorResponse> buildResponse(
            Exception ex, HttpStatus status, Integer code) {
        return new ResponseEntity(
                ErrorResponse.builder().error_code(code).error_message(ex.getLocalizedMessage()).build(),
                status);
    }
}

@Builder
@Data
class ErrorResponse{
    Integer error_code;
    String error_message;
}
