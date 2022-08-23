package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.api.ManagerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

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

    @Override
    public IPage<SpuInfo> getPage(Long pageNum, Long pageSize, Long category3Id) {
        IPage<SpuInfo> spuInfoIPage = new Page<>(pageNum, pageSize);
        return spuInfoMapper.selectPage(spuInfoIPage,
                new QueryWrapper<SpuInfo>().eq("category3_id", category3Id));
    }

    @Override
    public List<BaseSaleAttr> getAllBaseSaleAttr() {
        return baseSaleAttrMapper.selectList(null);
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        // 1、保存 SpuInfo 基本数据
        spuInfoMapper.insert(spuInfo);
        // 2、保存 List<SpuImage>
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        Long spuInfoId = spuInfo.getId();
        spuImageMapper.batchInsert(spuInfoId, spuImageList);
        // 3、保存 SpuSaleAttr
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {

            spuSaleAttr.setSpuId(spuInfoId);
            spuSaleAttrMapper.insert(spuSaleAttr);
            // 4、保存 SpuSaleAttrValue
            // [1]准备数据
            Long baseSaleAttrId = spuSaleAttr.getBaseSaleAttrId();
            String saleAttrName = spuSaleAttr.getSaleAttrName();
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            //[2]执行保存
            spuSaleAttrValueMapper.batchInsert(spuInfoId, baseSaleAttrId, saleAttrName,spuSaleAttrValueList);

        }



    }

}
