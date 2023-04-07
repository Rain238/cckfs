<template>
  <div class="center-vh back">
    <div class="bg-translucent p-2">
      <div class="flex-box bg-white login-box">
        <div class="login-left p-5">
          <div class="text-center mb-3 d-sm-none">
            <a href="index.html"> <img alt="light year admin" src="../assets/images/logo.png"> </a>
          </div>
          <form onsubmit="return false" @keyup.enter="login()" method="post" class="signin-form needs-validation" novalidate>
            <div class="input-container">
              <input v-model="username" type="text" id="input" required="">
              <label for="input" class="label">Username</label>
              <div class="underline"></div>
            </div>
            <div class="input-container">
              <input v-model="password" type="password" id="input" required="">
              <label for="input" class="label">Password</label>
              <div class="underline"></div>
            </div>
            <div class="mb-3 has-feedback row">
              <div class="col-7">
                <div class="input-container">
                  <input v-model="captcha" type="text" id="input" required="">
                  <label for="input" class="label">Captcha</label>
                  <div class="underline"></div>
                </div>
              </div>
              <div class="col-5 text-right">
                <img :src="imgData" class="pull-right" id="captcha"
                     style="margin-top: 30px;cursor: pointer;"
                     @click="getCaptcha()" title="点击刷新" alt="captcha">
              </div>
            </div>
            <div class="mb-3 d-grid">
              <button @click="login()" class="btn btn-primary" type="button">蟹老板，我还要继续工作！</button>
            </div>
          </form>
        </div>
        <div class="login-right p-5 d-none d-sm-block">
          <p class="mb-3"><img src="../assets/images/logo.png" alt="logo"/></p>
          <p class="text-white">螃蟹城堡之王金融统计分析系统</p>
          <p class="text-white align-self-end">Copyright © 2023 <a target="_blank" href="#"
                                                                   class="text-white">Light Rain</a>. All right reserved
          </p>
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
  name: "Login",
  data() {
    return {
      imgData: "",
      username: "",
      password: "",
      captcha: ""
    }
  },
  methods: {
    /**
     * 获取验证码
     */
    getCaptcha() {
      axios({
        url: "/api/createKaptchaCodeImg?d=" + Math.random(),
        withCredentials: true,
        method: 'get',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.status === "SUCCESS") {
          this.imgData = res.data.message
        }
      })
    },
    /**
     * 登录提交
     */
    login() {
      const data = {
        username: this.username,
        password: this.password,
        captcha: this.captcha
      }
      axios({
        url: "/api/login",
        data: qs.stringify(data),
        withCredentials: true,
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res)
        if (res.data.status === "SUCCESS") {
          window.location.href = '/menu';
        } else {
          this.getCaptcha()
          ElMessage.error(res.data.message)
        }
      })
    },
    /**
     * session失效后重载验证码
     */
    sessionInvalid() {
      axios({
        url: "/api/session/invalid ",
        withCredentials: true,
        method: 'get',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {

      }).catch(e => {
        this.getCaptcha()
      })
    },
    //父级重定向到当前路径
    parentRedirection() {
      //让父级重定向到当前路径
      if (window !== top) top.location.href = location.href;
    }
  },
  mounted() {
    this.parentRedirection()
    this.sessionInvalid()
  }
}
</script>

<style>
.p-2 {
  opacity: 0.9;
}

.bg-white {
  --bs-bg-opacity: 0;
  border-radius: 20px 20px 20px 8px;
}

.p-5 {
  border-radius: 5px 20px 8px 5px;
}

.signin-form .has-feedback {
  position: relative;
}

.signin-form .has-feedback .form-control {
  padding-left: 36px;
}

.signin-form .has-feedback .mdi {
  position: absolute;
  top: 0;
  left: 0;
  right: auto;
  width: 36px;
  height: 36px;
  line-height: 36px;
  z-index: 4;
  color: #dcdcdc;
  display: block;
  text-align: center;
  pointer-events: none;
}

.signin-form .has-feedback.row .mdi {
  left: 15px;
}

.signin-form .form-group:last-child,
.login-right p:last-child {
  margin-bottom: 0px;
}

.login-right {
  background: #095BE3 !important;
  background: -moz-linear-gradient(45deg, #E352B5 0, #095BE3 100%) !important;
  background: -webkit-linear-gradient(45deg, #E352B5 0, #095BE3 100%) !important;
  background: linear-gradient(45deg, #E352B5 0, #095BE3 100%) !important;
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#E352B5', endColorstr='#095BE3', GradientType=1) !important;
}

.login-box {
  max-width: 700px;
}

.login-right {
  max-width: 50%;
}

.login-left {
  background: #b7c4da !important;
  background: -moz-linear-gradient(40deg, rgba(218, 120, 197, 0.35) 0, rgba(253, 252, 253, 0) 100%) !important;
  background: -webkit-linear-gradient(40deg, rgba(218, 120, 197, 0.35) 0, rgba(253, 252, 253, 0) 100%) !important;
  background: linear-gradient(40deg, rgba(218, 120, 197, 0.35) 0, rgba(253, 252, 253, 0) 100%) !important;
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='rgba(218, 120, 197, 0.35)', endColorstr='rgba(253, 252, 253, 0)', GradientType=1) !important;
}


.input-container {
  position: relative;
  margin: 12px auto;
  /*width: 200px;*/
}

.input-container input[type="text"] {
  font-size: 20px;
  width: 100%;
  border: none;
  border-bottom: 2px solid rgba(255, 255, 255, 0.7);
  padding: 5px 0;
  background-color: transparent;
  outline: none;
  color: #0754E0;
}

.input-container input[type="password"] {
  font-size: 20px;
  width: 100%;
  border: none;
  border-bottom: 2px solid rgba(255, 255, 255, 0.7);
  padding: 5px 0;
  background-color: transparent;
  outline: none;
  color: #0754E0;
}

.input-container .label {
  position: absolute;
  top: 0;
  left: 0;
  color: rgba(255, 255, 255, 0.7);
  transition: all 0.3s ease;
  pointer-events: none;
}

.input-container input[type="text"]:focus ~ .label,
.input-container input[type="text"]:valid ~ .label {
  top: -15px;
  font-size: 16px;
  color: #CB38A0;
}

.input-container input[type="password"]:focus ~ .label,
.input-container input[type="password"]:valid ~ .label {
  top: -15px;
  font-size: 16px;
  color: #CB38A0;
}

.input-container .underline {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 2px;
  width: 100%;
  background-color: #CB38A0;
  transform: scaleX(0);
  transition: all 0.3s ease;
}

.input-container input[type="text"]:focus ~ .underline,
.input-container input[type="text"]:valid ~ .underline {
  transform: scaleX(1);
}

.input-container input[type="password"]:focus ~ .underline,
.input-container input[type="password"]:valid ~ .underline {
  transform: scaleX(1);
}

.back {
  background-image: url(../assets/images/login-bg-7.jpg);
  background-size: cover;
}
</style>