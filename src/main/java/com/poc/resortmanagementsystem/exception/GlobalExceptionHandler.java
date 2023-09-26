package com.poc.resortmanagementsystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

    /*@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ModelAndView mav = new ModelAndView("error-page");
        mav.addObject("errorMessage", "Oops! This operation is not supported.");
        return mav;
    }*/
}

