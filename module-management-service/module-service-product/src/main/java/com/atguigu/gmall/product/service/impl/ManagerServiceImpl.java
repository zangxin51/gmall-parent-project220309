package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.api.ManagerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName ManagerServiceImpl.java
 * @Description TODO
 * @createTime 2022年08月19日 12:27:00
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;

    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;

    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseCategory1> getCategory1List() {
        return baseCategory1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2List(Long category1Id) {
        return baseCategory2Mapper.selectList(new QueryWrapper<BaseCategory2>().eq("category1_id", category1Id));
    }

    @Override
    public List<BaseCategory3> getCategory3List(Long category2Id) {
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>().eq("category2_id", category2Id));
    }

    @Override
    public List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        //获取baseAttrInfo 对象的id值
        Long baseAttrInfoId = baseAttrInfo.getId();
        //保存
        if (baseAttrInfoId == null) {
            // 先保存平台属
            baseAttrInfoMapper.insert(baseAttrInfo);
            // 获取平台属性数据记录的主键值
            baseAttrInfoId = baseAttrInfo.getId();
        } else {
            //修改
            //先修改baseAttrInfo
            baseAttrInfoMapper.updateById(baseAttrInfo);
            //再重置baseAttrValue
            //删除原来的属性对应的属性值List
            baseAttrValueMapper.delete(
                    new QueryWrapper<BaseAttrValue>()
                            .eq("attr_id",baseAttrInfoId));
        }
        //保存
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        baseAttrValueMapper.insertBatch(baseAttrInfoId, attrValueList);
    }

    @Override
    public List<BaseAttrValue> getBaseAttrValueList(Long attrId) {
        return baseAttrValueMapper.selectList(
                new QueryWrapper<BaseAttrValue>()
                        .eq("attr_id", attrId));
    }

}
