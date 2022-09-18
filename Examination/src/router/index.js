import {createRouter,createWebHashHistory,createWebHistory} from 'vue-router'
import yuangong from "../components/yuangong.vue";
import HelloWorld from "../components/monikaos.vue";
import tiku from "../components/tiku.vue"
const routes=[
    {path:"/",component: yuangong},
{path: "/HelloWorld",component: HelloWorld},
    {path: "/tiku",component: tiku}

]
const router=createRouter({
    history:createWebHashHistory(),
    routes
})
export default router