<template>
  <!--左侧导航-->
  <aside class="lyear-layout-sidebar">
    <!-- logo -->
    <div id="logo" class="sidebar-header">
      <a><img src="../../assets/images/logo-2.png" title="LightYear" alt="LightYear"/></a>
    </div>
    <div class="lyear-layout-sidebar-info lyear-scroll">

      <nav class="sidebar-main">

        <ul class="nav-drawer">
          <li class="nav-item active">
            <a class="multitabs" href="/ac" id="default-page">
              <i class="mdi mdi-home-city-outline"></i>
              <span>后台首页</span>
            </a>
          </li>
          <li class="nav-item nav-item-has-subnav">
            <a href="javascript:void(0)">
              <i class="mdi mdi-television-guide"></i>
              <span>财务列表</span>
            </a>
            <ul class="nav nav-subnav">
              <li><a class="multitabs" href="/bill-list">账单列表</a></li>
              <li><a class="multitabs" href="/revenue">收入列表</a></li>
              <li><a class="multitabs" href="/expenditure">支出列表</a></li>
            </ul>
          </li>
          <li class="nav-item active">
            <a class="multitabs" href="/memberList" id="default-page">
              <i class="mdi mdi-home-city-outline"></i>
              <span>会员列表</span>
            </a>
          </li>
          <li class="nav-item active">
            <a class="multitabs" href="/debitList" id="default-page">
              <i class="mdi mdi-home-city-outline"></i>
              <span>借记列表</span>
            </a>
          </li>
        </ul>
      </nav>

      <div class="sidebar-footer">
        <p class="copyright">
          <span>Copyright &copy; 2023. </span>
          <a target="_blank">Light Rain</a>
          <span> All rights reserved.</span>
        </p>
      </div>
    </div>

  </aside>
  <!--End 左侧导航-->
</template>

<script>
import MainContent from "@/components/mian/MainContent.vue";
import Ac from "@/components/Index.vue";

export default {
  name: "LeftNavigation",
  components: {Ac, MainContent}
}
</script>

<style scoped>

</style>