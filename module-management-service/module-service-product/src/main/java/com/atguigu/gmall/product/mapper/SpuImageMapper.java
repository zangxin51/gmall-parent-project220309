package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName SpuImageMapper.java
 * @Description TODO
 * @createTime 2022年08月22日 21:29:00
 */
@Mapper
public interface SpuImageMapper extends BaseMapper<SpuImage> {

    void batchInsert(@Param("spuId") Long spuId,
                     @Param("spuImageList") List<SpuImage> spuImageList);

}
