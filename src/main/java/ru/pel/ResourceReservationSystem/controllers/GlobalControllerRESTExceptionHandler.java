package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pel.ResourceReservationSystem.exceptions.ExceptionBody;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerRESTExceptionHandler extends ResponseEntityExceptionHandler {

    private String getUrlFromWebRequest(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURL().toString();
    }

    @ExceptionHandler({NoSuchElementException.class,
            IllegalArgumentException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ExceptionBody(getUrlFromWebRequest(request), ex),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler({SQLException.class})
    protected ResponseEntity<Object> handleSQLExceptions(SQLException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ExceptionBody(getUrlFromWebRequest(request), ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<Object> handleExceptions(RuntimeException ex, WebRequest request) {
//        HttpHeaders headers = new HttpHeaders();
//        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
//        ExceptionBody body = new ExceptionBody(url, ex);
//        return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_FOUND, request);
//    }
}
