package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pel.ResourceReservationSystem.exceptions.ExceptionBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error errorBody = processFieldErrors(ex.getBindingResult().getAllErrors());
        return handleExceptionInternal(ex,
                errorBody,
                headers,
                HttpStatus.BAD_REQUEST,
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

    private Error processFieldErrors(List<ObjectError> objectErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (var objectError : objectErrors) {
            error.addErrorMessage(objectError);
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private final List<String> errorMessagesList = new ArrayList<>();

        public Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public void addErrorMessage(ObjectError objectError) {
            if (objectError instanceof FieldError fieldError) {
                errorMessagesList.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessagesList.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
            }
        }

        public List<String> getErrorMessagesList() {
            return errorMessagesList;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }
}
