package com.rain.cckfs.mapper;

import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.pojo.AddDebit;
import com.rain.cckfs.pojo.AddMembers;
import com.rain.cckfs.sql.DynamicSql;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: Sql
 * @DateTime: 2023-03-29 00:35
 * @Version：1.0 DynamicSql
 **/
@Mapper
public interface CrabCastleKingFinancialSystemMapper {
    @Insert("insert into bill values(null,#{date},#{wechat},#{cash},#{alipay},#{smallTownTakeout},#{oldMember},#{newMember},#{memberRecharge},#{jiaheDistribution},#{customerDistribution},#{jambeDistribution},#{ruimaoDistribution},#{huashunDistribution},#{auntieDistribution},#{updateDate})")
    boolean addBillingData(String date, BigDecimal wechat, BigDecimal cash, BigDecimal smallTownTakeout, BigDecimal alipay, BigDecimal oldMember, BigDecimal newMember, BigDecimal memberRecharge, BigDecimal jiaheDistribution, BigDecimal customerDistribution, BigDecimal jambeDistribution, BigDecimal ruimaoDistribution, BigDecimal huashunDistribution, BigDecimal auntieDistribution, long updateDate);

    @Select("select count(bill_id) from bill")
    int getBillTotal();

    @Select("select bill_id billId,date,wechat,cash,alipay,takeaway,old_member oldMember,new_member newMember,member_recharge memberRecharge,jiahe_distribution jiaheDistribution,customer_distribution customerDistribution,customer_distribution customerDistribution,jambe_distribution jambeDistribution,ruimao_distribution ruimaoDistribution,huashun_distribution huashunDistribution,auntie_distribution auntieDistribution,update_date updateDate from bill order by bill_id desc limit #{start},#{pageSize} ")
    List<AddBillingData> getBillList(int start, int pageSize);

    @Update("update bill set date=#{date},wechat=#{wechat},cash=#{cash},alipay=#{alipay},takeaway=#{takeaway},old_member=#{oldMember},new_member=#{newMember},member_recharge=#{memberRecharge},jiahe_distribution=#{jiaheDistribution},customer_distribution=#{customerDistribution},jambe_distribution=#{jambeDistribution},ruimao_distribution=#{ruimaoDistribution},huashun_distribution=#{huashunDistribution},auntie_distribution=#{auntieDistribution},update_date=#{updateDate} where bill_id=#{billId}")
    boolean modifyBillingData(int billId, String date, BigDecimal wechat, BigDecimal cash, BigDecimal takeaway, BigDecimal alipay, BigDecimal oldMember, BigDecimal newMember, BigDecimal memberRecharge, BigDecimal jiaheDistribution, BigDecimal customerDistribution, BigDecimal jambeDistribution, BigDecimal ruimaoDistribution, BigDecimal huashunDistribution, BigDecimal auntieDistribution, long updateDate);

    @Delete("delete from bill where bill_id=#{billId}")
    boolean deleteBill(int billId);

    @UpdateProvider(type = DynamicSql.class, method = "modifyBillingData2")
    boolean modifyBillingData2(@Param("billId") int billId, @Param("date") String date, @Param("wechat") BigDecimal wechat, @Param("cash") BigDecimal cash, @Param("takeaway") BigDecimal takeaway, @Param("alipay") BigDecimal alipay, @Param("oldMember") BigDecimal oldMember, @Param("newMember") BigDecimal newMember, @Param("memberRecharge") BigDecimal memberRecharge, @Param("jiaheDistribution") BigDecimal jiaheDistribution, @Param("customerDistribution") BigDecimal customerDistribution, @Param("jambeDistribution") BigDecimal jambeDistribution, @Param("ruimaoDistribution") BigDecimal ruimaoDistribution, @Param("huashunDistribution") BigDecimal huashunDistribution, @Param("auntieDistribution") BigDecimal auntieDistribution, @Param("updateDate") long updateDate);

    @Insert("insert into member values(null,#{date},#{cardNumber},#{name},#{amount},#{userTel},#{remark},#{updateDate})")
    boolean addMembers(String date, Integer cardNumber, String name, BigDecimal amount, String userTel, String remark, long updateDate);

    @Select("select count(id) from member")
    int getMemberTotal();

    @Select("select id,date,card_number cardNumber,name,amount,user_tel userTel,remark,update_time updateTime  from member order by id desc limit #{start},#{pageSize} ")
    List<AddMembers> getMemberList(int start, int pageSize);

    @Update("update member set date=#{date},card_number=#{cardNumber},name=#{name},amount=#{amount},user_tel=#{userTel},remark=#{remark},update_time=#{updateDate} where id=#{id}")
    boolean modifyMemberData(int id, String date, Integer cardNumber, String name, BigDecimal amount, String userTel, String remark, long updateDate);

    @Delete("delete from member where id=#{id}")
    boolean deleteMember(int id);

    @Select("select id,date,card_number cardNumber,name,amount,user_tel userTel,remark,update_time updateTime from member where card_number like #{searchContent} or name like #{searchContent} order by id desc limit #{start},#{pageSize}")
    List<AddMembers> search(String searchContent, int start, int pageSize);

    @Select("select count(id) from member where card_number like #{searchContent} or name like #{searchContent}")
    int getMemberSearchTotal(String searchContent);

    @Insert("insert into debit values(null,#{date},#{storeName1},#{storeName2},#{commodity},#{payment},#{flag},#{updateDate})")
    boolean addDebit(String date, String storeName1, String storeName2, String commodity, BigDecimal payment, String flag, long updateDate);

    @Select("select count(id) from debit")
    int getDebitTotal();

    @Select("select id,date,store_name1 storeName1,store_name2 storeName2,commodity,payment,flag,update_time updateTime from debit order by id desc limit #{start},#{pageSize}")
    List<AddDebit> getDebitList(int start, int pageSize);

    @Update("update debit set date=#{date},store_name1=#{storeName1},store_name2=#{storeName2},commodity=#{commodity},payment=#{payment},flag=#{flag},update_time=#{updateDate} where id=#{id}")
    boolean modificationDebit(int id, String date, String storeName1, String storeName2, String commodity, BigDecimal payment, String flag, long updateDate);

    @Delete("delete from debit where id=#{id}")
    boolean deleteDebit(int id);

    @Select("select bill_id billId,date,wechat,cash,alipay,takeaway from bill where date=#{date}")
    AddBillingData getTodayIncome(String date);

    @Select("select id,date,store_name1 storeName1,store_name2 storeName2,commodity,payment,flag,update_time updateTime from debit where flag='i' order by id desc")
    List<AddDebit> getInProgressDebitList();
}
