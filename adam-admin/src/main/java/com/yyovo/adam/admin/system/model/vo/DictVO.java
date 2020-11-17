package com.yyovo.adam.admin.system.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DictVO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID")
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
     * 是否默认 0:否 1:是
     */
    @ApiModelProperty(value = "是否默认 0:否 1:是")
    private Integer checked;

    /**
     * 是否禁用 0:否 1:是
     */
    @ApiModelProperty(value = "是否禁用 0:否 1:是")
    private Integer disable;

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

    /**
     * 子集合
     */
    @ApiModelProperty(value = "子集合")
    private List<?> subList;
}
