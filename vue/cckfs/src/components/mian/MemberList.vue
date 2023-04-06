<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <header class="card-header">
            <div class="card-title">会员列表<span style="font-size: 14px">&nbsp;&nbsp;&nbsp;共{{ total }}条</span></div>
          </header>
          <div class="card-body">
            <div style="display: flex;" class="card-btns mb-2-5">
              <button @click="restore()" class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
                      data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">新增会员
              </button>
              <form class="form" onsubmit="return false" @keyup.enter="search()">
                <label for="search">
                  <input @blur="blur()" v-model="searchContent" required="" placeholder="搜索卡号或姓名" type="text">
                  <div class="icon">
                    <svg stroke-width="2" stroke="currentColor" viewBox="0 0 24 24" fill="none"
                         xmlns="http://www.w3.org/2000/svg" class="swap-on">
                      <path d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" stroke-linejoin="round"
                            stroke-linecap="round"></path>
                    </svg>
                    <svg @click="search()" stroke-width="2" stroke="currentColor"
                         viewBox="0 0 24 24" fill="none"
                         xmlns="http://www.w3.org/2000/svg" class="swap-off">
                      <path d="M10 19l-7-7m0 0l7-7m-7 7h18" stroke-linejoin="round" stroke-linecap="round"></path>
                    </svg>
                  </div>
                  <button type="reset" class="close-btn">
                    <svg @click="clearInput()" viewBox="0 0 20 20" class="h-5 w-5" xmlns="http://www.w3.org/2000/svg">
                      <path clip-rule="evenodd"
                            d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                            fill-rule="evenodd"></path>
                    </svg>
                  </button>
                </label>
              </form>
            </div>
            <div class="table-responsive">
              <table style="text-align: center;" class="table table-bordered">
                <thead>
                <tr>
                  <th>编号</th>
                  <th>充值日期</th>
                  <th>卡号</th>
                  <th>姓名</th>
                  <th>金额</th>
                  <th>手机号</th>
                  <th>备注</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(m,index) in memberData" :key="index">
                  <td>{{ index + 1 }}</td>
                  <td>{{ m.date }}</td>
                  <td>{{ m.cardNumber }}</td>
                  <td>{{ m.name }}</td>
                  <td>{{ m.amount }}</td>
                  <td>{{ m.userTel }}</td>
                  <td>{{ m.remark }}</td>
                  <td>
                    <div class="btn-group btn-group-sm">
                      <a class="btn btn-default" href="#!" data-bs-toggle="offcanvas"
                         @click="echoData(m.id,m.date,m.cardNumber,m.name,m.amount,m.userTel,m.remark)"
                         data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling" title="编辑">
                        <i class="mdi mdi-pencil"></i>
                      </a>
                      <el-popconfirm
                          confirm-button-text="Yes"
                          cancel-button-text="No"
                          icon-color="#626AEF"
                          title="确认删除后将无法恢复！"
                          @confirm="deleteMember(m.id)"
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
            <div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
                 id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
              <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasScrollingLabel">{{ title }}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
              </div>
              <div class="offcanvas-body">
                <!--表单-->
                <div class="mb-3">
                  <label for="充值日期">充值日期</label>
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
                  <label for="卡号">卡号</label>
                  <input v-model="cardNumber" id="卡号" class="form-control" type="text" placeholder="卡号">
                </div>
                <div class="mb-3">
                  <label for="姓名">姓名</label>
                  <input v-model="name" id="姓名" class="form-control" type="text" placeholder="姓名">
                </div>
                <div class="mb-3">
                  <label for="金额">金额</label>
                  <input v-model="amount" id="金额" class="form-control" type="number" placeholder="金额">
                </div>
                <div class="mb-3">
                  <label for="手机号">手机号</label>
                  <input v-model="userTel" id="手机号" class="form-control" type="text" placeholder="手机号">
                </div>
                <div class="mb-3">
                  <label for="备注">备注</label>
                  <textarea v-model="remark" id="备注" class="form-control" aria-atomic="false" type="text"
                            placeholder="备注"/>
                </div>
                <div class="d-grid gap-2 col-6 mx-auto">
                  <button v-if="title==='新增会员数据'" @click="confirmSubmission()" class="btn btn-primary"
                          type="button">
                    <i class="mdi mdi-checkbox-marked-circle-outline"></i>
                    确认提交
                  </button>
                  <button v-if="title==='修改会员数据'" @click="confirmModifications()" class="btn btn-primary"
                          type="button">
                    <i class="mdi mdi-checkbox-marked-circle-outline"></i>
                    确认修改
                  </button>
                </div>
              </div>
            </div>

            <ul class="pagination" v-if="searchContent===''&&total!==0">
              <el-pagination pager-count="12" background layout="prev, pager, next" v-model:page-size="pageSize"
                             :total="total" v-model:current-page="currentPage"
                             @current-change="getMemberList(currentPage,pageSize)"/>
            </ul>
            <ul class="pagination" v-if="searchContent!==''&&total!==0">
              <el-pagination pager-count="12" background layout="prev, pager, next" v-model:page-size="pageSize"
                             :total="total" v-model:current-page="currentPage"
                             @current-change="search(currentPage,pageSize)"/>
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
import {ElMessage} from "element-plus";

