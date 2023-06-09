package com.coderpwh.member.infrastructure.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.coderpwh.member.domain.model.MemberSettlementRule;
import com.coderpwh.member.infrastructure.persistence.entity.MemberSettlementRuleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.coderpwh.member.application.command.MemberSettlementRuleQuery;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author coderpwh
 * @since 2023-05-24
 */
@Mapper
public interface MemberSettlementRuleMapper extends BaseMapper<MemberSettlementRuleDO> {

    /**
     * 根据 实体不为空的数据查询
     *
     * @param query 查询对象
     * @return 检索后的集合
     */
    List<MemberSettlementRule> findListByEntity(MemberSettlementRuleQuery query);


    /***
     *packageId和cashierType查询
     * @param tenantId
     * @param packageId
     * @param orderType
     * @param cashierType
     * @return
     */
    List<MemberSettlementRuleDO> selectByPackageIdAndCashierType(@Param("tenantId") Long tenantId, @Param("packageId") Long packageId, @Param("orderType") Integer orderType, @Param("cashierType") String cashierType);


    /***
     * 通过tenantId和orderType和packageId查询
     * @param tenantId
     * @param orderType
     * @param packageId
     * @return
     */
    List<MemberSettlementRuleDO> selectByOrderTypeAndPackageId(@Param("tenantId") Long tenantId, @Param("orderType") Integer orderType, @Param("packageId") Long packageId);

}
