package com.github.crisdiazalv.billger.interfaces.rest.util;

import com.github.crisdiazalv.billger.domain.exception.AccountNotFoundException;
import com.github.crisdiazalv.billger.domain.exception.BillNotFoundException;
import com.github.crisdiazalv.billger.domain.exception.CategoryNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccountNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<Object> handleBadRequest(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
