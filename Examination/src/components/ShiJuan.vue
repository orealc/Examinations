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
  total: 0,
  pageNum: 1,
  pageSize: 5,
})
const form = reactive({

})
const dialogFormVisible = ref(false)
const dialogFormVisible2 = ref(false)
const a=ref("");
const bbbb=[
  {
    lx:'安抚',
    nr:'奥璨',
    tk:'传送',
    jb:'阿萨',
  },  {
    lx:'安抚',
    nr:'奥璨',
    tk:'传送',
    jb:'阿萨',
  },  {
    lx:'安抚',
    nr:'奥璨',
    tk:'传送',
    jb:'阿萨',
  },  {
    lx:'安抚',
    nr:'奥璨',
    tk:'传送',
    jb:'阿萨',
  }
]
const aaaa=[
  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  },
  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  },
  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  },
  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  },
  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  }
  ,  {
    name:'阿布',
    fangshi:'武器',
    fenlei:'为呃',
    zonfen:'公海',
    cjr:'前往',
    cjtime:'2022-2-2'
  }
]
</script>

<template>
<div style="padding: 10px" id="aa">
<!--搜索功能区域-->
  <div style="position: relative;bottom: 25px">


    <el-select v-model="ShiJuan" autocomplete="off" placeholder="试卷分类" style="width: 10%" clearable>
      <el-option label="全部分类" value="全部分类"></el-option>
      <el-option label="理科" value="理科"></el-option>
      <el-option label="社会学" value="社会学"></el-option>
      <el-option label="近现代" value="近现代"></el-option>
    </el-select>
    &nbsp
    <el-select autocomplete="off" placeholder="组卷方式" style="width: 10%" clearable>
      <el-option label="抽题方式" value="抽题方式"></el-option>
      <el-option label="选题方式" value="选题方式"></el-option>
    </el-select>
    &nbsp
    <el-input placeholder="试卷名称" style="width: 10%" clearable></el-input>
    <el-button type="primary" style="margin-left: 5px">查询</el-button>
  </div>
  <!-- 功能区域 -->

    <el-button type="primary" @click="dialogFormVisible = true" style="position: relative;">+ 添加</el-button>


  <div style="width: 1400px;margin-top: 25px">
    <el-table :data="aaaa"  border stripe style="width: 99%" >
      <!-- sortable排序 -->
      <el-table-column type="selection" width="55" />
      <el-table-column type="index" label="序号" />
      <el-table-column prop="name" label="试卷名称" width="180">
        <template #default=scope v-slot="scope">
          <span style="color: #00aaff">{{scope.row.name}}</span>
        </template>
      </el-table-column>

      <el-table-column prop="fangshi" label="组卷方式" />
      <el-table-column prop="fenlei" label="试卷分类" />
      <el-table-column prop="zonfen" label="组卷总分" />
      <el-table-column prop="cjr" label="创建人" />
      <el-table-column prop="cjtime" label="创建时间" />
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

        <el-form-item prop="" label="试卷名称" :label-width="formLabelWidth">
          <el-input autocomplete="off" />
        </el-form-item>

        <el-form-item prop="" label="试卷分类" :label-width="formLabelWidth">
          <el-select autocomplete="off" placeholder="请选择">
            <el-option label="全部分类" value="全部分类"></el-option>
            <el-option label="理科" value="理科"></el-option>
            <el-option label="社会学" value="社会学"></el-option>
            <el-option label="近现代" value="近现代"></el-option>
          </el-select>
        </el-form-item>
        <br/>
        <a style="font-size:18px">添加试题</a>
        <br/>
        <br/>
        <el-select autocomplete="off" placeholder="试题类型" style="width: 25%" clearable>
          <el-option label="多选题" value="多选题"></el-option>
          <el-option label="单选题" value="单选题"></el-option>
        </el-select>
        &nbsp
        <el-input  placeholder="题库搜索" style="width: 25%" clearable></el-input>
        &nbsp
        <el-input  placeholder="试题内容" style="width: 25%" clearable></el-input>
        <br/>
        <div style="width: 800px;margin-top: 25px">
          <el-table :data="bbbb"  border stripe style="width: 99%" >
            <!-- sortable排序 -->
            <el-table-column type="selection" width="55" />
            <el-table-column type="index" label="序号" />
            <el-table-column prop="lx" label="试题类型" />
            <el-table-column prop="nr" label="试题内容" />
            <el-table-column prop="tk" label="所属题库" />
            <el-table-column prop="jb" label="级别" />
          </el-table>
        </div>
        <br/>
        每题<el-input style="width: 25%" clearable></el-input>分
        <br/>
        <br/>
        <el-form-item style="position: relative;top: 0px;">
          <el-button type="primary" @click="addpost(form)"  :validate-event="false">确认</el-button>
          <el-button @click="dialogFormVisible = false,qx()">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
<!--  弹窗2  -->
    <el-dialog v-model="dialogFormVisible2" title="创建考试">
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
          <el-table :data="aaaa"  border stripe style="width: 99%" >
            <!-- sortable排序 -->
            <el-table-column prop="name" label="试卷名称" width="180">
              <template #default=scope v-slot="scope">
                <span style="color: #00aaff">{{scope.row.name}}</span>
              </template>
            </el-table-column>

            <el-table-column prop="fangshi" label="组卷方式" />
            <el-table-column prop="fenlei" label="试卷分类" />
            <el-table-column prop="zonfen" label="组卷总分" />
            <el-table-column prop="cjr" label="创建人" />
            <el-table-column prop="cjtime" label="创建时间" />
            <el-table-column label="操作">
              <template #default=scope v-slot="scope">
                <!--          <el-button size="small" @click="deletcg(scope.row.postId)" :icon="Delete" circle color="red"></el-button>-->
                <el-button size="small" @click="showStaffForm = true;">选定</el-button>
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