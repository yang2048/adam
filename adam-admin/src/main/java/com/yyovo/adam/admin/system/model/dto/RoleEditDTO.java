package com.yyovo.adam.admin.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "系统角色", description = "RoleEditDTO")
public class RoleEditDTO implements Serializable {
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
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String remark;

    /**
     * 排序 数字越大越靠前
     */
    @ApiModelProperty(value = "排序 数字越大越靠前")
    private Integer sort;

    /**
     * 数据权限范围类型 0:自定义 1:全部数据 2:本部门及下属部门 3:仅本部门
     */
    @ApiModelProperty(value = "数据权限范围类型 0:自定义 1:全部数据 2:本部门及下属部门 3:仅本部门")
    private Integer scopeType;

    /**
     * 数据权限范围
     */
    @ApiModelProperty(value = "数据权限范围")
    private String dataScope;

    /**
     * 状态 0:否 1:是
     */
    @ApiModelProperty(value = "状态 0:否 1:是")
    private Boolean disable;
}
