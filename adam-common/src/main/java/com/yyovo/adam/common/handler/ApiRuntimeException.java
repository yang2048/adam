package com.yyovo.adam.common.handler;

import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.base.model.IErrorType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApiRuntimeException extends RuntimeException implements Serializable {

    private IErrorType errorType;

    public ApiRuntimeException() {
        this.errorType = ErrorType.SYSTEM_ERROR;
    }

    public ApiRuntimeException(IErrorType errorType) {
        this.errorType = errorType;
    }

    public ApiRuntimeException(IErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ApiRuntimeException(IErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
