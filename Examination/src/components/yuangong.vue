<template>
   <!--主div-->
   <div style="width: 1530px;height: 715px;border: 1px solid red;background-color: #ECF2F8;position: absolute;left: 0px;top: 0px">
      <!--头部-->
      <div style="width: 100%;height: 60px;border: 1px solid black;background-color: #4377FB;">
         <!--图标-->
         <div style="width: 200px;height: 60px;border: 1px solid black;margin-left:150px">
            <img src="src/assets/imgs/云帆.png" style="width: 200px;height: 60px;">
         </div>
         <!--ul-->
         <div style="border: 1px solid black;width: 400px;height: 60px;position: relative;top: -63px;left: 500px; ">
            <ul>
               <li>
                  在线考试
               </li>
               <li>
                  <span @click="route()">题库训练</span>
               </li>
            </ul>
         </div >
         <!--个人信息-->
         <div style="border: 1px solid black;width: 200px;height: 60px;position: relative;top: -123px;left: 1220px; ">
            <ul>
               <li>
                  <el-button  @click="dddd=true,gecx()">个人信息</el-button>
                  {{store.state.userInfo.username}}

               </li>
               <li style="margin-left: 600px" id="login4" @click="qx()">
                  退出
               </li>
            </ul>

         </div>
      </div>
      <!--主体-->
      <div style="width: 80%;height: 520px;border: 1px solid black;margin:0 auto;position: relative;top: 40px;background-color: white">
         <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
            <el-tab-pane label="正式考试" name="first">
               <div >
                  <el-input
                          v-model="input1"
                          size="large"
                          placeholder="搜索考试名称"
                          :suffix-icon="Search"
                          style="width: 200px;position: relative;left: 60px;"
                  />
               </div>
               <div style="position: absolute;top: 1px;left: 300px;">
                  <el-date-picker
                          v-model="value2"
                          type="datetimerange"
                          :shortcuts="shortcuts"
                          range-separator="至"
                          start-placeholder="开始时间"
                          end-placeholder="结束时间"
                          size="large"
                  />
               </div>
               <div style="width: 95%;height: 370px;border: 1px solid blue;margin:0px auto;position: relative;top: 10px;">
                  <el-table
                          :data="tableData"
                          style="width: 100%"
                  >
                     <el-table-column prop="date" label="考试名称" width="180">
                        <template #default=scope v-slot="scope">
                           <span style="color: #00aaff" @click="dialogFormVisible = true">{{scope.row.date}}</span>
                        </template>
                     </el-table-column>
                     <el-table-column prop="name" label="时长" width="180" >
                     </el-table-column>
                     <el-table-column prop="address" label="总分" />
                     <el-table-column prop="address" label="及格分" />
                     <el-table-column prop="address" label="开放时间" />
                     <el-table-column prop="address" label="售价" />
                     <el-table-column prop="address" label="考试状态" />
                  </el-table>
               </div>

            </el-tab-pane>
            <el-tab-pane label="模拟考试" name="second">
               <Hello></Hello>
            </el-tab-pane>
         </el-tabs>
         <!--弹窗-->
         <el-dialog v-model="dialogFormVisible" title="Shipping address" width="900px">
            <el-descriptions
                    class="margin-top"
                    title="考试信息"
                    :column="3"
                    :size="size"
                    border
            >

               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">
                        考试名称
                     </div>
                  </template>
                  铁道知识
               </el-descriptions-item>
               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">

                        考试总分
                     </div>
                  </template>
                  100
               </el-descriptions-item>
               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">

                       及格分数
                     </div>
                  </template>
                  70
               </el-descriptions-item>
               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">

                        考试时长
                     </div>
                  </template>
                  <el-tag size="small">120分钟</el-tag>
               </el-descriptions-item>
               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">
                        考试类型
                     </div>
                  </template>
                 正式考试
               </el-descriptions-item>
               <el-descriptions-item>
                  <template #label>
                     <div class="cell-item">
                       注意事项
                     </div>
                  </template>
                 认真做题
               </el-descriptions-item>
            </el-descriptions>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">返回</el-button>
        <el-button type="primary" @click="dialogFormVisible = false,DaTi()"
        >开始考试</el-button
        >
      </span>
            </template>
         </el-dialog>
      </div>
      <el-dialog v-model="dddd" width="70%" height="1050px">
         <el-form label-width="100px" :model="data.tableData" >
            <el-form-item  label="账号:" prop="userName" style="font-weight: bold;float: left">
               <el-input v-model="data.tableData.userName" style="width: 150px;" disabled />
            </el-form-item>
            <el-form-item label="真实姓名:" prop="realname" style="font-weight: bold;float: left">
               <el-input v-model="data.tableData.realname" style="width: 150px;"  />
            </el-form-item>
            <el-form-item label="部门:" prop="deptname" style="font-weight: bold;float: left">
               <el-input v-model="data.tableData.deptname" style="width: 150px;" disabled />
            </el-form-item>
            <el-form-item label="手机号:" prop="phone" style="font-weight: bold;float: left">
               <el-input v-model="data.tableData.phone" style="width: 150px;"  />
            </el-form-item>
            <el-form-item  label="职位" prop="rolename" style="font-weight: bold;float: left" >
               <el-input v-model="data.tableData.rolename" style="width: 150px;" disabled />
            </el-form-item>
            <el-button type="primary" @click="xg3(),dddd=false" style="margin-left: 40px" >修改</el-button>
            <el-button type="primary" @click="xiugaimm=true" style="margin-left: 40px" >修改密码</el-button>
         </el-form>
      </el-dialog>
      <el-dialog v-model="xiugaimm" width="70%" height="1050px">
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
      <div style="width: 80%;height: 30px;border: 1px solid black;margin:0 auto;position: relative;top: 60px;">
         分页
      </div>
   </div>
