<template>
  <!-- 左侧导航 -->
  <LeftNavigation/>
  <!-- 头部信息 -->
  <HeaderInformation/>
  <!-- 页面主要内容 -->
  <MainContent/>
</template>

<script>
import MainContent from "@/components/mian/MainContent.vue";
import HeaderInformation from "@/components/head/HeaderInformation.vue";
import LeftNavigation from "@/components/navigation/LeftNavigation.vue";
import axios from "axios";
import qs from "qs";
import {ElMessage} from "element-plus";

export default {
  name: "Menu",
  components: {LeftNavigation, HeaderInformation, MainContent},
  methods: {
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
  }
}
</script>

<style scoped>

</style>