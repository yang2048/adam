package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.common.base.model.SuperPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleQueryDTO extends SuperPage implements Serializable {
    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 状态 0:否 1:是
     */
    @ApiModelProperty(value = "状态 0:否 1:是")
    private String disabled;
}
