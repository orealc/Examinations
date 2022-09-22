import {
    createRouter, createWebHashHistory,
} from 'vue-router'
import LoginView from '../components/LoginView.vue';
import zhuce from '../components/zhuce.vue';
import yuangong from "../components/yuangong.vue";
import monikaos from "../components/monikaos.vue";
import tiku from "../components/tiku.vue"
import ShiJuan from "../components/ShiJuan.vue"
import KaoShi from "../components/KaoShi.vue"
import HelloWorld from "../components/monikaos.vue";
import DaTi from "../components/DaTi.vue";
import Tikuxq from "../components/Tikuxq.vue"
import HomeView from '../components/HomeView.vue';
import insertk from '../components/insertk.vue';
import testgl from "../components/testgl.vue";
import instest from "../components/instest.vue";
import toubu from "../components/toubu.vue";
import genrenxx from "../components/genrenxx.vue";
import yuangxx from "../components/yuangxx.vue";
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
    {path: "/monikaos",component: monikaos},
    {path: "/tiku",component: tiku},
    {
        path:"/yg",
        name: "yg",
        component: yuangong},
    {
        path: "/HelloWorld",
        name:"HelloWorld",
        component: HelloWorld},
    {
        path: "/tiku",
        component: tiku,
        name: 'tiku',},
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
    {path: "/DaTi",component: DaTi,
        name: 'DaTi',},
    {path: "/Tikuxq",component: Tikuxq,
        name: 'Tikuxq'},
    {
        path: '/toubu',
        name: 'toubu',
        component: toubu,
        children: [
            {
                path: '/genrenxx',
                name: 'genrenxx',
                component: genrenxx,
            },
            {
                path: '/HomeView',
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
                name: 'testgl',
                component: testgl
            },
            {
                path: "/instest",
                name: "instest",
                component: instest
            },
            {
                path: '/KaoShi',
                name: 'KaoShi',
                component: KaoShi,

            },
            {
                path: '/ShiJuan',
                name: 'ShiJuan',
                component: ShiJuan,

            }, {
                path: '/yuangxx',
                name: 'yuangxx',
                component: yuangxx,
            }/*,
            {
                path: '/QuestionManagement',
                name: '试题管理',
                component: QuestionManagement,
            },
            {
                path: '/TestQuestionManagement',
                name: '题库管理',
                component: TestQuestionManagement,
            }*/
        ]


    },

    {path: "/DaTi", component: DaTi},


]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})


export default router



