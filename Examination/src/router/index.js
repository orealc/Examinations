import {
    createRouter, createWebHashHistory,
    createWebHistory
} from 'vue-router'
import LoginView from '../components/LoginView.vue'
import zhuce from '../components/zhuce.vue'
import yuangong from "../components/yuangong.vue";
import HelloWorld from "../components/monikaos.vue";
import tiku from "../components/tiku.vue"
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
    {path:"/",component: yuangong},
    {path: "/HelloWorld",component: HelloWorld},
    {path: "/tiku",component: tiku}

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})


export default router



