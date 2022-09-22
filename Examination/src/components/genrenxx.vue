<template>
    <div style="background-color: #f9f9f9;width: 83vw;height: 86vh;margin-left: -20px;margin-top: -20px" class="ab" >
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
                    <el-button type="primary" @click="xiugaipass(userid)">确认</el-button>
                </el-form-item>
            </el-form>
    </el-dialog>

        <div style="background-color: sandybrown;float:left;height: 488px;width: 450px;margin-left: 250px;margin-top: -8px;border-radius:30px;">
            <h1 style="margin-left: 150px"> 账号信息</h1>
            <el-form label-width="100px" style="margin-top: 30px" :model="data.tableData"  >
                <el-form-item label="账号:" prop="userName" style="font-weight: bold">
                    <el-input v-model="data.tableData.userName" style="width: 150px;" disabled />
                </el-form-item>
                <br>
                <el-form-item label="真实姓名:" prop="realname" style="font-weight: bold;">
                    <el-input v-model="data.tableData.realname" style="width: 150px;"  />
                </el-form-item>
                <br>
                <el-form-item label="部门:" prop="deptname" style="font-weight: bold;">
                    <el-input v-model="data.tableData.deptname" style="width: 150px;" disabled />
                </el-form-item>
                <br>
                <el-form-item label="手机号:" prop="phone" style="font-weight: bold;">
                    <el-input v-model="data.tableData.phone" style="width: 150px;"  />
                </el-form-item>
                <br>
                <el-form-item  label="职位" prop="rolename" style="font-weight: bold;" >
                    <el-input v-model="data.tableData.rolename" style="width: 150px;" disabled />
                </el-form-item>
                <el-button type="primary" @click="xg3()" style="margin-left: 40px" >修改</el-button>
                <el-button type="primary" @click="dialogFormVisible=true" style="margin-left: 40px" >修改密码</el-button>
            </el-form>

        </div>

    </div>
</template>

<script setup>
    import {reactive,ref,onBeforeMount} from 'vue'
    import { useRoute ,useRouter, onBeforeRouteUpdate,} from 'vue-router' //useRouter（跳转）, useRoute（获取路由参数）
    import axios from 'axios'//引入axios
    import {ElMessage, ElMessageBox} from 'element-plus'
    import {
        useStore
    } from "vuex"
    const router=useRouter()
    const store = useStore()

    const dialogFormVisible = ref(false)
    const formSize = ref('default')
    const data = reactive({
        tableData: [],
        userid:store.state.userInfo.userid,
        userPass:'',
        pass:[],
        xgmm:{
            userid:store.state.userInfo.userid,
            realname:'',
            phone:''
        }

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
            console.log("数据"+data.tableData.userName)
        }).catch(function(error) {
            console.log(error)
        })
    })
    //修改密码
    function xiugaipass(userid) {
        if (this.data.password == null) {
            ElMessage({
                message: '请输入原密码!!!',
                type: 'warning'
            });
        }  else if (this.data.password2 == null) {
            ElMessage({
                message: '请输入新密码!!!',
                type: 'warning'
            });
        }else {
            axios.get("http://localhost:8082/usercontroller/xiugai1",{
                params:{
                    userid: data.userid
                },
                headers:{
                    'content-type': 'application/json',
                    'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
                }
            }).then(function (response) {
                data.pass = response.data.data
                console.log("获取的数据----------------"+data.pass.password)
                if(data.password != data.pass.password){
                    ElMessage.error('原密码有误!!! ')
                } else {
                    axios.get("http://localhost:8082/usercontroller/upxiugai",{
                        params:{
                            userid:data.userid,
                            password:data.password2
                        },
                        headers:{
                            'content-type': 'application/json',
                            'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
                        }
                    }).then(function (response) {
                        ElMessage.success('修改成功,退出系统!!! ')
                        qx()
                    })
                }

            }).catch(function (error) {
                console.log(error)
            })
        }

    }
    function qx(){
        router.push({name:'LoginView'})
    }
    //修改角色权限
    function xg3() {
        //alert(data.dept.roleid)
        console.log("获取的id",data.xgmm.userid)
        data.xgmm.realname=data.tableData.realname
        data.xgmm.phone=data.tableData.phone
        axios.post("http://localhost:8082/usercontroller/xg3",data.xgmm,{
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            console.log("后端获取的数据"+data.realname)
            ElMessage.success('修改成功!!! ')

        })
    }
</script>

<style>
    #login3{
        background-image: url("../assets/7.jpg");

    }
    .ab{
        padding: 0px;
        margin: 0px;
    }
</style>