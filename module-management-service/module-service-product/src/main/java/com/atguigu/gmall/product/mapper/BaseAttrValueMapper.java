package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName BaseAttrValueMapper.java
 * @Description TODO
 * @createTime 2022年08月19日 18:38:00
 */
@Mapper
public interface BaseAttrValueMapper extends BaseMapper<BaseAttrValue> {

    void insertBatch(Long baseInfoId, List<BaseAttrValue> attrValueList);

}
