import { createApp } from 'vue'
import 'element-plus/dist/index.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
import axios from 'axios'
import VueAxios from 'vue-axios'
import * as ElIcons from '@element-plus/icons-vue'
import router from './router'
import store from "./store";
const app=createApp(App).use(ElementPlus).use(router).use(store).use(VueAxios, axios)
// 统一注册el-icon图标
for(let iconName in ElIcons){
    app.component(iconName,ElIcons[iconName])
}
app.mount('#app')