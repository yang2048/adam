package com.yyovo.adam.admin.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "系统权限菜单", description = "MenuEditDTO")
public class DictEditDTO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典组id
     */
    @ApiModelProperty(value = "字典组id")
    private Long parentId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String name;

    /**
     * 字典类型 1:字典组 2:字典属性
     */
    @ApiModelProperty(value = "字典类型 1:字典组 2:字典属性")
    private Integer type;

    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String label;

    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    private String value;

    /**
     * 扩展
     */
    @ApiModelProperty(value = "扩展")
    private String extend;

    /**
     * 是否禁用 0:否 1:是
     */
    @ApiModelProperty(value = "是否禁用 0:否 1:是")
    private String disabled;

    /**
     * 排序 数字越大越靠前
     */
    @ApiModelProperty(value = "排序 数字越大越靠前")
    private Integer sort;

    /**
     * 备注 0:否 1:是
     */
    @ApiModelProperty(value = "备注 0:否 1:是")
    private String remark;
}
