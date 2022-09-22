<template>
    <div style="padding-left:300px">
        <!-- 搜索区域 -->
        <div style="margin: 10px 0;padding-left:30px;position: relative;top: 20px">
            <el-select v-model="data.cp" placeholder="选择或搜索题库" style="width: 150px;right:30px" clearable>
                <el-option label="未交付" value="未交付"/>
                <el-option label="已交付" value="已交付"/>
                <el-option label="意外终止" value="意外终止"/>
            </el-select>
            <el-select v-model="data.zts " placeholder="请选择题型" autocomplete="off" style="right:20px">
                <!-- <el-option v-for="item in data.khname" :label="item.orderTitle" :value="item.orderTitle" /> -->
                <el-option label="未交付" value="未交付"/>
                <el-option label="已交付" value="已交付"/>
                <el-option label="意外终止" value="意外终止"/>
            </el-select>
            <el-input v-model="data.stnr" placeholder="试题内容" style="width: 150px;" clearable/>
            <el-button :icon="Search" @click="tjsel()"/>
        </div>
        <!-- 功能区域 -->
        <div style="margin: 10px 0;padding-left:700px">
            <el-button type="primary" @click="insertest()">添加</el-button>
            <el-button type="danger" :icon="Delete">删除</el-button>
        </div>
        <el-table ref="multipleTableRef" :data="data.st" style="width: 985px" border>
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="tcontent" label="试题内容" width="260"/>
            <el-table-column prop="questionbankid" label="题库" width="200"/>
            <el-table-column prop="tqtype" label="题型" show-overflow-tooltip width="100"/>
            <el-table-column prop="tqlevel" label="难度" show-overflow-tooltip width="100"/>
            <el-table-column prop="tqtime" label="创建日期" width="150">
                <template #default="scope">{{ scope.row.tqtime }}</template>
            </el-table-column>
            <el-table-column label="操作" width="120">
                <template #default=scope v-slot="scope">
                    <el-button :icon="Edit" @click="tables=true,selectid(scope.row.testid)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize"
                       layout="prev,pager,next" :total="this.data.total" @current-change="page" prev-text="上一页"
                       next-text="下一页"/>
        <el-drawer v-model="tables" title="试题修改" direction="btt" size="80%"
                   :before-close="handleClose">
            <el-form :inline="true" v-model="data.xgst" class="demo-form-inline">
                <el-form-item label="试题类型">
                    <el-input v-model="data.xgst.tqtype" placeholder="Approved by"/>
                </el-form-item>
                <el-form-item label="所属题库" style="position: relative;left: 200px;">
                    <el-select v-model="data.xgst.questionbankid" placeholder="请选择题库">
                        <el-option label="1" value="1"/>
                        <el-option label="2" value="2"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="难度等级" style="position: relative;right: 600px;top: 100px;">
                    <el-select v-model="data.xgst.tqlevel" placeholder="请选择难度等级">
                        <el-option label="简单" value="简单"/>
                        <el-option label="一般" value="一般"/>
                        <el-option label="较难" value="较难"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="试题内容" style="position: relative;right: 340px;top: 200px;">
                    <el-input type="textarea" v-model="data.xgst.tcontent" style="width: 450px;"/>
                </el-form-item>
                <el-button type="primary" @click="uptquestion(data.xgst)">保存</el-button>
                <el-button>返回</el-button>
            </el-form>
        </el-drawer>
    </div>
</template>
<script lang="ts" setup>
    import request from "../utils/request.js";
    import {
        Delete,
        Edit,
        Search,
        Share,
        Upload
    } from '@element-plus/icons-vue'
    import {
        useRouter
    } from 'vue-router'

    const router = useRouter()
    import {
        ref,
        reactive,
        computed,
        onBeforeMount
    } from 'vue'
    import {
        ElTable,
        ElMessage
    } from 'element-plus'
    import axios from 'axios'

    const tables = ref(false)
    var data = reactive({
        zts: '',
        cp: '',
        st: [],
        stnr: '',
        pageNum: 1,
        pageSize: 4,
        total: 0,
        xgst: []
    })
    function page() {
        axios.get("http://localhost:8089/eqselname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                tcontent: data.stnr,
            }
        }).then(function (response) {
            if (response.data.code != 200) {
                alert("查询失败")
            }
            data.st = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.st)
        }).catch(function (error) {
            console.log(error)
        })
    }
    //分页
    function tjsel() {
        axios.get("http://localhost:8089/eqselname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                tcontent: data.stnr,
            }
        }).then(function (response) {
            if (response.data.code != 200) {
                alert("查询失败")
            }
            data.st = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.st)
        }).catch(function (error) {
            console.log(error)
        })
    }

    onBeforeMount(() => {
        axios.get("http://localhost:8089/eqselname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                tcontent: data.stnr,
            }
        }).then(function (response) {
            if (response.data.code != 200) {
                alert("查询失败")
            }
            data.st = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.st)
        }).catch(function (error) {
            console.log(error)
        })
    })

    //根据id查询
    function selectid(testid) {
        console.log("编号" + testid)
        axios.get("http://localhost:8089/findid", {
            params: {
                testid: testid
            }
        }).then(function (a) {
            console.log(a.data.data);
            data.xgst = a.data.data;
            console.log(data.xgst);
        }).catch(function (error) {
            console.log(error);
        })
    }

    //修改
    function uptquestion(xgst) {
        console.log("修改后的数据" + xgst)
        axios.put("http://localhost:8089/uptquestion", xgst).then(function (aa) {
            console.log(aa)
            if (aa.data.code != 200) {
                // ElMessage.error('修改失败 ')
                alert("修改失败！")
                // location.reload()
            }
            xgst: ""
            alert("修改成功！")
            console.log("11111")
            // location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }

    function insertest() {
        router.push({
            path: '/instest'
        })
    }

    function xgst() {
        router.push({
            path: '/uptest'
        })
    }
</script>
<style>
    .tc1 {
        width: 500px;
        overflow: auto;
    }
</style>
