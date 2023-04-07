<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <header class="card-header">
            <div class="card-title">收入列表</div>
          </header>
          <div class="card-body">
            <div class="table-responsive">
              <table style="text-align: center;" class="table table-bordered">
                <thead>
                <tr>
                  <th>编号</th>
                  <th>日期</th>
                  <th>微信</th>
                  <th>现金</th>
                  <th>支付宝</th>
                  <th>小镇外卖</th>
                  <th>收入总计</th>
                  <th>会员充值</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(b,index) in billingData" :key="index">
                  <td>{{ currentPage === 1 ? index + 1 : (index + 1) + currentPage * pageSize - pageSize}}</td>
                  <td>{{ b.date }}</td>
                  <td>{{ b.wechat }}</td>
                  <td>{{ b.cash }}</td>
                  <td>{{ b.alipay }}</td>
                  <td>{{ b.takeaway }}</td>
                  <td>{{ b.totalRevenue }}</td>
                  <td>{{ b.memberRecharge }}</td>
                  <td>
                    <div class="btn-group btn-group-sm">
                      <a class="btn btn-default" href="#!" data-bs-toggle="offcanvas"
                         @click="echoBill(b.billId,b.date,b.wechat,b.cash,b.alipay,b.takeaway,b.memberRecharge)"
                         data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling" title="编辑">
                        <i class="mdi mdi-pencil"></i>
                      </a>
                    </div>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>

            <div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
                 id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
              <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasScrollingLabel">修改收入数据</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
              </div>
              <div class="offcanvas-body">
                <!--表单-->
                <div class="mb-3">
                  <label for="日期">日期</label>
                  <div class="input-group">
                    <input v-model="time" disabled id="日期" type="text" placeholder="选择日期" class="form-control"
                           data-provide="datepicker"
                           data-today-highlight="true"
                           data-autoclose="false">
                    <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="微信">微信</label>
                  <input @blur="blur()" v-model="wechat" id="微信" class="form-control" type="number"
                         placeholder="微信">
                </div>
                <div class="mb-3">
                  <label for="现金">现金</label>
                  <input @blur="blur()" v-model="cash" id="现金" class="form-control" type="number" placeholder="现金">
                </div>
                <div class="mb-3">
                  <label for="支付宝">支付宝</label>
                  <input @blur="blur()" v-model="alipay" id="支付宝" class="form-control" type="number"
                         placeholder="支付宝">
                </div>
                <div class="mb-3">
                  <label for="小镇外卖">小镇外卖</label>
                  <input @blur="blur()" v-model="takeaway" id="小镇外卖" class="form-control" type="number"
                         placeholder="小镇外卖">
                </div>
                <div class="mb-3">
                  <label for="总计">总计</label>
                  <input v-model="totalRevenue" id="总计" disabled class="form-control" type="number"
                         placeholder="总计">
                </div>
                <div class="mb-3">
                  <label for="会员充值">会员充值</label>
                  <input @blur="blur()" v-model="memberRecharge" id="会员充值" class="form-control" type="number"
                         placeholder="会员充值">
                </div>
                <div class="d-grid gap-2 col-6 mx-auto">
                  <button @click="confirmModifications()" class="btn btn-primary" type="button">
                    <i class="mdi mdi-checkbox-marked-circle-outline"></i>
                    确认修改
                  </button>
                </div>
              </div>
            </div>

            <ul v-if="total!==0" class="pagination">
              <el-pagination pager-count="12" background layout="prev, pager, next" v-model:page-size="pageSize"
                             :total="total" v-model:current-page="currentPage"
                             @current-change="getBillList(currentPage,pageSize)"/>
            </ul>

          </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>

import axios from "axios";
import qs from "qs";
import {Decimal} from 'decimal.js';
import {ElMessage} from "element-plus";

export default {
  name: "Revenue",
  data() {
    return {
      /**
       * 账单id
       */
      billId: null,
      /**
       * 当前页数
       */
      currentPage: 1,
      /**
       * 展示条数
       */
      pageSize: 30,
      /**
       * 账单数据
       */
      billingData: "",
      /**
       * 日期
       */
      time: null,
      /**
       * 微信
       */
      wechat: null,
      /**
       * 现金
       */
      cash: null,
      /**
       * 支付宝
       */
      alipay: null,
      /**
       * 小镇外卖
       */
      takeaway: null,
      /**
       * 会员充值
       */
      memberRecharge: null,
      /**
       * 总数量
       */
      total: null,
      /**
       * 收入总计
       */
      totalRevenue: null,
    }
  },
  methods: {
    /**
     * 失去焦点自动执行
     */
    blur() {
      if (this.wechat==="") this.wechat=0
      if (this.cash==="") this.cash=0
      if (this.alipay==="") this.alipay=0
      if (this.takeaway==="") this.takeaway=0
      if (this.memberRecharge==="") this.memberRecharge=0
      this.totalRevenue = new Decimal(0.0).add(this.wechat).add(this.cash).add(this.alipay).add(this.takeaway);
    },
    /**
     * 回显数据
     * @param id
     * @param date
     * @param wechat
     * @param cash
     * @param alipay
     * @param takeaway
     * @param memberRecharge
     */
    echoBill(id, date, wechat, cash, alipay, takeaway, memberRecharge) {
      this.billId = id
      this.time = date
      this.wechat = wechat
      this.cash = cash
      this.alipay = alipay
      this.takeaway = takeaway
      this.memberRecharge = memberRecharge
      this.totalRevenue = new Decimal(0.0).add(wechat).add(cash).add(alipay).add(takeaway);
    },
    getBillList(currPage, pageSize) {
      const data = {
        currPage: currPage,
        pageSize: pageSize
      }
      axios({
        url: "/api/cckfs/getBillList",
        data: qs.stringify(data),
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res)
        this.billingData = res.data.billList
        this.currentPage = res.data.currPage
        this.total = res.data.total
      })
    },
    /*
   初始化判断
    */
    assignmentJudgment() {
      if (this.wechat === null) this.wechat = 0
      if (this.cash === null) this.cash = 0
      if (this.alipay === null) this.alipay = 0
      if (this.takeaway === null) this.takeaway = 0
      if (this.memberRecharge === null) this.memberRecharge = 0
    },
    /**
     * 确认修改提交操作
     */
    confirmModifications() {
      //初始化判断
      this.assignmentJudgment()
      const data = {
        date: this.time,
        billId: this.billId,
        wechat: this.wechat,
        cash: this.cash,
        alipay: this.alipay,
        takeaway: this.takeaway,
        memberRecharge: this.memberRecharge,
      };
      if (this.wechat !== 0 || this.cash !== 0 || this.alipay !== 0 || this.takeaway !== 0 || this.memberRecharge !== 0) {
        axios({
          url: "/api/cckfs/modifyBillingData",
          data: qs.stringify(data),
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res => {
          if (res.data.status === "SUCCESS") {
            console.log(res.data.message)
            //确定后执行局部刷新操作
            window.location.reload();
          } else {
            console.log(res.data.message)
            ElMessage.error(res.data.message)
          }
        })
      }
    },
    /**
     * session状态
     */
    sessionStatus() {
      axios({
        url: "/api/session/status",
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res)
        if (res.data.status === "FAILED") {
          window.location.href='/login';
          ElMessage.error(res.data.message)
        }
      })
    },
  },
  mounted() {
    this.sessionStatus()
    this.getBillList(this.currentPage, this.pageSize)
  }
}
</script>

<style scoped>
.td-check, .th-check {
  display: flex
}

.form-check {
  margin: auto
}
</style>