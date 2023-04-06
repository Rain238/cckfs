package com.rain.cckfs.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: LightRain
 * @Description: 新增账单数据
 * @DateTime: 2023-03-28 22:00
 * @Version：1.0
 **/
@Setter
@Getter
@ToString
public class AddBillingData {
    /**
     * id
     */
    private int billId;
    /**
     * 日期
     */
    private String date;
    /**
     * 微信
     */
    private BigDecimal wechat;
    /**
     * 现金
     */
    private BigDecimal cash;
    /**
     * 支付宝
     */
    private BigDecimal alipay;
    /**
     * 小镇外卖
     */
    private BigDecimal takeaway;
    /**
     * 旧会员
     */
    private BigDecimal oldMember;
    /**
     * 新会员
     */
    private BigDecimal newMember;
    /**
     * 会员充值
     */
    private BigDecimal memberRecharge;
    /**
     * 佳禾配货
     */
    private BigDecimal jiaheDistribution;
    /**
     * 众客配货
     */
    private BigDecimal customerDistribution;
    /**
     * 詹贝配货
     */
    private BigDecimal jambeDistribution;
    /**
     * 瑞茂配货
     */
    private BigDecimal ruimaoDistribution;
    /**
     * 华顺配货
     */
    private BigDecimal huashunDistribution;
    /**
     * 姑姑配货
     */
    private BigDecimal auntieDistribution;
    /**
     * 更新时间
     */
    private BigInteger updateDate;



    /**
     * 总收入合计
     */
    private BigDecimal totalRevenue;
    /**
     * 营业额
     */
    private BigDecimal turnover;
    /**
     * 总货款
     */
    private BigDecimal totalPayment;

}
