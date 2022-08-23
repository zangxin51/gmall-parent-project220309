package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.api.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName SpuMannagerController.java
 * @Description TODO
 * @createTime 2022年08月22日 21:08:00
 */
@RestController
@RequestMapping("/admin/product")
@Api(tags = "standard product unit 管理接口")
public class SpuManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/baseSaleAttrList")
    @ApiOperation(value = "查询所有基本销售属性")
    public Result<List<BaseSaleAttr>> getAllBaseSaleAtt() {
        return Result.ok(managerService.getAllBaseSaleAttr());
    }

    //还没写完
//    @GetMapping("/spuSaleAttrList/{spuId}")
//    @ApiOperation(value = "根据SPU id获取销售属性")
//    public Result<List<SpuSaleAttr>> getSpuSaleAttrList(@PathVariable Long spuId) {
////        managerService.getSpuSaleAttrListById(spuId);
//        return Result.ok();
//    }

    @PostMapping("/saveSpuInfo")
    @ApiOperation(value = "保存spu")
    public Result<SpuInfo> saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        managerService.saveSpuInfo(spuInfo);
        return Result.ok();
    }
}
