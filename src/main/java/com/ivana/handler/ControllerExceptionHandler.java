package com.ivana.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //will intercept all @Controller classes
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) //
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        //logging
        logger.error("request url: {}, exception: {}", request.getRequestURL(), e.getMessage());

        //we don't want to intercept all exceptions, so we let some pass
        //if we gave @ResponseStatus to the this type of exception, then we let it pass.
        // springboot will go to the right error page.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }


        //send data
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);

        //go to
        mv.setViewName("error/error");
        return mv;

    }
}
