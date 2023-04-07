<template>
  <!--页面主要内容-->
  <main class="lyear-layout-content">
    <div id="iframe-content">
    </div>
  </main>
  <!--End 页面主要内容-->
</template>

<script>
import axios from "axios";

export default {
  name: "MainContent",
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