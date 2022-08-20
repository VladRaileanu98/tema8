package com.db.summerspring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class})
    public ModelAndView nullPointerExceptionHandler(Exception exception){
        ModelAndView modelAndView = new ModelAndView("error-message");
        modelAndView.addObject("errorMessage",
                "Controller Advice: "+exception.getMessage());
        return modelAndView;
    }
}
