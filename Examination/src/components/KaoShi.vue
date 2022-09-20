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

var data = reactive({
  contracts: [],
  shijuan:[],
  total: 0,
  pageNum: 1,
  pageSize: 5,
})
const form = reactive({

})
const dialogFormVisible = ref(false)

const a=ref("");
onBeforeMount(() => {
  axios.get("http://localhost:8089/expapaer/selectks", {
  }).then(function(response) {
    console.log(response.data.data)
    data.contracts = response.data.data
    data.total = response.data.data.total
    console.log(data.contracts)
  }).catch(function(error) {
    console.log(error)
  })
})
//查询试卷
function selectsj() {
  axios.get("http://localhost:8089/TestpaperController/selectsj", {}).then(function (response) {
    console.log(response.data.data)
    data.shijuan = response.data.data
    data.total = response.data.data.total
    console.log(data.contracts)
  }).catch(function (error) {
    console.log(error)
  })
}

</script>

<template>
  <div style="padding: 10px" id="aa">
    <!--搜索功能区域-->
    <div style="position: relative;bottom: 25px">


      <el-select v-model="a" autocomplete="off" placeholder="考试类型" style="width: 15%" clearable>
        <el-option label="正式考试" value="正式考试"></el-option>
        <el-option label="模拟考试" value="模拟考试"></el-option>
      </el-select>
      &nbsp
      <el-input placeholder="考试名称" style="width: 15%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <!-- 功能区域 -->

    <el-button type="primary" @click="dialogFormVisible = true;selectsj()" style="position: relative;">+ 添加</el-button>


    <div style="width: 1400px;margin-top: 25px">
      <el-table :data="data.contracts"  border stripe style="width: 99%" >
        <!-- sortable排序 -->
        <el-table-column type="selection" width="55" />
        <el-table-column type="index" label="序号" />
        <el-table-column prop="examname" label="考试名称" width="180">
          <template #default=scope v-slot="scope">
            <span style="color: #00aaff">{{scope.row.examname}}</span>
          </template>
        </el-table-column>

        <el-table-column prop="examtype" label="考试类型" />
        <el-table-column prop="questionscore" label="试卷总分" />
        <el-table-column prop="kssc" label="考试时长" />
        <el-table-column label="操作">
          <template #default=scope v-slot="scope">
            <!--          <el-button size="small" @click="deletcg(scope.row.postId)" :icon="Delete" circle color="red"></el-button>-->
            <el-button size="small" @click="showStaffForm = true;">分析</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="margin:10px 0">
      <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize"
                     layout="prev,pager,next" :total="this.data.total" @current-change="page" prev-text="上一页"
                     next-text="下一页" />
      <!-- 弹窗 -->
      <el-dialog v-model="dialogFormVisible" title="创建考试">
        <el-form ref="ruleFormRef" :model="form" :rules="rules" :inline="true"  class="demo-form-inline" :size="formSize">

          <el-select autocomplete="off" placeholder="考试类型" style="width: 25%" clearable>
            <el-option label="全部类型" value="全部类型"></el-option>
            <el-option label="现代学" value="现代学"></el-option>
          </el-select>
          &nbsp
          <el-input  placeholder="试卷名称" style="width: 25%" clearable></el-input>
          &nbsp
          <br/>
          <div style="width: 800px;margin-top: 25px">
            <el-table :data="data.shijuan"  border stripe style="width: 99%" >
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
                  <el-button size="small">选定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <br/>
          <br/>
          <el-form-item style="position: relative;top: 0px;">
            <el-button type="primary" @click="addpost(form)"  :validate-event="false">确认</el-button>
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