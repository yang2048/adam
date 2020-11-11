package com.yyovo.adam.admin.system.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.handler.ApiRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(3, "未知");


    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;

    @Override
    public String toString() {
        return desc;
    }

    /**
     * 枚举转换
     *
     * @param value 描述desc
     * @return Enum
     */
    public static GenderEnum convert(String value) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.desc.equals(value)) {
                return gender;
            }
        }
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.code == Integer.parseInt(value)) {
                return gender;
            }
        }
        throw new ApiRuntimeException(ErrorType.ENUM_NOT_VALID);
    }

}
