import {createRouter,createWebHashHistory,createWebHistory} from 'vue-router'
import Pent from "../view/Pent.vue"
import jiaofujihua from "../view/jiaofujihua.vue"

const routes=[
	{path:'/Pent',component:Pent},
	{path:'/jiaofujihua',component:jiaofujihua}
]
const router=createRouter({
	history:createWebHashHistory(),
	routes
})
export default router