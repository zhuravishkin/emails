package com.zhuravishkin.springbootoracledbplsqlprocedure.handler;

import com.zhuravishkin.springbootoracledbplsqlprocedure.model.RespondingServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RespondingServerException.class)
    public ResponseEntity<Object> handleResponseException(final RespondingServerException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode())).body(e.toResponse());
    }
}
