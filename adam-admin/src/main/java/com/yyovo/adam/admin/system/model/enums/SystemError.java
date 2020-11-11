package com.yyovo.adam.admin.system.model.enums;

import com.yyovo.adam.common.base.model.IErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemError implements IErrorType {

    USER_REGISTERED(100001, "该数据已存在"),
    SYSTEM_ERROR(100002, "是");

    /**
     * 错误类型码
     */
    private final int code;
    /**
     * 错误类型描述信息
     */
    private final String msg;
}
