package com.yyovo.adam.common.base.enums;

import com.yyovo.adam.common.base.model.IErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType implements IErrorType {

    SYSTEM_ERROR(-1, "系统异常"),
    SYSTEM_BUSY(1, "系统繁忙,请稍候再试"),
    ARGUMENT_NOT_VALID(2, "请求参数校验不通过"),
    ENUM_NOT_VALID(3, "枚举对象错误"),
    SUBMIT_REPEAT(4, "操作频繁,请稍候再试"),
    DUPLICATE_PRIMARY_KEY(5, "当前数据已录入,请更换再试"),
    SYSTEM_CONVERT(6, "数据转换错误"),

    GATEWAY_NOT_FOUND_SERVICE(7, "服务未找到"),
    GATEWAY_ERROR(8, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(9, "网关超时");

    /**
     * 错误类型码
     */
    private final int code;
    /**
     * 错误类型描述信息
     */
    private final String msg;
}
