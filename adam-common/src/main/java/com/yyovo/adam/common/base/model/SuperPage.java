package com.yyovo.adam.common.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SuperPage implements Serializable {
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数")
    protected long limit = 10;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    protected long page = 1;
}
