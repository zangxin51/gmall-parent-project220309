package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.api.ManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName BaseManagerController.java
 * @Description TODO
 * @createTime 2022年08月19日 12:31:00
 */
@Api(tags = "商品属性接口")
@RestController
@RequestMapping("/admin/product")
public class BaseManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {

        Result<List<BaseCategory1>> result;
        try {
            //调用Service方法获取数据
            List<BaseCategory1> category1List = managerService.getCategory1List();
            //把查询的数据封装到Result中.
            result = Result.ok(category1List);
        } catch (Exception e) {
            e.printStackTrace();
            //再发生异常时,也可以返回Result类型数据
            //fail的错误码(code)可以被前端读取,来判断服务是否成功
            result = Result.fail();
            result.message(e.getMessage());
        }
        return result;

    }

    @GetMapping("/getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable Long category1Id) {

        Result<List<BaseCategory2>> result = null;
        try {
            List<BaseCategory2> baseCategory2List = managerService.getCategory2List(category1Id);
            result = Result.ok(baseCategory2List);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.fail();
            result.message(e.getMessage());
        }
        return result;
    }

    /**
     * @param category2Id 二级分类id
     * @return Result List BaseCategory2 对象
    * @ throws
    * @ title 根据二级分类id查询3级分类信息
    * @ description
    * @ author cqs
    * @ updateTime 2 0 2 2 / 8 / 1 9 1 8 : 2 2
     */
    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable Long category2Id) {

        Result<List<BaseCategory3>> result = null;
        try {
            List<BaseCategory3> baseCategory3List = managerService.getCategory3List(category2Id);
            result = Result.ok(baseCategory3List);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.fail();
            result.message(e.getMessage());
        }
        return result;
    }

    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> getAttrInfoList(
            @PathVariable Long category1Id,
            @PathVariable Long category2Id,
            @PathVariable Long category3Id
    ) {
        Result<List<BaseAttrInfo>> result = null;
        try {
            List<BaseAttrInfo> baseAttrInfoList = managerService.getBaseAttrInfoList(category1Id, category2Id, category3Id);
            result = Result.ok(baseAttrInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.fail();
            result.message(e.getMessage());
        }
        return result;
    }

    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {

        Result result = null;
        try {
            managerService.saveAttrInfo(baseAttrInfo);
            result = Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.fail();
            result.message(e.getMessage());
        }
        return result;
    }

    @GetMapping("/getAttrValueList/{attrId}")
    public Result<List<BaseAttrValue>> getAttrValueList(@PathVariable Long attrId) {

        Result<List<BaseAttrValue>> result = null;
        try {
            List<BaseAttrValue> baseAttrValueList = managerService.getBaseAttrValueList(attrId);
            result = Result.ok(baseAttrValueList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.fail();
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
