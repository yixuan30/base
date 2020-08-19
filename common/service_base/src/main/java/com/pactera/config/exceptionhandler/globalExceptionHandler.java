package com.pactera.config.exceptionhandler;

import com.pactera.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class globalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error (Exception e){
        e.printStackTrace();
        return R.error();
    }


    @ExceptionHandler(pacteraException.class)
    @ResponseBody
    public  R error(pacteraException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
