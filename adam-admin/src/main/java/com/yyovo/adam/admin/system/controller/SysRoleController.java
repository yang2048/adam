package com.yyovo.adam.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyovo.adam.admin.system.model.dto.RoleEditDTO;
import com.yyovo.adam.admin.system.model.dto.RoleQueryDTO;
import com.yyovo.adam.admin.system.model.enums.SystemError;
import com.yyovo.adam.admin.system.model.pojo.SysRole;
import com.yyovo.adam.admin.system.model.vo.RoleVO;
import com.yyovo.adam.admin.system.service.ISysRoleService;
import com.yyovo.adam.common.base.controller.SuperController;
import com.yyovo.adam.common.base.model.Result;
import com.yyovo.adam.common.utils.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends SuperController {

    private final ISysRoleService sysRoleService;

    public SysRoleController(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public Result<?> save(@RequestBody @Valid RoleEditDTO roleEditDTO) {
        int count = sysRoleService.count(Wrappers.<SysRole>lambdaQuery()
                .or().eq(SysRole::getCode, roleEditDTO.getCode()));
        if (count > 0) {
            return Result.failed(SystemError.USER_REGISTERED);
        }
        SysRole role = ConvertUtil.copyToDest(roleEditDTO, SysRole.class);
        sysRoleService.save(role);
        return Result.success(ConvertUtil.copyToDest(role, RoleVO.class));
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "修改")
    public Result<?> update(@PathVariable("id") Long id, @RequestBody RoleEditDTO roleEditDTO) {
        SysRole role = ConvertUtil.copyToDest(roleEditDTO, SysRole.class);
        role.setId(id);
        sysRoleService.updateById(role);
        return Result.success(ConvertUtil.copyToDest(role, RoleVO.class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取")
    public Result<?> get(@PathVariable("id") String id) {
        SysRole role = sysRoleService.getById(id);
        return Result.success(ConvertUtil.copyToDest(role, RoleVO.class));
    }

    @GetMapping
    @ApiOperation(value = "获取列表")
    public Result<?> page(RoleQueryDTO roleQueryDTO) {
        LambdaQueryWrapper<SysRole> ew = Wrappers.lambdaQuery();

        Page<SysRole> page = new Page<>(roleQueryDTO.getCurrent(), roleQueryDTO.getSize());
        page.addOrder(OrderItem.asc("sort"));
        page = sysRoleService.page(page, ew);
        return Result.success(ConvertUtil.copyToPage(page, RoleVO.class));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    public Result<?> remove(@PathVariable("id") String id) {
        sysRoleService.removeById(id);
        return Result.success();
    }

    @PostMapping("remove")
    @ApiOperation(value = "批量删除")
    public Result<?> BatchRemove(@RequestParam Long[] idList) {
        sysRoleService.removeByIds(Arrays.asList(idList));
        return Result.success();
    }
}
