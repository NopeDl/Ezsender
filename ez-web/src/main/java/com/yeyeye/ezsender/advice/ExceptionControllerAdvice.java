package com.yeyeye.ezsender.advice;

import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pojo.SendResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public SendResponse<?> exception(Exception e) {
        log.error("未知异常，堆栈信息：", e);
        return SendResponse.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SendResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.info("参数异常：", e);
        return SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS, e.getAllErrors().get(0).getDefaultMessage());
    }
}
