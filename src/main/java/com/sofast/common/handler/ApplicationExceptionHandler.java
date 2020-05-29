package com.sofast.common.handler;


import com.sofast.common.enums.EnumErrorCode;
import com.sofast.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @author ekko
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {


    private final static Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    /**
     * 参数校验异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> illegalArgumentException(IllegalArgumentException e) {
        if(log.isDebugEnabled()){
            log.debug("全局异常处理:IllegalArgumentException[{}]:{}",e.getClass(),e.getMessage());
        }
        return Result.build(EnumErrorCode.illegalArgument.getCode(), e.getMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> Exception(Exception e) {
        if(log.isDebugEnabled()){
            log.debug("全局异常处理:Exception[{}]:{}",e.getClass(),e.getMessage());
        }
        return Result.build(EnumErrorCode.unknowFail.getCode(), e.getMessage());
    }




}
