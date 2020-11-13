package com.yyovo.adam.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyovo.adam.admin.system.model.dto.DeptEditDTO;
import com.yyovo.adam.admin.system.model.dto.DeptQueryDTO;
import com.yyovo.adam.admin.system.model.enums.SystemError;
import com.yyovo.adam.admin.system.model.pojo.SysDept;
import com.yyovo.adam.admin.system.model.vo.DeptVO;
import com.yyovo.adam.admin.system.service.ISysDeptService;
import com.yyovo.adam.common.base.controller.SuperController;
import com.yyovo.adam.common.base.model.Result;
import com.yyovo.adam.common.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 系统机构表 前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-10
 */
@Api(value = "系统机构")
@RestController
@RequestMapping("/sysDept")
public class SysDeptController extends SuperController {

    private final ISysDeptService sysDeptService;
    public SysDeptController(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public Result<?> save(@RequestBody @Valid DeptEditDTO deptEditDTO) {
        int count = sysDeptService.count(Wrappers.<SysDept>lambdaQuery()
                .or().eq(SysDept::getCode, deptEditDTO.getCode()));
        if (count > 0) {
            return Result.failed(SystemError.USER_REGISTERED);
        }
        SysDept dept = ConvertUtil.copyToDest(deptEditDTO, SysDept.class);
        sysDeptService.save(dept);
        return Result.success(ConvertUtil.copyToDest(dept, DeptVO.class));
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "修改")
    public Result<?> update(@PathVariable("id") Long id, @RequestBody DeptEditDTO deptEditDTO) {
        SysDept dept = ConvertUtil.copyToDest(deptEditDTO, SysDept.class);
        dept.setId(id);
        sysDeptService.updateById(dept);
        return Result.success(ConvertUtil.copyToDest(dept, DeptVO.class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取")
    public Result<?> get(@PathVariable("id") String id) {
        SysDept dept = sysDeptService.getById(id);
        return Result.success(ConvertUtil.copyToDest(dept, DeptVO.class));
    }

    @GetMapping
    @ApiOperation(value = "获取列表")
    public Result<?> page(DeptQueryDTO deptQueryDTO) {
        LambdaQueryWrapper<SysDept> ew = Wrappers.lambdaQuery();

        Page<SysDept> page = new Page<>(deptQueryDTO.getPage(), deptQueryDTO.getLimit());
        page.addOrder(OrderItem.asc("sort"));
        page = sysDeptService.page(page, ew);
        return Result.success(ConvertUtil.copyToPage(page, DeptVO.class));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    public Result<?> remove(@PathVariable("id") String id) {
        sysDeptService.removeById(id);
        return Result.success();
    }

    @PostMapping("remove")
    @ApiOperation(value = "批量删除")
    public Result<?> BatchRemove(@RequestParam Long[] idList) {
        sysDeptService.removeByIds(Arrays.asList(idList));
        return Result.success();
    }

    @GetMapping("listDept")
    @ApiOperation(value = "部门列表")
    public Result<?> ListDept(DeptQueryDTO deptQueryDTO){
        LambdaQueryWrapper<SysDept> ew = Wrappers.lambdaQuery();
        ew.eq(SysDept::getDisable, false);
        ew.eq(SysDept::getParentId, deptQueryDTO.getParentId());
        List<SysDept> sysDeptList = sysDeptService.list(ew);
//        List<DeptVO> deptVOList = ConvertUtil.copyToList(sysDeptList, DeptVO.class);
        return Result.success(ConvertUtil.copyToList(sysDeptList, DeptVO.class));
    }
}

