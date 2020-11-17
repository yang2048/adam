package com.yyovo.adam.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyovo.adam.admin.system.model.dto.MenuEditDTO;
import com.yyovo.adam.admin.system.model.dto.MenuQueryDTO;
import com.yyovo.adam.admin.system.model.dto.RoleEditDTO;
import com.yyovo.adam.admin.system.model.enums.SystemError;
import com.yyovo.adam.admin.system.model.pojo.SysDept;
import com.yyovo.adam.admin.system.model.pojo.SysMenu;
import com.yyovo.adam.admin.system.model.vo.DeptVO;
import com.yyovo.adam.admin.system.model.vo.MenuVO;
import com.yyovo.adam.admin.system.service.ISysMenuService;
import com.yyovo.adam.common.base.controller.SuperController;
import com.yyovo.adam.common.base.model.Result;
import com.yyovo.adam.common.utils.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统权限表 前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends SuperController {

    private final ISysMenuService sysMenuService;

    public SysMenuController(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public Result<?> save(@RequestBody @Valid MenuEditDTO menuEditDTO) {
        int count = sysMenuService.count(Wrappers.<SysMenu>lambdaQuery()
                .or().eq(SysMenu::getCode, menuEditDTO.getCode()));
        if (count > 0) {
            return Result.failed(SystemError.USER_REGISTERED);
        }
        SysMenu menu = ConvertUtil.copyToDest(menuEditDTO, SysMenu.class);
        sysMenuService.save(menu);
        return Result.success(ConvertUtil.copyToDest(menu, MenuVO.class));
    }

    @PatchMapping
    @ApiOperation(value = "修改")
    public Result<?> update(@RequestBody @Valid MenuEditDTO menuEditDTO) {
        SysMenu menu = ConvertUtil.copyToDest(menuEditDTO, SysMenu.class);
        sysMenuService.updateById(menu);
        return Result.success(ConvertUtil.copyToDest(menu, MenuVO.class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取")
    public Result<?> get(@PathVariable("id") String id) {
        SysMenu menu = sysMenuService.getById(id);
        return Result.success(ConvertUtil.copyToDest(menu, MenuVO.class));
    }

    @GetMapping
    @ApiOperation(value = "获取列表")
    public Result<?> list(MenuQueryDTO queryDTO) {
        LambdaQueryWrapper<SysMenu> ew = Wrappers.lambdaQuery();

        if (queryDTO.isPagination()) {
            Page<SysMenu> page = new Page<>(queryDTO.getPage(), queryDTO.getLimit());
            page.addOrder(OrderItem.asc(queryDTO.getColumn()));
            if (!queryDTO.isAsc()) {
                page.addOrder(OrderItem.desc(queryDTO.getColumn()));
            }
            page = sysMenuService.page(page, ew);
            return Result.success(ConvertUtil.copyToPage(page, MenuVO.class));
        }
        List<SysMenu> sysDeptList = sysMenuService.list(ew);
        return Result.success(ConvertUtil.copyToList(sysDeptList, DeptVO.class));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    public Result<?> remove(@PathVariable("id") String id) {
        sysMenuService.removeById(id);
        return Result.success();
    }

    @PostMapping("remove")
    @ApiOperation(value = "批量删除")
    public Result<?> BatchRemove(@RequestBody List<MenuEditDTO> editDTOS) {
        List<Long> idList = editDTOS.stream().map(MenuEditDTO::getId).collect(Collectors.toList());
        sysMenuService.removeByIds(idList);
        return Result.success();
    }
}
