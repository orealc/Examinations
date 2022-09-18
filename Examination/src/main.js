import {
    createApp
} from 'vue'
import App from './App.vue'
import './assets/css/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import zhCn from 'element-plus/lib/locale/lang/zh-cn' //把默认的英文改成中文
import router from './router'
createApp(App).use(VueAxios, axios).use(router).use(ElementPlus, {
    locale: zhCn,
}).mount('#app')
