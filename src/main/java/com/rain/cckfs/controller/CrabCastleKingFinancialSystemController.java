package com.rain.cckfs.controller;

import com.rain.cckfs.pojo.AddBillingData;
import com.rain.cckfs.pojo.AddDebit;
import com.rain.cckfs.pojo.AddMembers;
import com.rain.cckfs.pojo.Return;
import com.rain.cckfs.service.CrabCastleKingFinancialSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: LightRain
 * @Description: 主控制器层 统一返回
 * @DateTime: 2023-03-28 21:51
 * @Version：1.0
 **/
@RequestMapping("/cckfs")
@Controller
public class CrabCastleKingFinancialSystemController {
    private final CrabCastleKingFinancialSystemService cckfss;

    public CrabCastleKingFinancialSystemController(CrabCastleKingFinancialSystemService cckfss) {
        this.cckfss = cckfss;
    }

    /**
     * @Author: LightRain
     * @Date: 29/3/2023 下午 3:27
     * @Param: [billingData]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 添加账单数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/addBillingData")
    public Return addBillingData(AddBillingData billingData) {
        return cckfss.addBillingData(billingData);
    }

    /**
     * @Author: LightRain
     * @Date: 29/3/2023 下午 11:42
     * @Param: [currPage, pageSize]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 获取账单数据并分页
     * @since 17
     */
    @ResponseBody
    @PostMapping("/getBillList")
    public Map<String, Object> getBillList(int currPage, int pageSize) {
        return cckfss.getBillList(currPage, pageSize);
    }

    /**
     * @Author: LightRain
     * @Date: 30/3/2023 上午 12:25
     * @Param: [billingData]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 根据id修改账单数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/modifyBillingData")
    public Return modifyBillingData2(AddBillingData billingData) {
        System.out.println("billingData.toString() = " + billingData.toString());
        return cckfss.modifyBillingData2(billingData);
    }

    /**
     * @Author: LightRain
     * @Date: 30/3/2023 上午 12:29
     * @Param: [billId]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 根据id删除账单数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/deleteBill")
    public Return deleteBill(int billId) {
        return cckfss.deleteBill(billId);
    }

    /**
     * @Author: LightRain
     * @Date: 31/3/2023 下午 5:25
     * @Param: [addMembers]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 新增会员数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/addMembers")
    public Return addMembers(AddMembers addMembers) {
        return cckfss.addMembers(addMembers);
    }

    /**
     * @Author: LightRain
     * @Date: 31/3/2023 下午 5:26
     * @Param: [currPage, pageSize]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 获取会员数据并分页
     * @since 17
     */
    @ResponseBody
    @PostMapping("/getMemberList")
    public Map<String, Object> getMemberList(int currPage, int pageSize) {
        return cckfss.getMemberList(currPage, pageSize);
    }

    /**
     * @Author: LightRain
     * @Date: 31/3/2023 下午 5:26
     * @Param: [addMembers]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 修改会员数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/modifyMemberData")
    public Return modifyMemberData(AddMembers addMembers) {
        return cckfss.modifyMemberData(addMembers);
    }

    /**
     * @Author: LightRain
     * @Date: 31/3/2023 下午 5:27
     * @Param: [id]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 删除会员数据
     * @since 17
     */
    @ResponseBody
    @PostMapping("/deleteMember")
    public Return deleteMember(int id) {
        return cckfss.deleteMember(id);
    }

    /**
     * @Author: LightRain
     * @Date: 31/3/2023 下午 5:27
     * @Param: [searchContent, currPage, pageSize]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 搜索会员数据并分页
     * @since 17
     */
    @ResponseBody
    @PostMapping("/search")
    public Map<String, Object> search(String searchContent, int currPage, int pageSize) {
        return cckfss.search(searchContent, currPage, pageSize);
    }

    /**
     * @Author: LightRain
     * @Date: 1/4/2023 下午 3:45
     * @Param: [addDebit]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 添加借记记录
     * @since 17
     */
    @ResponseBody
    @PostMapping("/addDebit")
    public Return addDebit(AddDebit addDebit) {
        System.out.println("addDebit.toString() = " + addDebit.toString());
        return cckfss.addDebit(addDebit);
    }

    /**
     * @Author: LightRain
     * @Date: 1/4/2023 下午 3:45
     * @Param: [currPage, pageSize]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 获取借记记录并分页
     * @since 17
     */
    @ResponseBody
    @PostMapping("/getDebitList")
    public Map<String, Object> getDebitList(int currPage, int pageSize) {
        return cckfss.getDebitList(currPage, pageSize);
    }

    /**
     * @Author: LightRain
     * @Date: 1/4/2023 下午 3:45
     * @Param: [addDebit]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 修改借记记录
     * @since 17
     */
    @ResponseBody
    @PostMapping("/modificationDebit")
    public Return modificationDebit(AddDebit addDebit) {
        return cckfss.modificationDebit(addDebit);
    }

    /**
     * @Author: LightRain
     * @Date: 1/4/2023 下午 3:45
     * @Param: [id]
     * @Return: com.rain.cckfs.pojo.Return
     * @Description: 删除借记记录
     * @since 17
     */
    @ResponseBody
    @PostMapping("/deleteDebit")
    public Return deleteDebit(int id) {
        return cckfss.deleteDebit(id);
    }

    /**
     * @Author: LightRain
     * @Date: 6/4/2023 下午 3:51
     * @Param: []
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 初始化首页内容
     * @since 17
     */
    @ResponseBody
    @PostMapping("/initialIndex")
    public Map<String, Object> initialIndex() {
        return cckfss.initialIndex();
    }

}
