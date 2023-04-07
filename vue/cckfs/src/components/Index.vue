<template>
  <div class="container-fluid">
    <div class="row">
      <!--今日收入-->
      <div class="col-md-6 col-xl-3">
        <div class="card bg-primary text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ tbdDecimal }}</span>
            </div>
            <div class="text-end">今日收入</div>
          </div>
        </div>
      </div>
      <!--昨日收入-->
      <div class="col-md-6 col-xl-3">
        <div class="card bg-warning text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ ybdDecimal }}</span>
            </div>
            <div class="text-end">昨日收入</div>
          </div>
        </div>
      </div>
      <!--历史金额-->
      <div class="col-md-6 col-xl-3">
        <div class="card bg-danger text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ ha }}</span>
            </div>
            <div class="text-end">历史金额</div>
          </div>
        </div>
      </div>
      <!--会员数量-->
      <div class="col-md-6 col-xl-3">
        <div class="card bg-purple text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
  	        <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-account fs-4"></i>
            </span>
              <span class="fs-4">{{ memberTotal }}</span>
            </div>
            <div class="text-end">会员数量</div>
          </div>
        </div>
      </div>
      <!--总营业额-->
      <div class="col-md-6 col-xl-3">
        <div style="background-color: #00A0FF" class="card  text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ ht }}</span>
            </div>
            <div class="text-end">总营业额</div>
          </div>
        </div>
      </div>
      <!--总计收入=历史总金额-历史货款-->
      <div class="col-md-6 col-xl-3">
        <div style="background-color: #ec693f" class="card  text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ totalRevenue }}</span>
            </div>
            <div class="text-end">总计收入</div>
          </div>
        </div>
      </div>
      <!--历史货款-->
      <div class="col-md-6 col-xl-3">
        <div style="background-color: #e42c64" class="card text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
            <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
              <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ hp }}</span>
            </div>
            <div class="text-end">历史货款</div>
          </div>
        </div>
      </div>
      <!--会员数量-->
      <div class="col-md-6 col-xl-3">
        <div style="background-color: #6759e7" class="card  text-white">
          <div class="card-body">
            <div class="d-flex justify-content-between">
  	        <span class="avatar-md rounded-circle bg-white bg-opacity-25 avatar-box">
             <i class="mdi mdi-currency-cny fs-4"></i>
            </span>
              <span class="fs-4">{{ mm }}</span>
            </div>
            <div class="text-end">会员金额</div>
          </div>
        </div>
      </div>
      <!--借记列表-->
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <header class="card-header">
                <div class="card-title">借记列表</div>
              </header>
              <div class="card-body">
                <div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
                     id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
                  <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasScrollingLabel">修改借记数据</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                  </div>
                  <div class="offcanvas-body">
                    <!--表单-->
                    <div class="mb-3">
                      <label for="日期">日期</label>
                      <div class="input-group">
                        <el-date-picker
                            style="width: 100%"
                            v-model="time"
                            type="date"
                            placeholder="选择日期"
                            value-format="YYYY-MM-DD"
                            id="日期"
                            :size="'large'"
                            :clearable="false"
                        />
                      </div>
                    </div>
                    <div class="mb-3">
                      <label for="店名">店名</label>
                      <input v-model="storeName1" id="店名" class="form-control" type="text" placeholder="店名">
                    </div>
                    <div class="d-grid gap-2 col-3 mx-auto">
                      <span class="badge bg-warning">借</span>
                    </div>
                    <div class="mb-3">
                      <label for="店名">店名</label>
                      <input v-model="storeName2" id="店名" class="form-control" type="text" placeholder="店名">
                    </div>
                    <div class="mb-3">
                      <label for="商品">商品</label>
                      <textarea v-model="commodity" id="商品" class="form-control" aria-atomic="false" type="text"
                                placeholder="商品"/>
                    </div>
                    <div class="mb-3">
                      <label for="货款">货款</label>
                      <input v-model="payment" id="货款" class="form-control" type="number" placeholder="货款">
                    </div>
                    <div class="mb-3 d-grid gap-2 d-md-block col-6 mx-auto">
                      <div v-if="flag==='i'" class="form-check form-check-inline col-6 mx-auto">
                        <input class="form-check-input" type="radio" name="type" id="inlineRadio1"
                               @click="select('i')" checked>
                        <label class="form-check-label" for="inlineRadio1"><span
                            class="badge bg-success">进行中</span></label>
                      </div>
                      <div v-if="flag!=='i'" class="form-check form-check-inline col-6 mx-auto">
                        <input class="form-check-input" type="radio" name="type" id="inlineRadio1"
                               @click="select('i')">
                        <label class="form-check-label" for="inlineRadio1"><span
                            class="badge bg-success">进行中</span></label>
                      </div>
                      <div v-if="flag==='c'" class="form-check form-check-inline  col-6 mx-auto">
                        <input class="form-check-input" type="radio" name="type" id="inlineRadio2"
                               @click="select('c')" checked>
                        <label class="form-check-label" for="inlineRadio2"><span
                            class="badge bg-primary">已完成</span></label>
                      </div>
                      <div v-if="flag!=='c'" class="form-check form-check-inline  col-6 mx-auto">
                        <input class="form-check-input" type="radio" name="type" id="inlineRadio2"
                               @click="select('c')">
                        <label class="form-check-label" for="inlineRadio2"><span
                            class="badge bg-primary">已完成</span></label>
                      </div>
                    </div>
                    <div class="mb-3 d-grid gap-2 col-6 mx-auto">
                      <button @click="confirmModifications()" class="btn btn-primary"
                              type="button">
                        <i class="mdi mdi-checkbox-marked-circle-outline"></i>
                        确认修改
                      </button>
                    </div>
                  </div>
                </div>
                <div class="table-responsive">
                  <table style="text-align: center;" class="table table-hover">
                    <thead>
                    <tr>
                      <th>#</th>
                      <th>日期</th>
                      <th>店名</th>
                      <th>标签</th>
                      <th>店名</th>
                      <th>商品</th>
                      <th>货款</th>
                      <th>状态</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(d,index) in debitList" :key="index">
                      <td>{{ index + 1 }}</td>
                      <td>{{ d.date }}</td>
                      <td>{{ d.storeName1 }}</td>
                      <td><span class="badge bg-warning">借</span></td>
                      <td>{{ d.storeName2 }}</td>
                      <td @click="commodity=d.commodity" data-bs-toggle="modal" href="#exampleModalToggle">
                        {{ this.ellipsis(d.commodity) }}
                      </td>
                      <td>{{ d.payment }}</td>
                      <td>
                        <span v-if="d.flag==='i'" class="badge bg-success">进行中</span>
                        <span v-if="d.flag==='c'" class="badge bg-primary">已完成</span>
                      </td>
                      <td>
                        <div class="btn-group btn-group-sm">
                          <a @click="echoData(d.id,d.date,d.storeName1,d.storeName2,d.commodity,d.payment,d.flag)"
                             class="btn btn-default" href="#!" data-bs-toggle="offcanvas"
                             data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling" title="编辑">
                            <i class="mdi mdi-pencil"></i>
                          </a>
                          <el-popconfirm
                              confirm-button-text="Yes"
                              cancel-button-text="No"
                              icon-color="#626AEF"
                              title="确认删除后将无法恢复！"
                              @confirm="deleteDebit(d.id)"
                          >
                            <template #reference>
                              <a class="btn btn-default" href="#!" data-bs-toggle="tooltip" title="删除">
                                <i class="mdi mdi-window-close"></i>
                              </a>
                            </template>
                          </el-popconfirm>
                        </div>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <!--          模态窗-->
              <div class="modal fade" id="exampleModalToggle" aria-hidden="true"
                   aria-labelledby="exampleModalToggleLabel"
                   tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-body">
                      {{ commodity }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import qs from "qs";
import {ElMessage} from "element-plus";

export default {
  name: "Index",
  data() {
    return {
      id: "",
      /**
       * 日期
       */
      time: "",
      /**
       * 店名1
       */
      storeName1: "",
      /**
       * 店名2
       */
      storeName2: "",
      /**
       * 商品
       */
      commodity: "",
      /**
       * 货款
       */
      payment: "",
      /**
       * 状态
       */
      flag: "",
      /**
       * 今日收入
       */
      tbdDecimal: "",
      /**
       * 昨日收入
       */
      ybdDecimal: "",
      /**
       * 历史金额
       */
      ha: "",
      /**
       * 会员数量
       */
      memberTotal: "",
      /**
       * 历史营业额
       */
      ht: "",
      /**
       * 总计收入
       */
      totalRevenue: "",
      /**
       * 历史货款
       */
      hp: "",
      /**
       * 会员总金额
       */
      mm: "",
      /**
       * 借方清单
       */
      debitList: "",
    }
  },
  methods: {
    /**
     * 确认修改
     */
    confirmModifications() {
      if (this.storeName1 !== "" && this.storeName2 !== "" && this.commodity !== "") {
        const data = {
          id: this.id,
          date: this.time,
          storeName1: this.storeName1,
          storeName2: this.storeName2,
          commodity: this.commodity,
          payment: this.payment,
          flag: this.flag,
        }
        axios({
          url: "/api/cckfs/modificationDebit",
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
          }
        })
      }
    },
    /**
     * 状态选择
     * @param flag
     */
    select(flag) {
      this.flag = flag
    },
    /**
     * 回显数据
     * @param id
     * @param date
     * @param storeName1
     * @param storeName2
     * @param commodity
     * @param payment
     * @param flag
     */
    echoData(id, date, storeName1, storeName2, commodity, payment, flag) {
      this.title = "修改借记数据"
      this.id = id
      this.time = date
      this.storeName1 = storeName1
      this.storeName2 = storeName2
      this.commodity = commodity
      this.payment = payment
      this.flag = flag
    },
    ellipsis(value) {
      if (value.length > 10) {
        return value.slice(0, 8) + '...'
      }
      return value
    },
    /**
     * 根据id删除
     * @param id
     */
    deleteDebit(id) {
      const data = {
        id: id,
      };
      axios({
        url: "/api/cckfs/deleteDebit",
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
        }
      })
    },
    /**
     * 初始化页面
     */
    initialIndex() {
      axios({
        url: "/api/cckfs/initialIndex",
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        let r = res.data;
        this.tbdDecimal = r.tbdDecimal
        this.ybdDecimal = r.ybdDecimal
        this.ha = r.ha
        this.memberTotal = r.memberTotal
        this.ht = r.ht
        this.totalRevenue = r.totalRevenue
        this.hp = r.hp
        this.mm = r.mm
        this.debitList = r.debitList
      })
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
    this.initialIndex()
  }
}
</script>

<style scoped>
div {
}
</style>