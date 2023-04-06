import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import axios from 'axios'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
axios.defaults.withCredentials=true;
createApp(App).use(router).use(ElementPlus, {
    locale: zhCn,
}).mount('#app')
