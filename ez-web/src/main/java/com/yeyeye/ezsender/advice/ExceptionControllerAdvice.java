package com.yeyeye.ezsender.advice;

import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pojo.SendResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author yeyeye
 * @Date 2023/4/19 0:45
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SendResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS, e.getAllErrors().get(0).getDefaultMessage());
    }
}
