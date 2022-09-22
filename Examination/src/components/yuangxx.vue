<template>
    <div style="background-color: #f9f9f9;width: 83vw;height: 86vh;margin-left: -20px;margin-top: -20px" class="ab" >
        <h1 style="font-size: 29px;color: #00aaff;">员工信息</h1>
        <div style="margin: 5px;margin-left: 40px">
            &nbsp;&nbsp;<span>部门</span>&nbsp;&nbsp;
            <el-select v-model="ruleForm.deptname" placeholder="请选择" @click="ddd()" clearable>
                <el-option v-for="item in data.dept" :key="item.deptid" :label="item.deptname" :value="item.deptname"
                           style="width: 200px"/>
            </el-select>
            <span>名字</span>&nbsp;&nbsp;
            <el-input placeholder="请输入名字" style="width: 20%;" v-model="ruleForm.realname" clearable />
            <el-button type="primary" style="margin-left: 5px;" @click="ticx()">
                <el-icon>
                    <search/>
                </el-icon>
                查询
            </el-button>

        </div>
        <div>
            <el-table ref="multipleTable" :data="data.usercx2" tooltip-effect="dark"
                      @selection-change="handleSelectionChange">
                <el-table-column prop="deptname" label="部门" width="200" />
                <el-table-column prop="rolename" label="职位" width="200" />
                <el-table-column prop="realname" label="名字" width="200" />
                <el-table-column prop="phone" label="手机号" width="200" />
                <el-table-column fixed="right" label="操作" width="200">
                    <template #default="scope" v-slot="scope">
                        <el-button link type="primary" size="small" @click="dialogFormVisible=true,ticx1(scope.row.realname)">设置权限</el-button>
                        <el-button link type="primary" size="small" @click="dialogFormVisible2=true,ticx1(scope.row.realname)" >更改部门</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-pagination background v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize"
                       layout="prev,pager,next" :total="this.data.total" @current-change="page" prev-text="上一页"
                       next-text="下一页" style="padding-left: 450px;padding-top: 5px;margin-top: 10px;position: fixed"  />
        <el-dialog v-model="dialogFormVisible" width="70%" height="1050px">
            <h1>设置权限</h1>
            <el-form ref="ruleFormRef" :model="data.dept" :rules="rules" label-width="120px" :size="formSize"
                     :inline="true" class="demo-form-inline">
                <el-form-item label="姓名" prop="data.cx.proId">
                    <el-input v-model="data.dept.realname" disabled  />
                </el-form-item>
                <el-form-item label="角色职位" prop="data.cx.proId">
                    <el-select v-model="data.dept.rolename" placeholder="请选择" @click="aaa()" clearable>
                        <el-option v-for="item in data.role" :key="item.roleid" :label="item.rolename" :value="item.roleid"
                                   style="width: 200px"/>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="dialogFormVisible = false,xg(),cx()">
                        确认修改
                    </el-button>
                </el-form-item>
            </el-form>&nbsp;
        </el-dialog>
        <el-dialog v-model="dialogFormVisible2" width="70%" height="1050px">
            <h1>设置权限</h1>
            <el-form ref="ruleFormRef" :model="data.dept" :rules="rules" label-width="120px" :size="formSize"
                     :inline="true" class="demo-form-inline">
                <el-form-item label="姓名" prop="data.cx.proId">
                    <el-input v-model="data.dept.realname" disabled  />
                </el-form-item>
                <el-form-item label="部门" prop="data.cx.proId">
                    <el-select v-model="data.dept.deptname" placeholder="请选择" @click="ddd()" clearable>
                        <el-option v-for="item in data.dept" :key="item.deptid" :label="item.deptname" :value="item.deptid"
                                   style="width: 200px"/>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="dialogFormVisible2 = false,xg2(),cx()">
                        确认修改
                    </el-button>
                </el-form-item>
            </el-form>&nbsp;
        </el-dialog>
    </div>

</template>

