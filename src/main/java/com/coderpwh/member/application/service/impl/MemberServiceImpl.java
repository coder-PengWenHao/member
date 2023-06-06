package com.coderpwh.member.application.service.impl;

import com.alibaba.fastjson2.JSON;
import com.coderpwh.member.application.command.MemberJoinCommand;
import com.coderpwh.member.application.command.MemberPackageDetailQuery;
import com.coderpwh.member.application.command.MemberRefundCommand;
import com.coderpwh.member.application.dto.MemberUserDTO;
import com.coderpwh.member.application.service.MemberService;
import com.coderpwh.member.application.vo.MemberRefundVO;
import com.coderpwh.member.application.vo.MemberSaveVO;
import com.coderpwh.member.domain.model.*;
import com.coderpwh.member.domain.service.DomainMemberService;
import com.coderpwh.member.domain.specification.MemberPackageDetailSpecification;
import com.coderpwh.member.domain.specification.MemberRefundSpecification;
import com.coderpwh.member.domain.specification.MemberSpecification;
import com.coderpwh.member.domain.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author coderpwh
 * @date 2023/5/22 13:40
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {


    @Resource
    private MemberPackageRepository memberPackageRepository;

    @Resource
    private MemberPackageBenefitRelRepository memberPackageBenefitRelRepository;

    @Resource
    private OrderOrderRepository orderOrderRepository;

    @Resource
    private MemberCardRepository memberCardRepository;


    @Resource
    private MemberTenantRepository memberTenantRepository;


    /***
     * 开通会员
     * @param command
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberSaveVO saveMember(MemberJoinCommand command) {
        log.info("开通会员,合作方订单号:{},入参为:{}", command.getPartnerOrderNumber(), JSON.toJSONString(command));

        // 获取登录用户
        MemberUserDTO memberUser = LoginUtil.loginUser();

        // 校验层(校验用户登录与会员权益套餐包)
        MemberSpecification memberSpecification = new MemberSpecification(memberPackageRepository, memberPackageBenefitRelRepository);
        memberSpecification.isUserLogin(memberUser);
        memberSpecification.isMemberPackage(Long.valueOf(memberUser.getTenantId()), command.getProductCode());


        // 领域层
        DomainMemberService domainMemberService = new DomainMemberService();
        MemberSaveVO memberSaveVO = domainMemberService.saveMember(command);

        return memberSaveVO;
    }


    /***
     * 注销会员
     * @param command
     * @return
     */
    @Override
    public MemberRefundVO refundMember(MemberRefundCommand command) {
        // 获取登录用户
        MemberUserDTO memberUser = LoginUtil.loginUser();

        // 校验层
        MemberRefundSpecification memberRefundSpecification = new MemberRefundSpecification(orderOrderRepository, memberCardRepository);
        memberRefundSpecification.isUserLogin(memberUser);
        memberRefundSpecification.isMemberRefund(command.getOrderNumber(), command.getPartnerOrderNumber());
        memberRefundSpecification.isMemberRefundByExpirationTime(command.getOrderNumber(), command.getAgentNumber());
        memberRefundSpecification.isMemberRefundByOutOf(command.getOrderNumber());


        // 领域层
        DomainMemberService domainMemberService = new DomainMemberService();
        MemberRefundVO memberRefundVO = domainMemberService.refundMember(command);

        return memberRefundVO;
    }


    /***
     * 查询会员权益包
     * @param query
     * @return
     */
    @Override
    public String getPackageDetail(MemberPackageDetailQuery query) {
        log.info("查询会员权益包入参为:{}", JSON.toJSONString(query));

        // 数据校验
        MemberPackageDetailSpecification specification = new MemberPackageDetailSpecification(memberTenantRepository);
        specification.isMemberPackageDetail(query.getAgentNumber());


        return null;
    }


}
