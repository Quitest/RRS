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
import ru.pel.ResourceReservationSystem.exceptions.NoSuchRoomException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerRESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String url = ((ServletWebRequest)request).getRequest().getRequestURL().toString();
        ExceptionBody body = new ExceptionBody(url,ex);
        return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_FOUND, request);
    }
}
