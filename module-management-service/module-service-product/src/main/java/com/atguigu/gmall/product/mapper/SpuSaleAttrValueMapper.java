package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName SpuSaleAttrValueMapper.java
 * @Description TODO
 * @createTime 2022年08月22日 21:57:00
 */
@Mapper
public interface SpuSaleAttrValueMapper extends BaseMapper<SpuSaleAttrValue> {

    void batchInsert(@Param("spuInfoId") Long spuInfoId,
                     @Param("baseSaleAttrId") Long baseSaleAttrId,
                     @Param("saleAttrName") String saleAttrName,
                     @Param("spuSaleAttrValueList")List<SpuSaleAttrValue> spuSaleAttrValueList);
}
