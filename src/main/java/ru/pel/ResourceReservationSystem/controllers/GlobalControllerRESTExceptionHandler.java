package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pel.ResourceReservationSystem.exceptions.NoSuchRoomException;

@ControllerAdvice
public class GlobalControllerRESTExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = NoSuchElementException.class)
////    @ResponseBody
//    public ErrorInfo handleSQLExceptions(HttpServletRequest request, Exception exception){
//        return new ErrorInfo(request.getRequestURL().toString(), exception);
//    }

    @ExceptionHandler(value = {NoSuchRoomException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), headers, HttpStatus.NOT_FOUND, request);
    }
}