<script setup>
    import axios from 'axios' //第一步引入axios
    import {
        EditPen,
        Search,
        RefreshRight
    } from '@element-plus/icons-vue'
    import {
        reactive,
        ref,
        onBeforeMount
    } from 'vue'
    import { ElMessage, ElMessageBox } from 'element-plus'
    import {
        useStore
    } from "vuex"
    import {useRouter} from "vue-router";
    const store = useStore()
    const router=useRouter()
    const radio = ref('selected and disabled')
    const formSize = ref('default')
    const dialogFormVisible = ref(false)
    const dialogFormVisible2 = ref(false)
    const data = reactive({
        usercx2: [],
        userid:store.state.userInfo.userid,
        userPass:'',
        pass:[],
        pageNum: 1,
        pageSize: 10,
        total: '',
        deptname:'',
        rolename:'',
        userName:'',
        phone:'',
        dept:[],
        realname:'',
        deptid:'',
        roleid:'',
        role:[],
        xgmm:{
            realname:'',
            roleid:''
        }
    })
    const ruleForm = reactive({
        deptname:'',
        realname:'',
        userName:'',
        phone:''
    })
    function gb(){
        dialogFormVisible = ref(false)
    }
    onBeforeMount(() => {
        axios.get("http://localhost:8082/usercontroller/usercx2", {
            params:{
                pageNum:1,
                pageSize:10,
            },
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function(response) {
            console.log("进来了")
            data.usercx2 = response.data.data.list
            data.total = response.data.data.total
            console.log("查询出的数据"+response.data.data+"查询出的数组"+data.usercx2)
        }).catch(function(error) {
            console.log(error)
        })
    })
    //查询
    function cx() {
        axios.get("http://localhost:8082/usercontroller/usercx2", {
            params:{
                pageNum: data.pageNum,
                pageSize: data.pageSize
            },
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            data.usercx2 = response.data.data.list
            data.total = response.data.data.total
            console.log("没有数据"+data.tableData)
        }).catch(function (error) {
            console.log(error)
        })
    }
    //分页
    function page() {
        axios.get("http://localhost:8082/usercontroller/usercx2", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize
            },
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            data.usercx2 = response.data.data.list
            data.total = response.data.data.total
            console.log("没有数据"+data.tableData)
        }).catch(function (error) {
            console.log(error)
        })
    }

    //查询部门
    function ddd() {
        axios.get("http://localhost:8082/usercontroller/deptIdcx").then(function (a) {
            data.dept = a.data.data
        }).catch(function (error) {
            console.log(error)
        })
    }
    //条件查询员工信息
    function ticx() {
        console.log("名字",ruleForm.deptname)
        axios.get("http://localhost:8082/usercontroller/usercx3",{
            params:{
                realname:ruleForm.realname,
                deptname:ruleForm.deptname,
                pageNum: data.pageNum,
                pageSize: data.pageSize
            },
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            if(ruleForm.rolename== "" || ruleForm.deptname=="" ){
                cx()
            }else {
                console.log("名字"+ruleForm.rolename)
                console.log("部门"+ruleForm.deptname)
                data.usercx2 = response.data.data
                console.log("条件查询数据"+data.dept+"条件查询"+response.data.date)
            }

        }).catch(function (error) {
            console.log(error)
        })
    }
    //条件查询员工信息
    function ticx1(realname) {
        console.log("名字",ruleForm.realname)
        axios.get("http://localhost:8082/usercontroller/usercx4",{
            params:{
                realname: realname
            },
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
                console.log("名字"+ruleForm.rolename)
                data.dept = response.data.data
                console.log("条件查询数据"+data.dept+"条件查询"+response.data.date)
        }).catch(function (error) {
            console.log(error)
        })
    }
    //查询角色
    function aaa() {
        axios.get("http://localhost:8082/usercontroller/rolecx",{
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (a) {
            data.role = a.data.data
        }).catch(function (error) {
            console.log(error)
        })
    }
    //修改角色权限
    function xg() {
        //alert(data.dept.roleid)
        data.xgmm.realname=data.dept.realname
        data.xgmm.roleid=data.dept.roleid
        axios.post("http://localhost:8082/usercontroller/xg",data.xgmm,{
            headers:{
                'content-type': 'application/json',
                'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
            }
        }).then(function (response) {
            console.log("后端获取的数据"+data.realname)
            ElMessage.success('修改成功!!! ')

        })
    }
    //修改角色权限
    function xg2() {
        //alert(data.dept.roleid)
        data.xgmm.realname=data.dept.realname
        data.xgmm.roleid=data.dept.roleid
        axios.post("http://localhost:8082/usercontroller/xg2",data.xgmm,{
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
    .ab{
        padding: 0px;
        margin: 0px;
    }
</style>
