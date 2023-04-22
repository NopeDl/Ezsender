package com.yeyeye.ezsender.exceptions;

import com.yeyeye.ezsender.enums.ResponseStatus;

/**
 * @author yeyeye
 * @Date 2023/4/22 14:47
 */
public class IllegalParamsException extends RuntimeException {
    private final String code;
    private final String msg;

    public IllegalParamsException(ResponseStatus status, String msg) {
        super(msg);
        this.msg = msg;
        this.code = status.getCode();
    }

    public IllegalParamsException(ResponseStatus status) {
        super(status.getContent());
        this.msg = status.getContent();
        this.code = status.getCode();
    }
}
