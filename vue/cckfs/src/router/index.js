//该文件专门用于创建整个应用的路由器
// import * as VueRouter from "vue-router";
//引入组件
import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import Menu from "@/components/mian/Menu.vue";
import BillList from "@/components/mian/BillList.vue";
import Revenue from "@/components/mian/Revenue.vue";
import Expenditure from "@/components/mian/Expenditure.vue";
import MemberList from "@/components/mian/MemberList.vue";
import DebitList from "@/components/mian/DebitList.vue";
import Index from "@/components/Index.vue";

const router = createRouter({
    history: createWebHistory(),
    // history: createWebHashHistory(),
    routes: [
        {
            path:'/',
            redirect:'/login'　　//默认显示
        },
        {
            path: "/login",
            component: Login
        },
        {
            path: "/menu",
            name:"/Menu",
            component: Menu
        },
        {
            path: "/ac",
            component: Index
        }, {
            path: "/bill-list",
            component: BillList
        },
        {
            path: "/revenue",
            component: Revenue
        }, {
            path: "/expenditure",
            component: Expenditure
        }, {
            path: "/memberList",
            component: MemberList
        }, {
            path: "/debitList",
            component: DebitList
        },

    ]
});
export default router;
