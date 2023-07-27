package com.oracle.tasks.advice;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for the VendingMachineApplicationExceptionHandler Class
 */
class VendingMachineApplicationExceptionHandlerTest {

    private VendingMachineApplicationExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new VendingMachineApplicationExceptionHandler();
    }

    @Test
    void testHandleExactChangeNotAvailableException() {
        ExactChangeNotAvailableException exception = new ExactChangeNotAvailableException("Exact change not available");
        Map<String, String> result = exceptionHandler.handleExactChangeNotAvailableException(exception);

        assertNotNull(result);
        assertTrue(result.containsKey("exception class " + exception.getClass()));
        assertEquals(exception.getMessage(), result.get("exception class " + exception.getClass()));
    }

    @Test
    void testHandleException() {
        String errorMessage = "Something went wrong";
        Exception exception = new Exception(errorMessage);
        Map<String, String> result = exceptionHandler.handleException(exception);

        assertNotNull(result);
        assertTrue(result.containsKey("exception class " + exception.getClass()));
        assertEquals(errorMessage, result.get("exception class " + exception.getClass()));
    }
}
