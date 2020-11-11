package com.yyovo.adam.admin.system.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yyovo.adam.admin.system.model.enums.GenderEnum;
import com.yyovo.adam.common.base.model.SuperModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("y_sys_user")
@ApiModel(value = "SysUser对象", description = "系统用户表")
public class SysUser extends SuperModel {

    private static final long serialVersionUID = 1L;

    /**
     * 机构id 可同时属多部门
     */
    @ApiModelProperty(value = "机构id 可同时属多部门")
    private Long deptId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userAccount;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 暗号
     */
    @ApiModelProperty(value = "暗号")
    private String cipher;

    /**
     * 用户类型 0:管理员 1:普通会员 2:游客
     */
    @ApiModelProperty(value = "用户类型 0:管理员 1:普通会员 2:游客")
    private String userType;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 性别 1:男 2:女 3:未知
     */
    @ApiModelProperty(value = "性别 1:男 2:女 3:未知")
    private GenderEnum gender;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 注册ip
     */
    @ApiModelProperty(value = "注册ip")
    private String registerIp;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;

    /**
     * 最后登录ip
     */
    @ApiModelProperty(value = "最后登录ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 状态 0:否 1:是
     */
    @ApiModelProperty(value = "状态 0:否 1:是")
    private Boolean disable;

}
