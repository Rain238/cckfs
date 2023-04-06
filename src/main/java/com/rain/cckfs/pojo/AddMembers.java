package com.rain.cckfs.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: LightRain
 * @Description: 新增会员
 * @DateTime: 2023-03-30 22:26
 * @Version：1.0
 **/
@Getter
@Setter
@ToString
public class AddMembers {
    private int id;
    /**
     * 充值日期
     */
    private String date;
    /**
     * 卡号
     */
    private Integer cardNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 手机号
     */
    private String userTel;
    /**
     * 备注
     */
    private String remark;
    /**
     * 更新时间
     */
    private BigInteger updateTime;
}
