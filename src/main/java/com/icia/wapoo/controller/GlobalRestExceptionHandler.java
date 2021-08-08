package com.icia.wapoo.controller;

import com.icia.wapoo.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result internalServerError(Exception e) {
        Result result = new Result(500, e.getMessage(), "인터셉터 오류처리");
        return result;
    }
}
