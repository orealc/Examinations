import {
    createRouter, createWebHashHistory,
    createWebHistory
} from 'vue-router'
import LoginView from '../components/LoginView.vue'
import zhuce from '../components/zhuce.vue'
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

    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})


export default router
