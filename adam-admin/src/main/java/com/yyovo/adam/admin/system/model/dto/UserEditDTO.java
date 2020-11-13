package com.yyovo.adam.admin.system.model.dto;

import com.yyovo.adam.admin.system.model.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@ApiModel(value = "系统用户", description = "UserEditDTO")
public class UserEditDTO {
    
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "机构id 可同时属多部门")
    private Long deptId;

    @NotBlank(message = "用户账号不能为空")
//    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    @ApiModelProperty(value = "用户名")
    private String userAccount;

    @ApiModelProperty(value = "用户类型 0:管理员 1:普通会员 2:游客")
    private String userType;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1:男 2:女")
    private GenderEnum gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @NotNull(message = "用户手机不能为空")
//    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "手机号码格式错误")
    @ApiModelProperty(value = "手机")
    private String phone;

    @NotBlank(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态 0:否 1:是")
    private Integer disable;

}
