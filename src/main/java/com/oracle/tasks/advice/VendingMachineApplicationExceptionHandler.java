package com.oracle.tasks.advice;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception Handler Advice Class
 */
@RestControllerAdvice
public class VendingMachineApplicationExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineApplicationExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExactChangeNotAvailableException.class)
    public Map<String, String> handleExactChangeNotAvailableException(ExactChangeNotAvailableException e) {
        LOGGER.error("Exact Change Not Available Exception");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("exception class "+e.getClass(), e.getMessage());
        return errorMap;
    }

    /**
     * Vending Machine Application Exception Interceptor method to catch any unhandled exceptions
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception exception) {
        LOGGER.error("Vending Machine Application Exception Interceptor called");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("exception class "+exception.getClass(), exception.getMessage());
        return errorMap;
    }
}
