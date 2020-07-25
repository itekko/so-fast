package com.sofast.common.exception;

import com.sofast.common.enums.EnumErrorCode;

import java.io.Serializable;

/**
 * @author ekko
 * @desc 全局异常类
 */
public class SoFastException extends RuntimeException implements Serializable {

    private EnumErrorCode enumErrorCode;

    public SoFastException(Throwable cause, EnumErrorCode enumErrorCode) {
        super(enumErrorCode.getMsg(), cause);
        this.enumErrorCode = enumErrorCode;
    }

    public SoFastException(EnumErrorCode enumErrorCode) {
        super(enumErrorCode.getMsg());
        this.enumErrorCode = enumErrorCode;
    }

    public EnumErrorCode getEnumErrorCode() {
        return enumErrorCode;
    }
}
