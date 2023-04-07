package com.rain.cckfs;

import com.rain.cckfs.mapper.CrabCastleKingFinancialSystemMapper;
import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.service.CrabCastleKingFinancialSystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.rain.cckfs.CSV.*;

@SpringBootTest
class CckfsApplicationTests {
    @Autowired
    private CrabCastleKingFinancialSystemService s;
    @Autowired
    public CrabCastleKingFinancialSystemMapper mapper;

    @Test
    void contextLoads() {
        List<AddBillingData> billList = mapper.getBillList(0, 10);
        System.out.println("billList = " + billList);
    }

    @Test
    void contextLoads2() {
        String encode = new BCryptPasswordEncoder().encode("13020505383");
        System.out.println("encode = " + encode);
    }

    @Test
    void contextLoads3() {
        Map<String, Object> stringObjectMap = s.initialIndex();
        System.out.println("stringObjectMap.toString() = " + stringObjectMap.toString());
    }

    @Test
    void contextLoads4() {
        Map<String, Object> map = readCsvByCsvReaderMap("C:\\Users\\LightRain\\Desktop\\XLSX\\2022年会员充值记录.CSV");
        ArrayList<BillBean> XlsxBeans = readCsvByCsvReader("C:\\Users\\LightRain\\Desktop\\XLSX\\日收账单.CSV");
        for (BillBean cvs : XlsxBeans) {
            System.out.println("cvs.toString() = " + cvs.toString());
            if (map.containsKey(cvs.get日期())) {
//                System.out.println(cvs.get日期()+" = " + map.get(cvs.get日期()).toString());
                String huiyan = map.get(cvs.get日期()).toString();
                mapper.insetBill2(cvs.get日期(), cvs.get实收(), huiyan, cvs.get旧会员(), cvs.get新会员(), cvs.get进货佳禾(), cvs.get进货众客(), cvs.get詹贝配货(), cvs.get进货瑞茂(), cvs.get进货华顺(), cvs.get姑姑带货(), System.currentTimeMillis());
                continue;
            }
            mapper.insetBill(cvs.get日期(), cvs.get实收(), cvs.get旧会员(), cvs.get新会员(), cvs.get进货佳禾(), cvs.get进货众客(), cvs.get詹贝配货(), cvs.get进货瑞茂(), cvs.get进货华顺(), cvs.get姑姑带货(), System.currentTimeMillis());
        }
    }

    @Test
    void contextLoads5() {
        ArrayList<MemberRecharge> XlsxBeans = readCsvByCsvReader2("C:\\Users\\LightRain\\Desktop\\XLSX\\2022年会员充值记录.CSV");
        for (MemberRecharge mr : XlsxBeans) {
            System.out.println("mr.toString() = " + mr.toString());
            mapper.insetMemberRecharge(mr.get日期(), mr.get卡号(), mr.get姓名(), mr.get金额(), mr.get手机号(), mr.get备注(), System.currentTimeMillis());
        }
    }

}
