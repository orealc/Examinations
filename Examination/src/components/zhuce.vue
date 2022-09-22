<template>
    <div style="border: 1px solid #4377FB;"></div>
        <div style="width: 440px;height: 370px;;margin-left: 460px;margin-top:90px;
        color: sandybrown;" id="login3" >
            <el-form label-width="100px" style="width: 380px;" v-model="loginData">
                <el-form-item label="用户名" prop="userName" style="font-weight: bold">
                    <el-input v-model="loginData.userName" />
                </el-form-item>
                <el-form-item label="手机号" prop="phone" style="font-weight: bold">
                    <el-input v-model="loginData.phone" />
                </el-form-item>
                <el-form-item label="真实姓名" prop="realname" style="font-weight: bold">
                    <el-input v-model="loginData.realname" />
                </el-form-item>
                <el-form-item label="部门" prop="deptid" style="font-weight: bold">
                    <el-select v-model="loginData.deptid" placeholder="请选择部门" @click="ddd()">
                        <el-option v-for="item in data.dept" :key="item.deptid" :label="item.deptname" :value="item.deptid"
                                   style="width: 200px"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="输入密码" prop="userPass" style="font-weight: bold">
                    <el-input v-model="loginData.password" type="password" />
                </el-form-item>
                <el-form-item label="确认密码" prop="userPass2" style="font-weight: bold">
                    <el-input v-model="loginData.password2" type="password" />
                </el-form-item>
                <el-form-item  prop="userPass">
                    <el-button type="primary" @click="zhuce(loginData)">确认</el-button>
                    <a style="color:blue;margin-top: 40px;margin-left: 130px;text-decoration:underline;" id="login4" @click="qx()" >返回登录</a>
                </el-form-item>
            </el-form>
        </div>

</template>

<script setup>
    import {reactive,ref,onBeforeMount} from 'vue'
    import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router' //useRouter（跳转）, useRoute（获取路由参数）
    import axios from 'axios'//引入axios
    import {ElMessage, ElMessageBox} from 'element-plus'
    import {
        useStore
    } from "vuex"
    const data=reactive({
        dept:[],
        ptid:'',
        deptname:''
    })
    const loginData=reactive({
        userName:'',
        password:'',
        password2:'',
        phone:'',
        deptid:'',
        deptname:'',
        realname:''
    })
    const router=useRouter()
    const store = useStore()
    function qx(){
        router.push({name:'LoginView'})
    }
    function zhuce(loginData){
        if (this.loginData.userName == "") {
            ElMessage({
                message: '账号不能为空!!!',
                type: 'warning'
            });
        }  else if (this.loginData.phone == "") {
            ElMessage({
                message: '手机号不能为空!!!',
                type: 'warning'
            });
        }else if (this.loginData.phone.length<11 || this.loginData.phone.length>11 ) {
            ElMessage({
                message: '手机号格式错误!!!',
                type: 'warning'
            });
        }else if (this.loginData.realname == "") {
            ElMessage({
                message: '真实姓名不能为空!!!',
                type: 'warning'
            });
        }else if (this.loginData.deptid == "") {
            ElMessage({
                message: '请选择部门!!!',
                type: 'warning'
            });
        } else if (this.loginData.password == 0) {
            ElMessage({
                message: '请确认密码!!!',
                type: 'warning'
            });
        }else if (this.loginData.password != this.loginData.password2) {
            ElMessage({
                message: '密码不一致!!!',
                type: 'warning'
            });
        }else {
            console.log(loginData)
            axios.post("http://localhost:8082/usercontroller/insertuser",loginData,{
                headers: {
                    'jwtAuth': store.state.token
                }
            }).then(function (a) {
                console.log(a)
                if (a.data.code != 200) {
                    ElMessage.error('注册失败')
                    /*location.reload()*/
                }
                ElMessage.success('注册成功 ')
                /*location.reload()*/
                qx()
            }).catch(function (error) {
                console.log(error)
            })
        }


    }
    //查询仓库i
    function ddd() {
        axios.get("http://localhost:8082/usercontroller/deptIdcx").then(function (a) {
            data.dept = a.data.data
        }).catch(function (error) {
            console.log(error)
        })
    }
</script>

<style scoped>
    #login3{
        background-image: url("../assets/7.jpg");

    }
    #login4:hover{
        cursor:pointer

    }
</style>