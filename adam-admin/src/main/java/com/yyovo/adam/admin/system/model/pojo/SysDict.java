package com.yyovo.adam.admin.system.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yyovo.adam.common.base.model.SuperModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("y_sys_dict")
@ApiModel(value="SysDict对象", description="系统字典表")
public class SysDict extends SuperModel {

    private static final long serialVersionUID = 1L;

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
    private String type;

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
