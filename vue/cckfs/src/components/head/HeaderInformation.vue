<template>
  <!--头部信息-->
  <header class="lyear-layout-header">

    <nav class="navbar">

      <div class="navbar-left">
        <div class="lyear-aside-toggler">
          <span class="lyear-toggler-bar"></span>
          <span class="lyear-toggler-bar"></span>
          <span class="lyear-toggler-bar"></span>
        </div>
      </div>

      <ul class="navbar-right d-flex align-items-center">
        <!--End 顶部消息部分-->
        <!--切换主题配色-->
        <li class="dropdown dropdown-skin">
		    <span data-bs-toggle="dropdown" class="icon-item">
              <i class="mdi mdi-palette fs-5"></i>
            </span>
          <ul class="dropdown-menu dropdown-menu-end" data-stopPropagation="true">
            <li class="lyear-skin-title"><p>主题</p></li>
            <li class="lyear-skin-li clearfix">
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_1" value="default"
                       checked="checked">
                <label class="form-check-label" for="site_theme_1"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_2"
                       value="translucent-green">
                <label class="form-check-label" for="site_theme_2"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_3"
                       value="translucent-blue">
                <label class="form-check-label" for="site_theme_3"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_4"
                       value="translucent-yellow">
                <label class="form-check-label" for="site_theme_4"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_5"
                       value="translucent-red">
                <label class="form-check-label" for="site_theme_5"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_6"
                       value="translucent-pink">
                <label class="form-check-label" for="site_theme_6"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_7"
                       value="translucent-cyan">
                <label class="form-check-label" for="site_theme_7"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="site_theme" id="site_theme_8" value="dark">
                <label class="form-check-label" for="site_theme_8"></label>
              </div>
            </li>
            <li class="lyear-skin-title"><p>LOGO</p></li>
            <li class="lyear-skin-li clearfix">
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_1" value="default"
                       checked="checked">
                <label class="form-check-label" for="logo_bg_1"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_2" value="color_2">
                <label class="form-check-label" for="logo_bg_2"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_3" value="color_3">
                <label class="form-check-label" for="logo_bg_3"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_4" value="color_4">
                <label class="form-check-label" for="logo_bg_4"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_5" value="color_5">
                <label class="form-check-label" for="logo_bg_5"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_6" value="color_6">
                <label class="form-check-label" for="logo_bg_6"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_7" value="color_7">
                <label class="form-check-label" for="logo_bg_7"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_8" value="color_8">
                <label class="form-check-label" for="logo_bg_8"></label>
              </div>
            </li>
            <li class="lyear-skin-title"><p>头部</p></li>
            <li class="lyear-skin-li clearfix">
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_1" value="default"
                       checked="checked">
                <label class="form-check-label" for="header_bg_1"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_2" value="color_2">
                <label class="form-check-label" for="header_bg_2"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_3" value="color_3">
                <label class="form-check-label" for="header_bg_3"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_4" value="color_4">
                <label class="form-check-label" for="header_bg_4"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_5" value="color_5">
                <label class="form-check-label" for="header_bg_5"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_6" value="color_6">
                <label class="form-check-label" for="header_bg_6"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_7" value="color_7">
                <label class="form-check-label" for="header_bg_7"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="header_bg" id="header_bg_8" value="color_8">
                <label class="form-check-label" for="header_bg_8"></label>
              </div>
            </li>
            <li class="lyear-skin-title"><p>侧边栏</p></li>
            <li class="lyear-skin-li clearfix">
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_1" value="default"
                       checked="checked">
                <label class="form-check-label" for="sidebar_bg_1"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_2" value="color_2">
                <label class="form-check-label" for="sidebar_bg_2"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_3" value="color_3">
                <label class="form-check-label" for="sidebar_bg_3"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_4" value="color_4">
                <label class="form-check-label" for="sidebar_bg_4"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_5" value="color_5">
                <label class="form-check-label" for="sidebar_bg_5"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_6" value="color_6">
                <label class="form-check-label" for="sidebar_bg_6"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_7" value="color_7">
                <label class="form-check-label" for="sidebar_bg_7"></label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sidebar_bg" id="sidebar_bg_8" value="color_8">
                <label class="form-check-label" for="sidebar_bg_8"></label>
              </div>
            </li>
          </ul>
        </li>
        <!--End 切换主题配色-->
        <!--个人头像内容-->
        <li class="dropdown">
          <a href="javascript:void(0)" data-bs-toggle="dropdown" class="dropdown-toggle">
            <img class="avatar-md rounded-circle" src="../../assets/images/users/avatar.jpg" alt="笔下光年"/>
            <span style="margin-left: 10px;">CCKFS</span>
          </a>
          <ul class="dropdown-menu dropdown-menu-end">
            <li>
              <a class="multitabs dropdown-item" data-url="lyear_pages_edit_pwd.html"
                 href="javascript:void(0)">
                <i class="mdi mdi-lock-outline"></i>
                <span>修改密码</span>
              </a>
            </li>
            <li class="dropdown-divider"></li>
            <li>
              <router-link  to="/"  class="dropdown-item">
                <i class="mdi mdi-logout-variant"></i>
                <span @click="exit()">退出登录</span>
              </router-link>
              <!--              <a class="dropdown-item" href="HelloWorld.vue">-->
              <!--                <i class="mdi mdi-logout-variant"></i>-->
              <!--                <span>退出登录</span>-->
              <!--              </a>-->
            </li>
          </ul>
        </li>
        <!--End 个人头像内容-->
      </ul>

    </nav>

  </header>
  <!--End 头部信息-->
</template>

<script>
import axios from "axios";

export default {
  name: "HeaderInformation",
  methods: {
    exit(){
      axios({
        url: "/api/logout",
        method: 'get',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>

</style>