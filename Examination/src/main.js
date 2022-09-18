import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import VueAxios from 'vue-axios'
import router from "./router";
createApp(App).use(ElementPlus).use(router).use(VueAxios, axios).mount('#app')
