package com.cbz.myusercenterafter.exception;

import com.cbz.myusercenterafter.common.BaseResponse;
import com.cbz.myusercenterafter.common.ErrorCode;
import com.cbz.myusercenterafter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author cbz
 * @version 1.0
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse businessException(BusinessException e) {
        log.error("BusinessException", e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(),"");
    }
}
