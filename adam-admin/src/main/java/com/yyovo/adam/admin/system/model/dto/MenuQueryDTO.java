package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.common.base.model.SuperPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MenuQueryDTO extends SuperPage implements Serializable {
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Long parentId;

    /**
     * 权限代码
     */
    @ApiModelProperty(value = "权限代码")
    private String code;

    /**
     * 菜单项标题
     */
    @ApiModelProperty(value = "菜单项标题")
    private String name;

    /**
     * 是否可见 0:否 1:是
     */
    @ApiModelProperty(value = "是否可见 0:否 1:是")
    private Boolean visible;

    /**
     * 禁用状态 0:否 1:是
     */
    @ApiModelProperty(value = "禁用状态 0:否 1:是")
    private Boolean disabled;
}
