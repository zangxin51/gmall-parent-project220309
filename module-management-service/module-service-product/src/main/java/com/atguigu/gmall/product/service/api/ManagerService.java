package com.atguigu.gmall.product.service.api;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName ManagerService.java
 * @Description TODO
 * @createTime 2022年08月19日 12:26:00
 */
public interface ManagerService {
    List<BaseCategory1> getCategory1List();

    List<BaseCategory2> getCategory2List(Long category1Id);

    List<BaseCategory3> getCategory3List(Long category2Id);

    List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    List<BaseAttrValue> getBaseAttrValueList(Long attrId);

    IPage<SpuInfo> getPage(Long pageNum, Long pageSize, Long category3Id);

    List<BaseSaleAttr> getAllBaseSaleAttr();

    void saveSpuInfo(SpuInfo spuInfo);

}
