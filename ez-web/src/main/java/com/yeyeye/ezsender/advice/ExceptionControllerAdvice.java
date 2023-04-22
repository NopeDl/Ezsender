package com.yeyeye.ezsender.advice;

import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pojo.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

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
    @ResponseBody
    public SendResponse<?> exception(Exception e) {
         log.error(getStackTrace(e));
        return SendResponse.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public SendResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS, e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 获取错误的堆栈信息
     *
     * @param throwable
     * @return
     */
    private String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }

    }
}
