package com.rain.cckfs.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: LightRain
 * @Description: 借记数据
 * @DateTime: 2023-03-31 17:21
 * @Version：1.0
 **/
@Getter
@Setter
@ToString
public class AddDebit {
    /**
     * id
     */
    private int id;
    /**
     * 借记日期
     */
    private String date;
    /**
     * 店名1
     */
    private String storeName1;
    /**
     * 店名2
     */
    private String storeName2;
    /**
     * 借记商品
     */
    private String commodity;
    /**
     * 借记货款
     */
    private BigDecimal payment;
    /**
     * 状态 i:进行中，c:已完成
     */
    private String flag;
    /**
     * 更新时间
     */
    private BigInteger updateTime;


}
