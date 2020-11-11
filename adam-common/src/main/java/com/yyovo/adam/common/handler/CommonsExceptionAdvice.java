package com.yyovo.adam.common.handler;

import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/10/7 18:03
 * @Description: 异常处理
 */
@Slf4j
@RestControllerAdvice
public class CommonsExceptionAdvice {

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public Result BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.failed(message);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.failed(message);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.failed(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> apiRuntimeException(HttpMessageNotReadableException e) {
        log.warn("【数据格式异常】 => {}", e.getMessage());
        return Result.failed("【数据格式异常】 => %s", e.getMessage());
    }

    @ExceptionHandler(ApiRuntimeException.class)
    public Result<?> apiRuntimeException(ApiRuntimeException e) {
        log.info("code: {} msg: {} ", e.getErrorType().getCode(), e.getErrorType().getMsg());
        return Result.failed(e.getErrorType());
    }

    /**
     * 自定义异常捕获
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result commonsRuntimeException(Exception e) {
        if (e instanceof DuplicateKeyException) {
            return Result.failed(ErrorType.DUPLICATE_PRIMARY_KEY);
        }
        log.warn("msg:{} ==> ", e.getMessage(), e);
        return Result.failed(ErrorType.SYSTEM_ERROR);
    }

}
