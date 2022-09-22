<script lang="ts" setup>
import {
  ref,
  computed,
  reactive,
  getCurrentInstance,
  watch,
  onBeforeMount
} from 'vue'
import {
  useRoute,
  onBeforeRouteUpdate
} from 'vue-router'
import axios from 'axios'
import type {
  FormInstance,
  FormRules
} from 'element-plus'
const radio1 = ref('1')
import {ElMessage} from "element-plus";
const formLabelWidth = '140px'
var data = reactive({
  contracts: [],
  shiti:[],
  total: 0,
  pageNum: 1,
  pageSize: 5,
  testname:[],
})
const form = reactive({
  testname:'',//试卷名称
  testclassification:'',//试卷分类
  questionscore:'',//试卷总分
  shuz:[],//
  shuz2:[],//
  examinationid:'',
  examname:'',
  examtype:'',
  testpaperid:'',
})
const dialogFormVisible = ref(false)
const dialogFormVisible2 = ref(false)
const a=ref("");
//页面刷新
function qx(){
  location.reload()
}
function SelectionChange(val) {
  form.shuz.length=0;
  for(var i=0;i<val.length;i++){
    form.shuz.push(val[i].testid)
  }
  console.log("数组",form.shuz)
}
function SelectionChange2(val) {
  form.shuz2.length=0;
  for (var i=0;i<val.length;i++){
    form.testpaperid=val[i].testpaperid
  }
  console.log("id", form.testpaperid)
}
onBeforeMount(() => {
  axios.get("http://localhost:8089/TestpaperController/selectsj", {
    params:{
      pageNum:1,
      pageSize:5,
    }
  }).then(function(response) {
    // console.log(response.data.data)
    data.contracts = response.data.data.list
    data.total = response.data.data.total
    // console.log(data.contracts)
  }).catch(function(error) {
    console.log(error)
  })
})
function page() {
  console.log(data.testname)
  axios.get("http://localhost:8089/TestpaperController/selectsj", {
    params: {
      testname:data.testname,
      pageNum: data.pageNum,
      pageSize: data.pageSize
    }
  }).then(function(response) {
    data.contracts = response.data.data.list
    data.total = response.data.data.total
    console.log(data.contracts)
  }).catch(function(error) {
    console.log(error)
  })
}
//查询试题
function selectst() {
  axios.get("http://localhost:8089/TquestionsController/selectst", {
  }).then(function (response) {
    //console.log(response.data.data)
    data.shiti.length = 0
    data.shiti = response.data.data
    console.log(data.shiti)
  }).catch(function (error) {
    console.log(error)
  })
}
//添加试卷
function insertsj(){
  if(this.form.testname == ""){
    ElMessage({
      message:'试卷名称不能为空',
      type:'warning'
    });
  }else if(this.form.questionscore == ""){
  ElMessage({
    message:'考试总分不能为空',
      type:'warning'
  })
  }else {
    axios.post("http://localhost:8089/TestpaperController/insertsj?sz=" + form.shuz, form, {}).then(function (a) {
      if (a.data.code != 200) {
        alert("失败")
        qx()
      }
      qx()
    }).catch(function (error) {
      console.log(error)
    })
  }
}
//添加考试
function insertks(){
  axios.post("http://localhost:8089/expapaer/insertks",form,{
  }).then(function (a){
    if(a.data.code !=200){
      alert("失败");
      qx();
    }
    qx()
  }).catch(function (error){
    console.log(error)
  })
}
</script>

<template>
<!--  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">-->
<!--    <li v-for="i in count" :key="i" class="infinite-list-item">{{ i }}</li>-->
<!--  </ul>-->
<div style="padding: 10px" id="aa">
<!--搜索功能区域-->
  <div style="position: relative;bottom: 25px">

