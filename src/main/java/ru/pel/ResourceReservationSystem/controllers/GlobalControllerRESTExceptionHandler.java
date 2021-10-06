package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.pel.ResourceReservationSystem.exceptions.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Controller
public class GlobalControllerRESTExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseBody
    public ErrorInfo handleSQLExceptions(HttpServletRequest request, Exception exception){
        return new ErrorInfo(request.getRequestURL().toString(), exception);
    }
}
