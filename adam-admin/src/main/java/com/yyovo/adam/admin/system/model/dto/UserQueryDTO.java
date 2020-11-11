package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.common.base.model.SuperPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryDTO extends SuperPage {

    @ApiModelProperty(value = "用户名")
    private String userAccount;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1:男 2:女")
    private String gender;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态 0:否 1:是")
    private Integer disable;
}
