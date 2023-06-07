package com.coderpwh.member.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author coderpwh
 * @date 2023/6/7 13:44
 */
@Data
public class MemberPackageOrderDTO implements Serializable {
    /***
     * 用户id
     */
    private Long userId;

    /***
     * 会员卡状态
     */
    private String status;


    /***
     * 会员卡类型
     */
    private String cardType;


    /***
     * 会员卡类型中文
     */
    private String cardTypeCn;


    /***
     * 会员套餐生效时间
     */

    private String effectiveTime;

    /***
     * 会员套餐失效时间
     */
    private String expirationTime;

}