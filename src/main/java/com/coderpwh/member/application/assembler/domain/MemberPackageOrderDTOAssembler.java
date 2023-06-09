package com.coderpwh.member.application.assembler.domain;

import com.coderpwh.member.application.dto.MemberPackageOrderDTO;
import com.coderpwh.member.common.util.mapstruct.MapStructConverter;
import com.coderpwh.member.domain.model.MemberCardHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author coderpwh
 * @date 2023/6/7 13:55
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPackageOrderDTOAssembler extends MapStructConverter<MemberPackageOrderDTO, MemberCardHistory> {

}
