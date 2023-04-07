package com.rain.cckfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: LightRain
 * @Description:
 * @DateTime: 2023-02-08 17:26
 * @Version：1.0
 **/
@Getter
@Setter
@ToString
public class BillBean {
    /*
    			总收		营业额/日							总货款	还款		现金	微信	支付宝	外卖	会员充值
     */
    //交易时间
    private String 日期;
    //交易类型
    private BigDecimal 实收;
    //交易对方
    private BigDecimal 旧会员;
    //商品
    private BigDecimal 新会员;
    //收支
    private BigDecimal 进货佳禾;
    //金额
    private BigDecimal 进货众客;
    //支付方式
    private BigDecimal 詹贝配货;
    //当前状态
    private BigDecimal 进货华顺;
    //交易单号
    private BigDecimal 进货瑞茂;
    //商户单号
    private BigDecimal 姑姑带货;
}
