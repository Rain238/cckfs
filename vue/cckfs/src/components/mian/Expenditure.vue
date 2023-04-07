<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <header class="card-header">
            <div class="card-title">支出列表</div>
          </header>
          <div class="card-body">
            <div class="table-responsive">
              <table style="text-align: center;" class="table table-bordered">
                <thead>
                <tr>
                  <th>编号</th>
                  <th>日期</th>
                  <th>佳禾配货</th>
                  <th>众客配货</th>
                  <th>詹贝配货</th>
                  <th>瑞茂配货</th>
                  <th>华顺配货</th>
                  <th>姑姑配货</th>
                  <th>总货款</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(b,index) in billingData" :key="index">
                  <td>{{ currentPage === 1 ? index + 1 : (index + 1) + currentPage * pageSize - pageSize}}</td>
                  <td>{{ b.date }}</td>
                  <td>{{ b.jiaheDistribution }}</td>
                  <td>{{ b.customerDistribution }}</td>
                  <td>{{ b.jambeDistribution }}</td>
                  <td>{{ b.ruimaoDistribution }}</td>
                  <td>{{ b.huashunDistribution }}</td>
                  <td>{{ b.auntieDistribution }}</td>
                  <td>{{ b.totalPayment }}</td>
                  <td>
                    <div class="btn-group btn-group-sm">
                      <a class="btn btn-default" href="#!" data-bs-toggle="offcanvas"
                         @click="echoBill(b.billId,b.date,b.jiaheDistribution,b.customerDistribution,b.jambeDistribution,b.ruimaoDistribution,b.huashunDistribution,b.auntieDistribution)"
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
                <h5 class="offcanvas-title" id="offcanvasScrollingLabel">修改支出数据</h5>
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
                  <label for="佳禾配货">佳禾配货</label>
                  <input @blur="blur()" v-model="jiaheDistribution" id="佳禾配货" class="form-control" type="number"
                         placeholder="佳禾配货">
                </div>
                <div class="mb-3">
                  <label for="众客配货">众客配货</label>
                  <input @blur="blur()" v-model="customerDistribution" id="众客配货" class="form-control" type="number"
                         placeholder="众客配货">
                </div>
                <div class="mb-3">
                  <label for="詹贝配货">詹贝配货</label>
                  <input @blur="blur()" v-model="jambeDistribution" id="詹贝配货" class="form-control" type="number"
                         placeholder="詹贝配货">
                </div>
                <div class="mb-3">
                  <label for="瑞茂配货">瑞茂配货</label>
                  <input @blur="blur()" v-model="ruimaoDistribution" id="瑞茂配货" class="form-control" type="number"
                         placeholder="瑞茂配货">
                </div>
                <div class="mb-3">
                  <label for="姑姑配货">姑姑配货</label>
                  <input @blur="blur()" v-model="auntieDistribution" id="姑姑配货" class="form-control" type="number"
                         placeholder="姑姑配货">
                </div>
                <div class="mb-3">
                  <label for="总货款">总货款</label>
                  <input v-model="totalPayment" id="总货款" disabled class="form-control" type="number"
                         placeholder="总货款">
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
import {Decimal} from "decimal.js";
import {ElMessage} from "element-plus";

export default {
  name: "Expenditure",
  data() {
    return {
      /**
       * 账单id
       */
      billId: null,
      /**
       * 日期
       */
      time: null,
      /**
       * 账单数据
       */
      billingData: "",
      /**
       * 当前页数
       */
      currentPage: 1,
      /**
       * 展示条数
       */
      pageSize: 30,
      /**
       * 总数量
       */
      total: null,
      /**
       * 佳禾配货
       */
      jiaheDistribution: null,
      /**
       * 众客配货
       */
      customerDistribution: null,
      /**
       * 詹贝配货
       */
      jambeDistribution: null,
      /**
       * 瑞茂配货
       */
      ruimaoDistribution: null,
      /**
       * 华顺配货
       */
      huashunDistribution: null,
      /**
       * 姑姑配货
       */
      auntieDistribution: null,
      /**
       * 总货款
       */
      totalPayment: null,
    }
  },
  methods: {
    /**
     * 失去焦点自动执行
     */
    blur() {
      if (this.jiaheDistribution==="") this.jiaheDistribution=0
      if (this.customerDistribution==="") this.customerDistribution=0
      if (this.jambeDistribution==="") this.jambeDistribution=0
      if (this.ruimaoDistribution==="") this.ruimaoDistribution=0
      if (this.huashunDistribution==="") this.huashunDistribution=0
      if (this.auntieDistribution==="") this.auntieDistribution=0
      this.totalPayment = new Decimal(0.0).add(this.jiaheDistribution).add(this.customerDistribution).add(this.jambeDistribution).add(this.ruimaoDistribution).add(this.huashunDistribution).add(this.auntieDistribution);
    },
    /**
     * 回显数据
     * @param id
     * @param date
     * @param jiaheDistribution
     * @param customerDistribution
     * @param jambeDistribution
     * @param ruimaoDistribution
     * @param huashunDistribution
     * @param auntieDistribution
     */
    echoBill(id, date, jiaheDistribution, customerDistribution, jambeDistribution, ruimaoDistribution, huashunDistribution, auntieDistribution) {
      this.billId = id
      this.time = date
      this.jiaheDistribution = jiaheDistribution
      this.customerDistribution = customerDistribution
      this.jambeDistribution = jambeDistribution
      this.ruimaoDistribution = ruimaoDistribution
      this.huashunDistribution = huashunDistribution
      this.auntieDistribution = auntieDistribution
      this.totalPayment = new Decimal(0.0).add(jiaheDistribution).add(customerDistribution).add(jambeDistribution).add(ruimaoDistribution).add(huashunDistribution).add(auntieDistribution);
    },
    /*
    获取账单列表 页码 展示条数
     */
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
      if (this.jiaheDistribution === null) this.jiaheDistribution = 0
      if (this.customerDistribution === null) this.customerDistribution = 0
      if (this.jambeDistribution === null) this.jambeDistribution = 0
      if (this.ruimaoDistribution === null) this.ruimaoDistribution = 0
      if (this.huashunDistribution === null) this.huashunDistribution = 0
      if (this.auntieDistribution === null) this.auntieDistribution = 0
    },
    /**
     * 确认修改提交操作
     */
    confirmModifications() {
      //初始化判断
      this.assignmentJudgment()
      const data = {
        billId: this.billId,
        date: this.time,
        jiaheDistribution: this.jiaheDistribution,
        customerDistribution: this.customerDistribution,
        jambeDistribution: this.jambeDistribution,
        ruimaoDistribution: this.ruimaoDistribution,
        huashunDistribution: this.huashunDistribution,
        auntieDistribution: this.auntieDistribution,
      };
      if (this.jiaheDistribution !== 0 || this.customerDistribution !== 0 || this.jambeDistribution !== 0 || this.ruimaoDistribution !== 0 || this.huashunDistribution !== 0 || this.auntieDistribution !== 0) {
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