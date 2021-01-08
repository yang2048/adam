package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.common.base.model.SuperPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "系统权限菜单", description = "MenuEditDTO")
public class DictQueryDTO extends SuperPage implements Serializable {

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
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String label;

    /**
     * 字典类型 1:字典组 2:字典属性
     */
    @ApiModelProperty(value = "字典类型 1:字典组 2:字典属性")
    private String type;

    /**
     * 是否禁用 0:否 1:是
     */
    @ApiModelProperty(value = "是否禁用 0:否 1:是")
    private String disabled;

}