</template>

<script  setup>
   import {reactive,ref,onBeforeMount} from 'vue'
   import { useRoute ,useRouter, onBeforeRouteUpdate,} from 'vue-router' //useRouter（跳转）, useRoute（获取路由参数）
   import axios from 'axios'//引入axios
   import {ElMessage, ElMessageBox} from 'element-plus'
   import {
      useStore
   } from "vuex"
   const router=useRouter()
   const store = useStore()
   const input1 = ref('')
   const value2 = ref('')
   const formSize = ref('default')
   const dialogFormVisible = ref(false)
   const dddd = ref(false)
   const xiugaimm = ref(false)
   const shortcuts = [

      {
         text: 'Last month',
         value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            return [start, end]
         },
      },

   ]
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
   function route() {
      router.push({path:"/tiku"})
   }
   function DaTi() {
      router.push({path:"/DaTi"})
   }
   function gecx() {
      axios.get("http://localhost:8082/usercontroller/usercx", {
         params: {
            userid: data.userid
         }, headers: {
            'content-type': 'application/json',
            'jwtAuth': JSON.parse(sessionStorage.getItem("state")).userInfo.token
         }
      }).then(function (response) {
         // console.log(response.data.data)
         data.tableData = response.data.data
         console.log("数据" + data.tableData.userName)
      }).catch(function (error) {
         console.log(error)
      })
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
   //修改密码
   function xiugaipass(userid) {
      if (this.data.password == null) {
         console.log("错误1")
         ElMessage({
            message: '请输入原密码!!!',
            type: 'warning'
         });
          } else if(this.data.password2 == null) {
         console.log("错误")
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
            console.log("获取的数据----------------"+data.pass.password,"获取的数据"+data.password)
            if(data.password != data.pass.password){
               ElMessage.error('原密码有误!!! ')
            } else{
               console.log("数据判断",data.password2)
               console.log("进来了")
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
</script>

<style scoped>
   ul li{
      list-style:none;
      float: left;
      margin-left: 30px;
      color: white;
   }
   #login4:hover{
      cursor:pointer

   }
</style>