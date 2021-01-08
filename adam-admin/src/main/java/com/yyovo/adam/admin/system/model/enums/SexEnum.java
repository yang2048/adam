package com.yyovo.adam.admin.system.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.handler.ApiRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {
    MALE("1", "男"),
    FEMALE("2", "女"),
    UNKNOWN("0", "未知");

    @EnumValue
//    @JsonValue
    private final String code;
    private final String desc;

    @Override
    public String toString() {
        return code;
    }

    public static SexEnum convert(String code) {
        try {
            for (SexEnum em : SexEnum.values()) {
                if (em.code.equals(code)) {
                    return em;
                }
            }
        } catch (Exception e) {
            throw new ApiRuntimeException(ErrorType.ENUM_NOT_VALID);
        }
        return null;
    }
}
