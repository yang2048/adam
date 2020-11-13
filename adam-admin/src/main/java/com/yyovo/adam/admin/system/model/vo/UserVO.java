package com.yyovo.adam.admin.system.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyovo.adam.admin.system.model.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "SysUserVO对象", description = "系统用户信息")
public class UserVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "机构id 可同时属多部门")
    private Long deptId;

    @ApiModelProperty(value = "用户名")
    private String userAccount;

    @ApiModelProperty(value = "用户类型 0:管理员 1:普通会员 2:游客")
    private String userType;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1:男 2:女")
    private GenderEnum gender;

    @JSONField(format="yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "注册ip")
    private String registerIp;

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "最后登录ip")
    private String lastLoginIp;

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;
}
