package com.lc.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ServiceException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
