import {
    createRouter, createWebHashHistory,
    createWebHistory
} from 'vue-router'
import LoginView from '../components/LoginView.vue'
import zhuce from '../components/zhuce.vue'
import yuangong from "../components/yuangong.vue";
import monikaos from "../components/monikaos.vue";
import tiku from "../components/tiku.vue"
import ShiJuan from "../components/ShiJuan.vue"
import KaoShi from "../components/KaoShi.vue"
import DaTi from "../components/DaTi.vue";
import Tikuxq from "../components/Tikuxq.vue"
const modules =
    import.meta.glob('../components/**/*.vue');

const routes = [{
    path: '/',
    name: 'LoginView',
    component: LoginView
},
    {
        path: '/zhuce',
        name: 'zhuce',
        component: zhuce,

    },
    {path:"/yg",component: yuangong},
    {path: "/monikaos",component: monikaos},
    {path: "/tiku",component: tiku},
    {
        path: '/ShiJuan',
        name: 'ShiJuan',
        component: ShiJuan,

    },
    {
        path: '/KaoShi',
        name: 'KaoShi',
        component: KaoShi,

    },
    {path: "/DaTi",component: DaTi},
    {path: "/Tikuxq",component: Tikuxq}

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})


export default router



