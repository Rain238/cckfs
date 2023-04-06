package com.rain.cckfs;

import com.rain.cckfs.mapper.CrabCastleKingFinancialSystemMapper;
import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.service.CrabCastleKingFinancialSystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

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

}
