package com.yyovo.adam.admin.system.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yyovo.adam.common.base.model.SuperModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统机构表
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("y_sys_dept")
@ApiModel(value = "SysDept对象", description = "系统机构表")
public class SysDept extends SuperModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父部门id
     */
    @ApiModelProperty(value = "父部门id")
    private Long parentId;

    /**
     * 部门集合体
     */
    @ApiModelProperty(value = "部门集合体")
    private String assembly;

    /**
     * 机构代号
     */
    @ApiModelProperty(value = "机构代号")
    private String code;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String logo;

    /**
     * 排序 数字越大越靠前
     */
    @ApiModelProperty(value = "排序 数字越大越靠前")
    private Integer sort;

    /**
     * 状态 0:否 1:是
     */
    @ApiModelProperty(value = "状态 0:否 1:是")
    private Boolean disable;

}
