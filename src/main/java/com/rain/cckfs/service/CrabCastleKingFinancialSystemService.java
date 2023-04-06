package com.rain.cckfs.service;

import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.pojo.AddDebit;
import com.rain.cckfs.pojo.AddMembers;
import com.rain.cckfs.pojo.Return;

import java.util.Map;

/**
 * @author LightRain
 */
public interface CrabCastleKingFinancialSystemService {

    Return addBillingData(AddBillingData billingData);

    Map<String,Object> getBillList(int page, int number);

    Return modifyBillingData(AddBillingData billingData);

    Return modifyBillingData2(AddBillingData billingData);

    Return deleteBill(int billId);

    Return addMembers(AddMembers addMembers);

    Map<String, Object> getMemberList(int currPage, int pageSize);

    Return modifyMemberData(AddMembers addMembers);

    Return deleteMember(int id);


    Map<String, Object> search(String searchContent, int currPage, int pageSize);

    Return addDebit(AddDebit addDebit);

    Map<String, Object> getDebitList(int currPage, int pageSize);

    Return modificationDebit(AddDebit addDebit);

    Return deleteDebit(int id);

    Map<String, Object> initialIndex();
}
