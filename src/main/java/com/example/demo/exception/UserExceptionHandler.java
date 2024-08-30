package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(value = ServiceException.class)
    public ErrorResponse handleServiceException(ServiceException e) {
        logger.debug(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handException(Exception e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse("Internal server error");
    }
}