<!--    <el-input v-model="data.testname" placeholder="试卷名称" style="width: 10%" clearable></el-input>-->
<!--    <el-button @click="page()" type="primary" style="margin-left: 5px">查询</el-button>-->
  </div>
  <!-- 功能区域 -->

    <el-button type="primary" @click="dialogFormVisible = true,selectst()" style="position: relative;">+ 添加</el-button>
    <br/>

  <div style="width: 1400px;margin-top: 25px">
    <el-table :data="data.contracts"  border stripe style="width: 99%"  @selection-change="SelectionChange2" row-key="testpaperid">
      <!-- sortable排序 -->
      <el-table-column type="selection" width="55" />
      <el-table-column type="index" label="序号" />
      <el-table-column prop="testname" label="试卷名称" width="180">
        <template #default=scope v-slot="scope">
          <span style="color: #00aaff">{{scope.row.testname}}</span>
        </template>
      </el-table-column>

      <el-table-column prop="testmethod" label="组卷方式" />
      <el-table-column prop="testclassification" label="试卷分类" />
      <el-table-column prop="questionscore" label="试卷总分" />
      <el-table-column prop="questionsnum" label="试题数量" />
      <el-table-column label="操作">
        <template #default=scope v-slot="scope">
          <!--          <el-button size="small" @click="deletcg(scope.row.postId)" :icon="Delete" circle color="red"></el-button>-->
          <el-button size="small" @click="dialogFormVisible2 = true;">创建考试</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <div style="margin:10px 0">
    <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize"
                   layout="prev,pager,next" :total="this.data.total" @current-change="page" prev-text="上一页"
                   next-text="下一页" />
    <!-- 弹窗 -->
    <el-dialog v-model="dialogFormVisible" title="创建试卷">
      <el-form ref="ruleFormRef" :model="form" :rules="rules" :inline="true"  class="demo-form-inline" :size="formSize">

        <el-form-item prop="testname" label="试卷名称" :label-width="formLabelWidth">
          <el-input v-model="form.testname" autocomplete="off" />
        </el-form-item>

        <el-form-item label="试卷分类">
          <el-select v-model="form.testclassification" placeholder="请选择">
            <el-option label="语文" value="语文"></el-option>
            <el-option label="数学" value="数学"></el-option>
          </el-select>
        </el-form-item>

        <br/>
        <a style="font-size:16px">添加试题</a>
        <br/>
<!--        <br/>-->
<!--        <el-select autocomplete="off" placeholder="试题类型" style="width: 25%" clearable>-->
<!--          <el-option label="多选题" value="多选题"></el-option>-->
<!--          <el-option label="单选题" value="单选题"></el-option>-->
<!--        </el-select>-->
<!--        &nbsp-->
<!--        <el-input  placeholder="题库搜索" style="width: 25%" clearable></el-input>-->
<!--        &nbsp-->
<!--        <el-input  placeholder="试题内容" style="width: 25%" clearable></el-input>-->
<!--        &nbsp-->
<!--        <el-button type="primary" style="margin-left: 5px">查询</el-button>-->
        <br/>
<!--        <el-scrollbar height="300px">-->
        <div style="width: 800px;margin-top: 25px">
          <el-table :data="data.shiti" height="300" border stripe style="width: 99%" @selection-change="SelectionChange" row-key="testid">
            <!-- sortable排序 -->
            <el-table-column type="selection" width="55" :reserve-selection="true"/>
            <el-table-column type="index" label="序号" />
            <el-table-column prop="tqtype" label="试题类型" />
            <el-table-column prop="tcontent" label="试题内容" />
            <el-table-column prop="qbname" label="所属题库" />
            <el-table-column prop="tqlevel" label="级别" />
            <el-table-column prop="tqtime" label="创建时间" />
          </el-table>
        </div>
<!--        </el-scrollbar>-->
        <br/>
       <el-form-item prop="questionscore" label="试卷总分" :label-width="formLabelWidth">
        <el-input v-model="form.questionscore" autocomplete="off" />
      </el-form-item>
        <br/>
        <br/>
        <el-form-item style="position: relative;top: 0px;">
          <el-button type="primary" @click="insertsj(form);"  :validate-event="false">确认</el-button>
          <el-button @click="dialogFormVisible = false,qx()">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
<!--  弹窗2  -->
    <el-dialog v-model="dialogFormVisible2" title="创建考试">
      <el-form ref="ruleFormRef" :model="form" :rules="rules" :inline="true"  class="demo-form-inline" :size="formSize">

        <el-form-item prop="examname" label="考试名称" :label-width="formLabelWidth">
          <el-input v-model="form.examname" autocomplete="off" />
        </el-form-item>

        <el-form-item label="考试类别">
          <el-select v-model="form.examtype" placeholder="请选择">
            <el-option label="正式" value="正式"></el-option>
            <el-option label="模拟" value="模拟"></el-option>
          </el-select>
        </el-form-item>
        <br/>
        <br/>
        <el-form-item style="position: relative;top: 0px;">
          <el-button type="primary" @click="insertks(form);qx()"  :validate-event="false">确认</el-button>
          <el-button @click="dialogFormVisible = false,qx()">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</div>
</template>

<style scoped>
#aa{
margin-top: 150px;
margin-left: 200px;
}
</style>