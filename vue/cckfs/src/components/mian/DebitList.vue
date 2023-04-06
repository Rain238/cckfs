<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <header class="card-header">
            <div class="card-title">借记列表</div>
          </header>
          <div class="card-body">
            <div class="card-btns mb-2-5">
              <button @click="restore()" class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
                      data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">新增数据
              </button>
            </div>
            <div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
                 id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
              <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasScrollingLabel">{{ title }}</h5>
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
                  <button v-if="title==='新增借记数据'" @click="confirmSubmission()" class="btn btn-primary"
                          type="button">
                    <i class="mdi mdi-checkbox-marked-circle-outline"></i>
                    确认提交
                  </button>
                  <button v-if="title==='修改借记数据'" @click="confirmModifications()" class="btn btn-primary"
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
                <tr v-for="(d,index) in debitData" :key="index">
                  <td>{{ index + 1 }}</td>
                  <td>{{ d.date }}</td>
                  <td>{{ d.storeName1 }}</td>
                  <td><span class="badge bg-warning">借</span></td>
                  <td>{{ d.storeName2 }}</td>
                  <td @click="commodity=d.commodity"  data-bs-toggle="modal" href="#exampleModalToggle">{{ this.ellipsis(d.commodity) }}</td>
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
            <ul v-if="total!==0" class="pagination">
              <el-pagination pager-count="12" background layout="prev, pager, next" v-model:page-size="pageSize"
                             :total="total" v-model:current-page="currentPage"
                             @current-change="getDebitList(currentPage,pageSize)"/>
            </ul>
          </div>
          <!--          模态窗-->
          <div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
               tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-body">
                  {{commodity}}
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
  name: "DebitList",
  data() {
    return {
      id: "",
      /**
       * 标题
       */
      title: "",
      /**
       * 账单数据
       */
      debitData: "",
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
    }
  },
  methods: {
    ellipsis(value){
      if (value.length>10){
        return value.slice(0,8)+'...'
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
          ElMessage.error(res.data.message)
        }
      })
    },
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
            ElMessage.error(res.data.message)
          }
        })
      }
    },
    /**
     * 还原初始数据
     */
    restore() {
      this.title = "新增借记数据"
      this.time = this.getTime()
      this.storeName1 = ""
      this.storeName2 = ""
      this.commodity = ""
      this.payment = ""
      this.flag = "i"
    },
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
    /**
     * 状态选择
     * @param flag
     */
    select(flag) {
      this.flag = flag
    },
    /**
     * 确认提交
     */
    confirmSubmission() {
      if (this.storeName1 !== "" && this.storeName2 !== "" && this.commodity !== "") {
        const data = {
          date: this.time,
          storeName1: this.storeName1,
          storeName2: this.storeName2,
          commodity: this.commodity,
          payment: this.payment,
          flag: this.flag,
        }
        axios({
          url: "/api/cckfs/addDebit",
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
    /*
    获取当前日期
     */
    getTime() {
      const myDate = new Date((new Date).getTime() + 8 * 60 * 60 * 1000);
      return myDate.toJSON().split('T').join(' ').substr(0, 10);
    },
    /*
    获取账单列表 页码 展示条数
    */
    getDebitList(currPage, pageSize) {
      const data = {
        currPage: currPage,
        pageSize: pageSize
      }
      axios({
        url: "/api/cckfs/getDebitList",
        data: qs.stringify(data),
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res)
        this.debitData = res.data.debitList
        // this.currPage = res.data.currPage
        this.total = res.data.total
      })
    },
  },
  mounted() {
    this.time = this.getTime()
    this.getDebitList(this.currentPage, this.pageSize)
  }
}
</script>

<style scoped>

</style>