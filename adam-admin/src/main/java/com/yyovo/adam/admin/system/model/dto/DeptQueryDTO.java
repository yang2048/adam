package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.common.base.model.SuperPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptQueryDTO extends SuperPage {
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
     * 状态 0:否 1:是
     */
    @ApiModelProperty(value = "状态 0:否 1:是")
    private String disabled;
}
