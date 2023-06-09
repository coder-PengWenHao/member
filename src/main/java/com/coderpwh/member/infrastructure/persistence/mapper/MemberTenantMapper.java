package com.coderpwh.member.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderpwh.member.infrastructure.persistence.entity.MemberTenantDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author coderpwh
 * @since 2023-05-16
 */
@Mapper
public interface MemberTenantMapper extends BaseMapper<MemberTenantDO> {


    /***
     * 通过租户号查询
     * @param agentNumber
     * @return
     */
    MemberTenantDO selectByAgentNumber(String agentNumber);
}
