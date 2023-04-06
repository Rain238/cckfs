package com.rain.cckfs.sql;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author: LightRain
 * @Description: 动态SQL
 * @DateTime: 2023-03-30 13:03
 * @Version：1.0
 **/
public class DynamicSql {
    public String modifyBillingData2(@Param("billId") int billId, @Param("date") String date, @Param("wechat") BigDecimal wechat, @Param("cash") BigDecimal cash, @Param("takeaway") BigDecimal takeaway, @Param("alipay") BigDecimal alipay, @Param("oldMember") BigDecimal oldMember, @Param("newMember") BigDecimal newMember, @Param("memberRecharge") BigDecimal memberRecharge, @Param("jiaheDistribution") BigDecimal jiaheDistribution, @Param("customerDistribution") BigDecimal customerDistribution, @Param("jambeDistribution") BigDecimal jambeDistribution, @Param("ruimaoDistribution") BigDecimal ruimaoDistribution, @Param("huashunDistribution") BigDecimal huashunDistribution, @Param("auntieDistribution") BigDecimal auntieDistribution, @Param("updateDate") long updateDate) {
        StringBuilder update = new StringBuilder("update bill set date=#{date}");
        if (wechat != null) {
            update.append(",wechat=#{wechat}");
        }
        if (cash != null) {
            update.append(",cash=#{cash}");
        }
        if (alipay != null) {
            update.append(",alipay=#{alipay}");
        }
        if (takeaway != null) {
            update.append(",takeaway=#{takeaway}");
        }
        if (oldMember != null) {
            update.append(",old_member=#{oldMember}");
        }
        if (newMember != null) {
            update.append(",new_member=#{newMember}");
        }
        if (memberRecharge != null) {
            update.append(",member_recharge=#{memberRecharge}");
        }
        if (jiaheDistribution != null) {
            update.append(",jiahe_distribution=#{jiaheDistribution}");
        }
        if (customerDistribution != null) {
            update.append(",customer_distribution=#{customerDistribution}");
        }
        if (jambeDistribution != null) {
            update.append(",jambe_distribution=#{jambeDistribution}");
        }
        if (ruimaoDistribution != null) {
            update.append(",ruimao_distribution=#{ruimaoDistribution}");
        }
        if (huashunDistribution != null) {
            update.append(",huashun_distribution=#{huashunDistribution}");
        }
        if (auntieDistribution != null) {
            update.append(",auntie_distribution=#{auntieDistribution}");
        }
        update.append(",update_date=#{updateDate} where bill_id=#{billId}");
        return update.toString();
    }
    public String modifyMemberData(@Param("id")int id,@Param("date") String date,@Param("cardNumber") Integer cardNumber,@Param("name") String name,@Param("amount") BigDecimal amount,@Param("userTel") String userTel,@Param("remark") String remark,@Param("updateDate") long updateDate) {
        StringBuilder update = new StringBuilder("update member set date=#{date},card_number=#{cardNumber},name=#{name},amount=#{amount}");

        update.append(",update_date=#{updateDate} where bill_id=#{billId}");
        return update.toString();
    }
}
