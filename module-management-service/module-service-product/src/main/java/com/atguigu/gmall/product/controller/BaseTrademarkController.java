package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.api.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName BaseTrademarkController.java
 * @Description TODO
 * @createTime 2022年08月22日 18:27:00
 */
@Api(tags = "品牌接口")
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;

    @GetMapping("/baseTrademark/{page}/{limit}")
    @ApiOperation(value = "分页查询")
    public Result<IPage<BaseTrademark>> getPage(@ApiParam(name = "page", value = "当前页", required = true) @PathVariable Long page,
                                                @ApiParam(name = "limit", value = "每页数据容量", required = true) @PathVariable Long limit) {
        IPage<BaseTrademark> page1 = new Page<BaseTrademark>(page, limit);
        IPage<BaseTrademark> trademarkIPage = baseTrademarkService.page(page1, null);

        return Result.ok(trademarkIPage);
    }

    @PostMapping("/baseTrademark/save")
    @ApiOperation(value = "添加品牌")
    public Result baseTrademarkSave(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    @GetMapping("/baseTrademark/remove/{id}")
    @ApiOperation(value = "删除品牌")
    public Result baseTrademarkRemove(@PathVariable Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/baseTrademark/update")
    @ApiOperation(value = "修改品牌")
    public Result baseTrademarkUpdate(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    @GetMapping("/baseTrademark/get/{id}")
    @ApiOperation(value = "根据id获取品牌")
    public Result<BaseTrademark> getById(@PathVariable Long id) {
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    @GetMapping("/baseTrademark/getTrademarkList")
    @ApiOperation(value = "获取品牌属性")
    public Result<List<BaseTrademark>> getTrademarkList() {
        return Result.ok(baseTrademarkService.list(null));
    }
}
