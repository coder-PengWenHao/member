package com.coderpwh.member.infrastructure.persistence.repository.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderpwh.member.common.database.PageTransformUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.coderpwh.member.application.command.OrderOrderExtraInfoQuery;
import com.coderpwh.member.infrastructure.persistence.converter.OrderOrderExtraInfoConverter;
import com.coderpwh.member.domain.model.OrderOrderExtraInfoRepository;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import com.coderpwh.member.infrastructure.persistence.mapper.OrderOrderExtraInfoMapper;
import com.coderpwh.member.infrastructure.persistence.entity.OrderOrderExtraInfoDO;
import com.coderpwh.member.domain.model.OrderOrderExtraInfo;
import com.coderpwh.member.common.database.PageUtils;

/**
 * <p>
 * 仓储实现类
 * </p>
 *
 * @author coderpwh
 * @since 2023-05-30
 */
@Service
public class OrderOrderExtraInfoRepositoryImpl extends ServiceImpl<OrderOrderExtraInfoMapper, OrderOrderExtraInfoDO> implements OrderOrderExtraInfoRepository {


    @Resource
    private OrderOrderExtraInfoConverter orderOrderExtraInfoConverter;


    @Override
    public PageUtils queryPage(OrderOrderExtraInfoQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<OrderOrderExtraInfoDO> doList = this.list(Wrappers.<OrderOrderExtraInfoDO>lambdaQuery());

        PageUtils page = PageTransformUtil.transform(doList);
        List<OrderOrderExtraInfo> pageList = orderOrderExtraInfoConverter.toEntity(doList);
        page.setList(pageList);
        return page;
    }


    @Override
    public List<OrderOrderExtraInfo> queryList(OrderOrderExtraInfoQuery query) {
        List<OrderOrderExtraInfoDO> doList = this.list(Wrappers.<OrderOrderExtraInfoDO>lambdaQuery());
        List<OrderOrderExtraInfo> list = orderOrderExtraInfoConverter.toEntity(doList);
        return list;
    }

    @Override
    public boolean saveBatch(List<OrderOrderExtraInfo> list) {
        List<OrderOrderExtraInfoDO> saveList = orderOrderExtraInfoConverter.toDTO(list);
        return super.saveBatch(saveList);
    }

    @Override
    public boolean save(OrderOrderExtraInfo orderOrderExtraInfo) {
        OrderOrderExtraInfoDO saveDO = orderOrderExtraInfoConverter.toDTO(orderOrderExtraInfo);
        return super.save(saveDO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(OrderOrderExtraInfo orderOrderExtraInfo) {
        OrderOrderExtraInfoDO updateDO = orderOrderExtraInfoConverter.toDTO(orderOrderExtraInfo);
        return super.updateById(updateDO);
    }

    @Override
    public OrderOrderExtraInfo getById(Integer id) {
        OrderOrderExtraInfoDO entityDO = super.getById(id);
        return orderOrderExtraInfoConverter.toEntity(entityDO);
    }

    @Override
    public List<OrderOrderExtraInfo> getByIds(List<Integer> ids) {
        List<OrderOrderExtraInfoDO> entityList = this.list(Wrappers.<OrderOrderExtraInfoDO>lambdaQuery().in(OrderOrderExtraInfoDO::getId, ids));
        return orderOrderExtraInfoConverter.toEntity(entityList);
    }


    /***
     * 通过订单号与key值查询
     * @param orderNumber
     * @param refundUrlKey
     * @return
     */
    @Override
    public OrderOrderExtraInfo selectByOrderNumberAndKey(String orderNumber, String refundUrlKey) {
        OrderOrderExtraInfoDO orderOrderExtraInfoDO = baseMapper.selectByOrderNumberAndKey(orderNumber, refundUrlKey);
        return orderOrderExtraInfoConverter.toEntity(orderOrderExtraInfoDO);
    }


    /***
     * 保存订单额外信息
     * @param orderNumber
     * @param concurrentHashMap
     * @return
     */
    @Override
    public Integer saveByMap(String orderNumber, ConcurrentHashMap<String, Object> concurrentHashMap) {

        if (CollectionUtils.isNotEmpty(concurrentHashMap)) {
            for (String str : concurrentHashMap.keySet()) {
                OrderOrderExtraInfoDO orderOrderExtraInfoDO = new OrderOrderExtraInfoDO();
                orderOrderExtraInfoDO.setOrderNumber(orderNumber);
                orderOrderExtraInfoDO.setKey(str);
                orderOrderExtraInfoDO.setValue(String.valueOf(concurrentHashMap.get(str)));
                baseMapper.insert(orderOrderExtraInfoDO);
            }
        }
        return 1;
    }


}
