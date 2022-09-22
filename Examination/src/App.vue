<script setup>
    import {
        h,
        ref,
        reactive,
        onBeforeMount,
        onMounted,
        getCurrentInstance
    } from 'vue'
    /*import emp from "/pubjs/"*/
    import {
        useStore
    } from "vuex"
    import {
        useRouter,
        useRoute
    } from 'vue-router'
    import axios from "axios"
    import {ElMessage} from "element-plus";
    const store = useStore()
    const router = useRouter()
    const asideMenus = reactive({
        aside: []
    })
    const route = useRoute()
    onBeforeMount(() => {
        //全局监听，页面刷新的时候将store里state的值存到sessionStorage中，
        //然后从sessionStorage中获取，再赋值给store。然后再把session里面
        //存的删除即可，相当于中间件的作用。在页面加载时读取sessionStorage
        //里的状态信息
        if (sessionStorage.getItem("state")) {
            //替换 store 的根状态，状态合并
            //Object.assign方法用于对象的合并，将源对象（source）的所有可枚举属性
            //，复制到目标对象（target）。Object.assign(target, source1, source2);
            var obj = JSON.parse(sessionStorage.getItem("state"))
            console.log("obj=%o", obj)
            if (obj.userName == "") {
                return
            }
            console.log("------------------------------------------------------------")
            store.replaceState(
                Object.assign({},
                    store.state,
                    JSON.parse(sessionStorage.getItem("state"))
                )
            )
        }
    })
</script>
<template>
    <div id="login">
        <router-view></router-view>
    </div>
</template>
<script>
    export default {
        name: "LoginView",
        methods: {
            menuClick(){
                this.$router.push("/LoginView");
            }
        }
    }
</script>
<style>
    *{
        padding: 0px;
        margin: 0px;
    }
    #login{
       /* background-image: url("");*/
        background: url("../src/assets/logo.png") no-repeat;
        background-size: 100% 100%;
        width: 100vw;
        height: 100vh
    }
</style>