<template>
    <div style="background-color: #f9f9f9;width: 83vw;height: 86vh;margin-left: -20px;margin-top: -20px" class="ab">
        <h1 style="font-size: 29px;color: #00aaff;" >个人信息</h1>
        <el-dialog v-model="dialogFormVisible" width="70%" height="1050px">
            <h1 style="margin-left: 220px">修改密码</h1>
            <el-form label-width="100px" style="margin-top: 30px"   >
                <el-form-item label="输入密码" prop="userPass" style="font-weight: bold">
                    <el-input v-model="data.password" type="password" />
                </el-form-item>
                <el-form-item label="确认密码" prop="userPass2" style="font-weight: bold">
                    <el-input v-model="data.password2" type="password" />
                </el-form-item>
                <el-form-item  prop="userPass">
                    <el-button type="primary" @click="">确认</el-button>
                </el-form-item>
            </el-form>
    </el-dialog>

        <div style="background-color: sandybrown;float:left;height: 250px;width: 390px;margin-left: 70px;border-radius:30px;">
            <h1 style="margin-left: 150px"> 账号信息</h1>
            <el-form label-width="100px" style="margin-top: 30px" :model="data.tableData"  >
                <el-form-item label="账号:" prop="userName" style="font-weight: bold">
                    <el-input v-model="data.tableData.userName" style="width: 80px;" disabled />
                </el-form-item>
                <el-form-item label="真实姓名:" prop="realname" style="font-weight: bold;float: left">
                    <el-input v-model="data.tableData.realname" style="width: 80px;" disabled />
                </el-form-item>
                <el-form-item label="部门:" prop="deptname" style="font-weight: bold;float: left">
                    <el-input v-model="data.tableData.deptname" style="width: 80px;" disabled />
                </el-form-item>
                <el-form-item label="手机号:" prop="phone" style="font-weight: bold;float: left">
                    <el-input v-model="data.tableData.phone" style="width: 80px;" disabled />
                </el-form-item>
                <el-form-item  label="职位" prop="rolename" style="font-weight: bold;float: left" >
                    <el-input v-model="data.tableData.rolename" style="width: 80px;" disabled />
                </el-form-item>
                <el-button type="primary" @click="dialogFormVisible=true" style="margin-left: 40px" >修改密码</el-button>
            </el-form>

        </div>

    </div>
</template>

<script lang="ts" setup>
    import axios from 'axios' //第一步引入axios
    import {
        reactive,
        ref,
        onBeforeMount,
    } from 'vue'
    import {
        useStore
    } from "vuex"
    import {ElMessage, ElMessageBox} from 'element-plus'
    const value = ref<string[]>([])
    const store = useStore()
    const dialogFormVisible = ref(false)
    const formSize = ref('default')
    const data = reactive({
        tableData: [],
        userid:store.state.userInfo.userid,
        userPass:''

    })
    const ruleForm = reactive({
        ckdd:'',//自动生成审核订单号
        password:'',
        password2:''
    })
       //根据状态查询
    onBeforeMount(() => {
        axios.get("http://localhost:8082/usercontroller/usercx", {
            params:{
                userid: data.userid
            }, headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function(response) {
            // console.log(response.data.data)
            data.tableData = response.data.data
            console.log("数据"+response.data.data)
        }).catch(function(error) {
            console.log(error)
        })
    })
    function xiugaipass() {
        axios.get("http://localhost:8080/crm/Sh/selectqx6",{
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            console.log(response.data.data)

        }).catch(function (error) {
            console.log(error)
        })
    }
</script>

<style>
    .ab{
        padding: 0px;
        margin: 0px;
    }
</style>