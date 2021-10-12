package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pel.ResourceReservationSystem.exceptions.ExceptionBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
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
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
        // FIXME: 12.10.2021 ex.getBindingResult().getFieldErrors() возвращает 0. Почему?
        Error errorBody = processFieldErrors(ex.getObjectName(),ex.getBindingResult().getFieldErrors());
        return handleExceptionInternal(ex,
                errorBody,
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        BindingResult bindingResult = exception.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        return processFieldErrors(bindingResult.getObjectName(), fieldErrors);
//    }

    @ExceptionHandler({SQLException.class})
    protected ResponseEntity<Object> handleSQLExceptions(SQLException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ExceptionBody(getUrlFromWebRequest(request), ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    private Error processFieldErrors(String objectName, List<FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (var fieldError : fieldErrors) {
            error.addFieldError(objectName, fieldError.getField(), fieldError.getDefaultMessage());
            fieldError.getDefaultMessage();
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private final List<FieldError> fieldErrors = new ArrayList<>();

        public Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public void addFieldError(String objectName, String path, String message) {
            FieldError fieldError = new FieldError(objectName, path, message);
            fieldErrors.add(fieldError);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }


//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<Object> handleExceptions(RuntimeException ex, WebRequest request) {
//        HttpHeaders headers = new HttpHeaders();
//        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
//        ExceptionBody body = new ExceptionBody(url, ex);
//        return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_FOUND, request);
//    }
}
