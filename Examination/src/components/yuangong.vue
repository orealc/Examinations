<template>
   <!--主div-->
   <div style="width: 1530px;height: 715px;background-color: #ECF2F8;position: absolute;left: 0px;top: 0px">
      <!--头部-->
      <div style="width: 100%;height: 60px;background-color: #4377FB;">
         <!--图标-->
         <div style="width: 200px;height: 60px;margin-left:150px">
            <img src="src/assets/imgs/云帆.png" style="width: 200px;height: 60px;">
         </div>
         <!--ul-->
         <div style="width: 400px;height: 60px;position: relative;top: -63px;left: 500px; ">
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
         <div style="width: 200px;height: 60px;position: relative;top: -123px;left: 1220px; ">
            <ul>
               <li>
                  <el-dropdown>
    <span class="el-dropdown-link">
     {{store.state.userInfo.username}}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
                     <template #dropdown>
                        <el-dropdown-menu>
                           <el-dropdown-item><span @click="dialogTableVisible = true,selectjl()">考试记录</span></el-dropdown-item>
                        </el-dropdown-menu>
                     </template>
                  </el-dropdown>
               </li>
               <li>
                  退出
               </li>
            </ul>

         </div>
      </div>
      <!--主体-->
      <div style="width: 80%;height: 520px;margin:0 auto;position: relative;top: 40px;background-color: white">
         <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
            <el-tab-pane label="正式考试" name="first">
               <div >
                  <el-input
                          v-model="data.examname"
                          size="large"
                          placeholder="搜索考试名称"
                          :suffix-icon="Search"
                          style="width: 200px;position: relative;left: 60px;"
                  />
                  <el-button type="primary" style="margin-left: 70px;" @click="page()">搜索</el-button>
               </div>
               <div style="width: 95%;height: 370px;border: 1px solid blue;margin:0px auto;position: relative;top: 10px;">
                  <el-table
                          :data="data.shuju"
                          style="width: 100%"
                  >
                     <el-table-column prop="examinationid" label="序列" width="180"  v-if="false">
                     </el-table-column>
                     <el-table-column prop="examname" label="考试名称" width="180">
                        <template #default=scope v-slot="scope">
                           <span style="color: #00aaff" @click="dialogFormVisible = true">{{scope.row.examname}}</span>
                        </template>
                     </el-table-column>
                     <el-table-column prop="questionscore" label="考试总分" width="180" >
                     </el-table-column>
                     <el-table-column prop="examtype" label="类型" width="180" >
                     </el-table-column>
                     <el-table-column prop="kssc" label="考试状态" />
                     <el-table-column  label="操作" width="180" >
                        <template #default=scope v-slot="scope">
                           <el-button type="primary" @click="DaTi(scope.row.examinationid)" v-if="scope.row.kssc=='进行中'">参加考试</el-button>
                           <el-button type="success" @click="DaTi(scope.row.examinationid)" v-if="scope.row.kssc=='考试完毕'">重新考试</el-button>
                        </template>
                     </el-table-column>
                  </el-table>
               </div>
            </el-tab-pane>
            <el-tab-pane label="模拟考试" name="second">
               <monikaos></monikaos>
            </el-tab-pane>
         </el-tabs>
      </div>
      <!--考试记录-->
      <el-dialog v-model="dialogTableVisible" title="考试记录">
         <el-scrollbar height="400px">
            <el-table :data="data.jl">
               <el-table-column property="recordid" label="序号" width="150" />
               <el-table-column property="realname" label="用户名" width="150" />
               <el-table-column property="testname" label="考试名称" width="200" />
               <el-table-column property="highestscore" label="考试分数" />
               <el-table-column property="recenttime" label="考试时间" v-slot="scope">
                  <span>{{scope.row.recenttime.substring(0,10)}}</span>
               </el-table-column>
            </el-table>
         </el-scrollbar>

      </el-dialog>
      <div style="width: 80%;height: 30px;margin:0 auto;position: relative;top: 60px;">
         <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize" layout="prev, pager, next"
                        :total="this.data.total" @current-change="page" prev-text="上一页" next-text="下一页" style="margin-left: 500px" />
      </div>
   </div>
</template>

<script lang="ts" setup>
   import {useStore} from "vuex"
   import axios from 'axios'
   import { ref,onBeforeMount,reactive} from 'vue'
   const handleSelect = (key: string, keyPath: string[]) => {
      console.log(key, keyPath)
   }
   import monikaos from "../components/monikaos.vue";
   const store=useStore();
   const value2 = ref('')
   const dialogFormVisible = ref(false)
   const dialogTableVisible = ref(false)
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

   const activeName = ref("first")
   var data=reactive({
      shuju:[],
      jl:"",
      pageNum: 1, //第一页
      pageSize: 4, //一页有几条数据
      total:0,
      examname:'',
   })
   import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router'
   const router=useRouter()
   function route() {
      router.push({path:"/tiku"})
   }
   function DaTi(id) {
      console.log("id",id);
      store.commit("bcid",id);
      router.push({path:"/DaTi"})
   }
   onBeforeMount(() => {
      axios.get("http://localhost:8085/StaffController/select",{
         params:{
            pageNum: data.pageNum,
            pageSize: data.pageSize,
            examname:data.examname,
         }
      }).then(function(response) {
         console.log(response.data.data)
         data.shuju=response.data.data.list;
         data.total = response.data.data.total
         console.log("数据",data.shuju)
      }).catch(function(error) {
         console.log(error)
      })
   })
   //上一页和下一页
   function page() {
      axios.get("http://localhost:8085/StaffController/select",{
         params:{
            pageNum: data.pageNum,
            pageSize: data.pageSize,
            examname:data.examname,
         }
      }).then(function(response) {
         console.log(response.data.data)
         data.shuju=response.data.data.list;
         data.total = response.data.data.total
         console.log("数据",data.shuju)
      }).catch(function(error) {
         console.log(error)
      })
   }

   function selectjl() {
      axios.get("http://localhost:8085/StaffController/selectjl",{
         params:{
            userid:store.state.userInfo.userid,
         }
      }).then(function (response) {
         console.log(response.data.data)
         data.jl=response.data.data;

      })
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