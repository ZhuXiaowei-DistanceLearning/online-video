package com.manaz.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author zxw
 * @date 2020/7/19 16:37
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handlerException(BaseException e) {
        return ResponseEntity.status(e.getExceptionEnums().getCode()).body(e.getExceptionEnums());
    }
}
