package com.rain.cckfs.service;

import com.light.rain.util.DateFormatUtil;
import com.rain.cckfs.mapper.CrabCastleKingFinancialSystemMapper;
import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.pojo.AddDebit;
import com.rain.cckfs.pojo.AddMembers;
import com.rain.cckfs.pojo.Return;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LightRain
 * @Description: 主业务层
 * @DateTime: 2023-03-28 23:57
 * @Version：1.0
 **/
@Service
public class CrabCastleKingFinancialSystemServiceEntityImpl implements CrabCastleKingFinancialSystemService {
    private final CrabCastleKingFinancialSystemMapper mapper;

    public CrabCastleKingFinancialSystemServiceEntityImpl(CrabCastleKingFinancialSystemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Return addBillingData(AddBillingData billingData) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean inset = mapper.addBillingData(billingData.getDate(), billingData.getWechat(), billingData.getCash(), billingData.getTakeaway(), billingData.getAlipay(), billingData.getOldMember(), billingData.getNewMember(), billingData.getMemberRecharge(), billingData.getJiaheDistribution(), billingData.getCustomerDistribution(), billingData.getJambeDistribution(), billingData.getRuimaoDistribution(), billingData.getHuashunDistribution(), billingData.getAuntieDistribution(), updateDate);
        if (inset) {
            return Return.builder().code(200).message("账单添加成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("账单添加失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Map<String, Object> getBillList(int currPage, int pageSize) {
        //开始计算分页
        //起始索引
        int start = (currPage - 1) * pageSize;
        //查询账单总条数
        int total = mapper.getBillTotal();
        //
        List<AddBillingData> billList = mapper.getBillList(start, pageSize);
        billList.forEach(b -> {
            /*
            收入总计
             */
            BigDecimal totalRevenue = b.getWechat().add(b.getCash()).add(b.getAlipay()).add(b.getTakeaway());
            b.setTotalRevenue(totalRevenue);
            /*
            营业额
            公式：(收入总计-会员充值)+旧会员+新会员=营业额
            */
            BigDecimal turnover = totalRevenue.subtract(b.getMemberRecharge()).add(b.getOldMember()).add(b.getNewMember());
            b.setTurnover(turnover);
            /*
            总货款
             */
            BigDecimal totalPayment = b.getJiaheDistribution().add(b.getCustomerDistribution()).add(b.getJambeDistribution()).add(b.getRuimaoDistribution()).add(b.getAuntieDistribution()).add(b.getHuashunDistribution());
            b.setTotalPayment(totalPayment);
        });
        //计算总页码（需获取总条数）
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return Map.of("billList", billList, "total", total, "pages", pages, "currPage", currPage);
    }

    @Override
    public Return modifyBillingData(AddBillingData billingData) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean update = mapper.modifyBillingData(billingData.getBillId(), billingData.getDate(), billingData.getWechat(), billingData.getCash(), billingData.getTakeaway(), billingData.getAlipay(), billingData.getOldMember(), billingData.getNewMember(), billingData.getMemberRecharge(), billingData.getJiaheDistribution(), billingData.getCustomerDistribution(), billingData.getJambeDistribution(), billingData.getRuimaoDistribution(), billingData.getHuashunDistribution(), billingData.getAuntieDistribution(), updateDate);
        if (update) {
            return Return.builder().code(200).message("账单修改成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("账单修改失败！").status(Return.FAILED).build();
        }
    }

    public Return modifyBillingData2(AddBillingData billingData) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean update = mapper.modifyBillingData2(billingData.getBillId(), billingData.getDate(), billingData.getWechat(), billingData.getCash(), billingData.getTakeaway(), billingData.getAlipay(), billingData.getOldMember(), billingData.getNewMember(), billingData.getMemberRecharge(), billingData.getJiaheDistribution(), billingData.getCustomerDistribution(), billingData.getJambeDistribution(), billingData.getRuimaoDistribution(), billingData.getHuashunDistribution(), billingData.getAuntieDistribution(), updateDate);
        if (update) {
            return Return.builder().code(200).message("账单修改成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("账单修改失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Return deleteBill(int billId) {
        boolean delete = mapper.deleteBill(billId);
        if (delete) {
            return Return.builder().code(200).message("账单删除成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("账单删除失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Return addMembers(AddMembers addMembers) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean inset = mapper.addMembers(addMembers.getDate(), addMembers.getCardNumber(), addMembers.getName(), addMembers.getAmount(), addMembers.getUserTel(), addMembers.getRemark(), updateDate);
        if (inset) {
            return Return.builder().code(200).message("会员添加成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("会员添加失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Map<String, Object> getMemberList(int currPage, int pageSize) {
        //开始计算分页
        //起始索引
        int start = (currPage - 1) * pageSize;
        //查询账单总条数
        int total = mapper.getMemberTotal();
        //
        List<AddMembers> memberList = mapper.getMemberList(start, pageSize);
        //计算总页码（需获取总条数）
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return Map.of("memberList", memberList, "total", total, "pages", pages, "currPage", currPage);
    }

    @Override
    public Return modifyMemberData(AddMembers addMembers) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean update = mapper.modifyMemberData(addMembers.getId(), addMembers.getDate(), addMembers.getCardNumber(), addMembers.getName(), addMembers.getAmount(), addMembers.getUserTel(), addMembers.getRemark(), updateDate);
        if (update) {
            return Return.builder().code(200).message("会员修改成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("会员修改失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Return deleteMember(int id) {
        boolean delete = mapper.deleteMember(id);
        if (delete) {
            return Return.builder().code(200).message("充值记录删除成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("充值记录删除失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Map<String, Object> search(String searchContent, int currPage, int pageSize) {
        searchContent = "%" + searchContent + "%";
        //开始计算分页
        //起始索引
        int start = (currPage - 1) * pageSize;
        //查询会员搜索内容总条数
        int total = mapper.getMemberSearchTotal(searchContent);
        List<AddMembers> search = mapper.search(searchContent, start, pageSize);
        System.out.println("search = " + search.toString());
        //计算总页码（需获取总条数）
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return Map.of("search", search, "total", total, "pages", pages, "currPage", currPage);
    }

    @Override
    public Return addDebit(AddDebit addDebit) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean inset = mapper.addDebit(addDebit.getDate(), addDebit.getStoreName1(), addDebit.getStoreName2(), addDebit.getCommodity(), addDebit.getPayment(), addDebit.getFlag(), updateDate);
        if (inset) {
            return Return.builder().code(200).message("借记记录添加成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("借记记录添加失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Map<String, Object> getDebitList(int currPage, int pageSize) {
        //开始计算分页
        //起始索引
        int start = (currPage - 1) * pageSize;
        //查询会员搜索内容总条数
        int total = mapper.getDebitTotal();
        List<AddDebit> debitList = mapper.getDebitList(start, pageSize);
        //计算总页码（需获取总条数）
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return Map.of("debitList", debitList, "total", total, "pages", pages, "currPage", currPage);
    }

    @Override
    public Return modificationDebit(AddDebit addDebit) {
        /*更新日期*/
        long updateDate = System.currentTimeMillis();
        boolean update = mapper.modificationDebit(addDebit.getId(), addDebit.getDate(), addDebit.getStoreName1(), addDebit.getStoreName2(), addDebit.getCommodity(), addDebit.getPayment(), addDebit.getFlag(), updateDate);
        if (update) {
            return Return.builder().code(200).message("借记记录修改成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("借记记录修改失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Return deleteDebit(int id) {
        boolean delete = mapper.deleteDebit(id);
        if (delete) {
            return Return.builder().code(200).message("借记记录删除成功！").status(Return.SUCCESS).build();
        } else {
            return Return.builder().code(500).message("借记记录删除失败！").status(Return.FAILED).build();
        }
    }

    @Override
    public Map<String, Object> initialIndex() {
        //今日日期
        String todayDate = DateFormatUtil.getTime(0, "yyyy-MM-dd");
        //今日收入
        AddBillingData tbd = mapper.getTodayIncome(todayDate);
        final BigDecimal[] tbdDecimal = {new BigDecimal(0)};
        if (tbd != null) {
            tbdDecimal[0] = tbdDecimal[0].add(tbd.getWechat().add(tbd.getCash()).add(tbd.getAlipay()).add(tbd.getTakeaway()));
        }
        //昨日日期
        String yesterdayDate = DateFormatUtil.getTime(-1, "yyyy-MM-dd");
        //昨日收入
        AddBillingData ybd = mapper.getTodayIncome(yesterdayDate);
        final BigDecimal[] ybdDecimal = {new BigDecimal(0)};
        if (ybd != null) {
            ybdDecimal[0] = ybdDecimal[0].add(ybd.getWechat().add(ybd.getCash()).add(ybd.getAlipay()).add(ybd.getTakeaway()));
        }
        //查询账单总条数
        int total = mapper.getBillTotal();
        List<AddBillingData> billList = mapper.getBillList(0, total);
        //历史金额
        final BigDecimal[] ha = {new BigDecimal(0)};
        //历史营业额
        final BigDecimal[] ht = {new BigDecimal(0)};
        //历史货款
        final BigDecimal[] hp = {new BigDecimal(0)};
        billList.forEach(b -> {
            /*
            收入总计
             */
            BigDecimal totalRevenue = b.getWechat().add(b.getCash()).add(b.getAlipay()).add(b.getTakeaway());
            ha[0] = ha[0].add(totalRevenue);
            /*
            营业额
            公式：(收入总计-会员充值)+旧会员+新会员=营业额
            */
            BigDecimal turnover = totalRevenue.subtract(b.getMemberRecharge()).add(b.getOldMember()).add(b.getNewMember());
            ht[0] = ht[0].add(turnover);
            /*
            总货款
             */
            BigDecimal totalPayment = b.getJiaheDistribution().add(b.getCustomerDistribution()).add(b.getJambeDistribution()).add(b.getRuimaoDistribution()).add(b.getAuntieDistribution()).add(b.getHuashunDistribution());
            hp[0] = hp[0].add(totalPayment);
        });
        //会员数量
        int memberTotal = mapper.getMemberTotal();
        //会员总金额
        List<AddMembers> memberList = mapper.getMemberList(0, memberTotal);
        final BigDecimal[] mm = {new BigDecimal(0)};
        memberList.forEach(m->{
            mm[0] = mm[0].add(m.getAmount());
        });
        //借记列表
        List<AddDebit> debitList =  mapper.getInProgressDebitList();
        Map<String, Object> m = new HashMap<>();
        m.put("tbdDecimal", tbdDecimal[0]);
        m.put("ybdDecimal", ybdDecimal[0]);
        m.put("ha", ha[0]);
        m.put("memberTotal", memberTotal);
        m.put("ht", ht[0]);
        //总计收入 历史金额-历史货款
        m.put("totalRevenue", ha[0].subtract(hp[0]));
        m.put("hp", hp[0]);
        m.put("mm", mm[0]);
        m.put("debitList", debitList);
        return m;
    }
}
