import {
    createRouter, createWebHashHistory,
    createWebHistory
} from 'vue-router'
import LoginView from '../components/LoginView.vue'
import zhuce from '../components/zhuce.vue'
import yuangong from "../components/yuangong.vue";
import HelloWorld from "../components/monikaos.vue";
import tiku from "../components/tiku.vue"
import ShiJuan from "../components/ShiJuan.vue"
import KaoShi from "../components/KaoShi.vue"
import DaTi from "../components/DaTi.vue";
import HomeView from '../view/HomeView.vue';
import insertk from '../view/insertk.vue';
import testgl from "../view/testgl.vue";
import instest from "../view/instest.vue";

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
    {path: "/yg", component: yuangong},
    {path: "/HelloWorld", component: HelloWorld},
    {path: "/tiku", component: tiku},
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
    {path: "/DaTi", component: DaTi},
    {
        path: '/',
        name: 'HomeView',
        component: HomeView
    },
    {
        path: '/insertk',
        name: 'insertk',
        component: insertk,

    },
    {
        path: "/testgl",
        component: testgl
    },
    {
        path: "/instest",
        component: instest
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})


export default router



