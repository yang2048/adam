package com.yyovo.adam.admin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyovo.adam.admin.system.model.dto.DictEditDTO;
import com.yyovo.adam.admin.system.model.dto.DictQueryDTO;
import com.yyovo.adam.admin.system.model.dto.MenuEditDTO;
import com.yyovo.adam.admin.system.model.enums.SystemError;
import com.yyovo.adam.admin.system.model.pojo.SysDept;
import com.yyovo.adam.admin.system.model.pojo.SysDict;
import com.yyovo.adam.admin.system.model.pojo.SysUser;
import com.yyovo.adam.admin.system.model.vo.DeptVO;
import com.yyovo.adam.admin.system.model.vo.DictVO;
import com.yyovo.adam.admin.system.model.vo.UserVO;
import com.yyovo.adam.admin.system.service.ISysDictService;
import com.yyovo.adam.common.base.controller.SuperController;
import com.yyovo.adam.common.base.model.Result;
import com.yyovo.adam.common.utils.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统字典表 前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/admin.system/sys-dict")
public class SysDictController extends SuperController {

    private final ISysDictService sysDictService;

    public SysDictController(ISysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public Result<?> save(@RequestBody @Valid DictEditDTO editDTO) {
        int count = sysDictService.count(Wrappers.<SysDict>lambdaQuery()
                .or().eq(SysDict::getName, editDTO.getName()));
        if (count > 0) {
            return Result.failed(SystemError.USER_REGISTERED);
        }
        SysDict dict = ConvertUtil.copyToDest(editDTO, SysDict.class);
        sysDictService.save(dict);
        return Result.success(ConvertUtil.copyToDest(dict, DictVO.class));
    }

    @PatchMapping
    @ApiOperation(value = "修改")
    public Result<?> update(@RequestBody @Valid DictEditDTO editDTO) {
        SysDict dict = ConvertUtil.copyToDest(editDTO, SysDict.class);
        sysDictService.updateById(dict);
        return Result.success(ConvertUtil.copyToDest(dict, DictVO.class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取")
    public Result<?> get(@PathVariable("id") String id) {
        SysDict dict = sysDictService.getById(id);
        return Result.success(ConvertUtil.copyToDest(dict, DictVO.class));
    }

    @GetMapping
    @ApiOperation(value = "获取列表")
    public Result<?> list(DictQueryDTO queryDTO) {
        LambdaQueryWrapper<SysDict> ew = Wrappers.lambdaQuery();
        ew.eq(SysDict::getType, queryDTO.getType());
        if (queryDTO.isPagination()) {
            Page<SysDict> page = new Page<>(queryDTO.getPage(), queryDTO.getLimit());
            page.addOrder(OrderItem.asc("sort"));
            page = sysDictService.page(page, ew);
            return Result.success(ConvertUtil.copyToPage(page, DictVO.class));
        }
        List<SysDict> sysDictList = sysDictService.list(ew);
        return Result.success(ConvertUtil.copyToList(sysDictList, DictVO.class));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    public Result<?> remove(@PathVariable("id") String id) {
        sysDictService.removeById(id);
        return Result.success();
    }

    @PostMapping("remove")
    @ApiOperation(value = "批量删除")
    public Result<?> BatchRemove(@RequestBody List<DictEditDTO> editDTOS) {
        List<Long> idList = editDTOS.stream().map(DictEditDTO::getId).collect(Collectors.toList());
        sysDictService.removeByIds(idList);
        return Result.success();
    }
}
