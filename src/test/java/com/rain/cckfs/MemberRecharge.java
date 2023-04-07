package com.rain.cckfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: LightRain
 * @Description: 会员充值
 * @DateTime: 2023-02-08 17:26
 * @Version：1.0
 **/
@Getter
@Setter
@ToString
public class MemberRecharge {
    /*
日期	卡号	姓名	金额	手机号	赠送	其他
     */
    private String 日期;
    private int 卡号;
    private String 姓名;
    private BigDecimal 金额;
    private String 手机号;
    private String 备注;
}
