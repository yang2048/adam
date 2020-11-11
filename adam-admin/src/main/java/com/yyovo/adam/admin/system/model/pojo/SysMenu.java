package com.yyovo.adam.admin.system.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yyovo.adam.common.base.model.SuperModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("y_sys_menu")
@ApiModel(value = "SysMenu对象", description = "系统权限表")
public class SysMenu extends SuperModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Long parentId;

    /**
     * 排序 数字越大越靠前
     */
    @ApiModelProperty(value = "排序 数字越大越靠前")
    private Integer sort;

    /**
     * 权限代码
     */
    @ApiModelProperty(value = "权限代码")
    private String code;

    /**
     * 菜单类型 1:菜单 2:按钮
     */
    @ApiModelProperty(value = "菜单类型 1:菜单 2:按钮")
    private Boolean type;

    /**
     * 菜单项标题
     */
    @ApiModelProperty(value = "菜单项标题")
    private String name;

    /**
     * 图标 className
     */
    @ApiModelProperty(value = "图标 className")
    private String icon;

    /**
     * 菜单项标识 可以是路由path
     */
    @ApiModelProperty(value = "菜单项标识 可以是路由path")
    private String path;

    /**
     * 徽标配置
     */
    @ApiModelProperty(value = "徽标配置")
    private String badge;

    /**
     * 菜单打开页面方式 route 路由打开、href 链接打开、blank 新窗口打开链接、event 触发事件
     */
    @ApiModelProperty(value = "菜单打开页面方式 route 路由打开、href 链接打开、blank 新窗口打开链接、event 触发事件")
    private String target;

    /**
     * 是否分组 0:否 1:是
     */
    @ApiModelProperty(value = "是否分组 0:否 1:是")
    private Boolean batch;

    /**
     * 分组标题
     */
    @ApiModelProperty(value = "分组标题")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;

    /**
     * 是否可见 0:否 1:是
     */
    @ApiModelProperty(value = "是否可见 0:否 1:是")
    private String visible;

    /**
     * 禁用状态 0:否 1:是
     */
    @ApiModelProperty(value = "禁用状态 0:否 1:是")
    private Boolean disabled;

}
