//package ru.pel.ResourceReservationSystem.controllers;
//
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLException;
//import java.util.NoSuchElementException;
//
///**
// * Глобальный класс обработчик исключений. Создан для изучения одного из способов обработки исключений.
// * Источник: <a href=https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc>Exception Handling in Spring MVC</a>
// *
// * @deprecated Заменен на SimpleMappingExceptionResolver в AppConfig.class
// */
//@Deprecated(forRemoval = true, since = "07.10.2021")
////@ControllerAdvice
//public class GlobalControllerExceptionHandler {
//    public static final String DEFAULT_ERROR_VIEW = "/error";
//
//    // Convert a predefined exception to an HTTP Status code
//    @ResponseStatus(value = HttpStatus.CONFLICT,
//            reason = "Data integrity violation")  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void conflict() {
//        // Nothing to do
//    }
//
//    // Specify name of a specific view that will be used to display the error:
//    @ExceptionHandler({SQLException.class, DataAccessException.class})
//    public String databaseError() {
//        // Nothing to do.  Returns the logical view name of an error page, passed
//        // to the view-resolver(s) in usual way.
//        // Note that the exception is NOT available to this view (it is not added
//        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//        // below.
//        return "databaseError";
//    }
//
//    //If you want to have a default handler for any exception, there is a slight wrinkle. You need to ensure annotated exceptions are handled by the framework. The code looks like this:
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        // If the exception is annotated with @ResponseStatus rethrow it and let
//        // the framework handle it - like the OrderNotFoundException example
//        // at the start of this post.
//        // AnnotationUtils is a Spring Framework utility class.
//        //WTF не совсем понятно это условие. Зачем такие проверки?
//        if (AnnotationUtils.findAnnotation
//                (e.getClass(), ResponseStatus.class) != null)
//            throw e;
//
//        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
//
//    // Total control - setup a model and return the view name yourself. Or
//    // consider subclassing ExceptionHandlerExceptionResolver (see below).
//    @ExceptionHandler(NoSuchElementException.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
////        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("/error");
//        return mav;
//    }
//}