export default {
  name: "MemberList",
  data() {
    return {
      /**
       * 标题
       */
      title: "",
      /**
       * 会员数据
       */
      memberData: "",
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
       * id
       */
      id: "",
      /**
       * 日期
       */
      time: "",
      /**
       * 卡号
       */
      cardNumber: "",
      /**
       * 姓名
       */
      name: "",
      /**
       * 金额
       */
      amount: "",
      /**
       * 手机号
       */
      userTel: "",
      /**
       * 备注
       */
      remark: "",
      /**
       * 搜索内容
       */
      searchContent: ""
    }
  },
  methods: {
    /**
     * 清除输入内容
     */
    clearInput() {
      this.searchContent = ""
      this.getMemberList(1, this.pageSize)
    },
    /**
     * 失去焦点事件后重新请求所有内容的第一页
     */
    blur() {
      if (this.searchContent === "") {
        this.getMemberList(1, this.pageSize)
      }
    },
    search() {
      if (this.searchContent !== "") {
        const data = {
          searchContent: this.searchContent,
          currPage: this.currentPage,
          pageSize: this.pageSize
        };
        axios({
          url: "/api/cckfs/search",
          data: qs.stringify(data),
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(res => {
          this.memberData = res.data.search
          this.total = res.data.total
        })
      }
    },
    /**
     * 根据id删除
     * @param id
     */
    deleteMember(id) {
      const data = {
        id: id,
      };
      axios({
        url: "/api/cckfs/deleteMember",
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
      const data = {
        id: this.id,
        date: this.time,
        cardNumber: this.cardNumber,
        name: this.name,
        amount: this.amount,
        userTel: this.userTel,
        remark: this.remark
      };
      if (this.cardNumber !== "" && this.name !== "" && this.amount !== "" && this.amount > 0) {
        axios({
          url: "/api/cckfs/modifyMemberData",
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
     * 还原初始状态
     */
    restore() {
      this.title = "新增会员数据"
      this.id = ""
      this.time = this.getTime()
      this.cardNumber = ""
      this.name = ""
      this.amount = ""
      this.userTel = ""
      this.remark = ""
    },
    /**
     * 回显数据
     * @param id
     * @param date
     * @param cardNumber
     * @param name
     * @param amount
     * @param userTel
     * @param remark
     */
    echoData(id, date, cardNumber, name, amount, userTel, remark) {
      this.title = "修改会员数据"
      this.id = id
      this.time = date
      this.cardNumber = cardNumber
      this.name = name
      this.amount = amount
      this.userTel = userTel
      this.remark = remark
    },
    //获取当前日期
    getTime() {
      const myDate = new Date((new Date).getTime() + 8 * 60 * 60 * 1000);
      return myDate.toJSON().split('T').join(' ').substr(0, 10);
    },
    /**
     * 确认提交
     */
    confirmSubmission() {
      const data = {
        date: this.time,
        cardNumber: this.cardNumber,
        name: this.name,
        amount: this.amount,
        userTel: this.userTel,
        remark: this.remark
      };
      if (this.cardNumber !== "" || this.name !== "" || this.amount !== "") {
        axios({
          url: "/api/cckfs/addMembers",
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
   获取会员列表 页码 展示条数
     */
    getMemberList(currPage, pageSize) {
      const data = {
        currPage: currPage,
        pageSize: pageSize
      }
      axios({
        url: "/api/cckfs/getMemberList",
        data: qs.stringify(data),
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res)
        this.memberData = res.data.memberList
        // this.currPage = res.data.currPage
        this.total = res.data.total
      })
    },
  },
  mounted() {
    this.time = this.getTime()
    this.getMemberList(this.currentPage, this.pageSize)
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

/* From uiverse.io by @satyamchaudharydev */
/* this button is inspired by -- whatsapp input */
/* == type to see fully interactive and click the close buttom to remove the text  == */

.form {
  --input-bg: #FFf;
  /*  background of input */
  --padding: 1.5em;
  --rotate: 80deg;
  /*  rotation degree of input*/
  --gap: 2em;
  /*  gap of items in input */
  --icon-change-color: #15A986;
  /*  when rotating changed icon color */
  --height: 40px;
  /*  height */
  width: 200px;
  padding-inline-end: 1em;
  /*  change this for padding in the end of input */
  background: var(--input-bg);
  position: relative;
  border-radius: 4px;
}

.form label {
  display: flex;
  align-items: center;
  width: 100%;
  height: var(--height);
}

.form input {
  width: 100%;
  padding-inline-start: calc(var(--padding) + var(--gap));
  outline: none;
  background: none;
  border: 0;
}

/* style for both icons -- search,close */
.form svg {
  /* display: block; */
  color: #111;
  transition: 0.3s cubic-bezier(.4, 0, .2, 1);
  position: absolute;
  height: 15px;
}

/* search icon */
.icon {
  position: absolute;
  left: var(--padding);
  transition: 0.3s cubic-bezier(.4, 0, .2, 1);
  display: flex;
  justify-content: center;
  align-items: center;
}

/* arrow-icon*/
.swap-off {
  transform: rotate(-80deg);
  opacity: 0;
  visibility: hidden;
}

/* close button */
.close-btn {
  /* removing default bg of button */
  background: none;
  border: none;
  right: calc(var(--padding) - var(--gap));
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #111;
  padding: 0.1em;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  transition: 0.3s;
  opacity: 0;
  transform: scale(0);
  visibility: hidden;
}

.form input:focus ~ .icon {
  transform: rotate(var(--rotate)) scale(1.3);
}

.form input:focus ~ .icon .swap-off {
  opacity: 1;
  transform: rotate(-80deg);
  visibility: visible;
  color: var(--icon-change-color);
}

.form input:focus ~ .icon .swap-on {
  opacity: 0;
  visibility: visible;
}

.form input:valid ~ .icon {
  transform: scale(1.3) rotate(var(--rotate))
}

.form input:valid ~ .icon .swap-off {
  opacity: 1;
  visibility: visible;
  color: var(--icon-change-color);
}

.form input:valid ~ .icon .swap-on {
  opacity: 0;
  visibility: visible;
}

.form input:valid ~ .close-btn {
  opacity: 1;
  visibility: visible;
  transform: scale(1);
  transition: 0s;
}

</style>