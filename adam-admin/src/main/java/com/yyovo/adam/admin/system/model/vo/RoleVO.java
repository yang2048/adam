package com.yyovo.adam.admin.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "RoleVO对象", description = "系统角色信息")
public class RoleVO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
