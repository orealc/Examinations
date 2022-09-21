<template>
    <div style="padding-left:300px">
        <!-- 功能区域 -->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="inserttk()">新增</el-button>
            <el-button type="danger" @click="tzym()" :icon="Delete">删除</el-button>
        </div>
        <!-- 搜索区域 -->
        <div style="margin: 10px 0">
            <el-select v-model="data.cp" placeholder="请选择分类" style="width: 150px;right:10px" clearable>
                <el-option label="未交付" value="未交付"/>
                <el-option label="已交付" value="已交付"/>
                <el-option label="意外终止" value="意外终止"/>
            </el-select>
            <!-- <el-input v-model="search" size="small" placeholder="搜索题库名称" style="width:150px" /> -->
            <el-input v-model="data.tkname" placeholder="搜索题库名称" style="width:150px" clearable/>
            <el-button :icon="Search" @click="page2()"/>
            <!-- <el-option v-for="item in data.khname" :label="item.proName" :value="item.proName" /> -->
        </div>
        <el-table ref="multipleTableRef" :data="data.tks" style="width:900px" border tooltip-effect="dark">
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="qbname" label="题库名称" width="100"/>
            <el-table-column prop="qbnumber" label="试题总数" width="100"/>
            <el-table-column prop="qbclassification" label="题库分类" show-overflow-tooltip width="300"/>
            <el-table-column prop="qbtime" label="创建日期" width="100">
                <template #default="scope">{{ scope.row.qbtime }}</template>
            </el-table-column>
            <el-table-column label="操作" show-overflow-tooltip width="245">
                <template #default=scope v-slot="scope">
                    <el-button :icon="Edit" @click="tikus=true,findtkid(scope.row.questionbankid)">修改</el-button>
                    <el-button @click="selstname(scope.row.questionbankid),tikushiti=true">试题管理</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize"
                       layout="prev,pager,next" :total="this.data.total"
                       @current-change="page" prev-text="上一页" next-text="下一页"
        />
        <!--		修改题库-->
        <el-drawer v-model="tikus" title="题库修改" direction="rtl" size="30%"
                   :before-close="handleClose">
                <el-form :inline="true" :model="data.tkzd" class="demo-form-inline">
                    <el-form-item label="题库名称">
                        <el-input v-model="data.tkzd.qbname" style=""/>
                    </el-form-item>
                    <el-form-item label="题库分类">
                        <el-select v-model="data.tkzd.qbclassification" placeholder="请选择题库">
                            <el-option label="1" value="1"/>
                            <el-option label="2" value="2"/>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="题库描述" style="">
                        <el-input type="textarea" v-model="data.tkzd.qbdescription" style=""/>
                    </el-form-item>
                    <el-button type="primary" @click="uptikus(data.tkzd)">保存</el-button>
                    <el-button>返回</el-button>
                </el-form>
        </el-drawer>
        <!--		根据题库查询题库下的试题-->
        <el-drawer v-model="tikushiti" title="题库试题" direction="btt" size="80%"
                   :before-close="handleClose">
            <el-table ref="multipleTableRef" :data="data.tkselst" style="width: 985px" border>
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
        </el-drawer>
    </div>
</template>
<script lang="ts" setup>
    import {
        useRouter
    } from 'vue-router'

    const router = useRouter()
    import request from "../utils/request.js";
    import {
        Delete,
        Edit,
        Search,
        Share,
        Upload
    } from '@element-plus/icons-vue'

    import {
        ref,
        reactive,
        computed,
        onBeforeMount
    } from 'vue'
    import {
        ElTable
    } from 'element-plus'
    import axios from 'axios'

    const tikus = ref(false)
    const tikushiti = ref(false)
    var data = reactive({
        tks: [],
        tkname: '',
        pageNum: 1,
        pageSize: 5,
        total: 0,
        tkzd: [],
        tkselst:[],
    })

    function page() {
        axios.get("http://localhost:8089/selname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                qbname: data.tkname,
            }
        }).then(function (response) {
            data.tks = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.tks)
        }).catch(function (error) {
            console.log(error)
        })
    }
    //分页
    function page2() {
        axios.get("http://localhost:8089/selname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                qbname: data.tkname,
            }
        }).then(function (response) {
            if (response.data.code != 200) {
                alert("查询失败")
            }
            data.tks = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.tks)
        }).catch(function (error) {
            console.log(error)
        })
    }

    //根据id查询
    function findtkid(questionbankid) {
        console.log("编号" + questionbankid)
        axios.get("http://localhost:8089/findtkid", {
            params: {
                questionbankid: questionbankid
            }
        }).then(function (a) {
            console.log(a.data.data);
            data.tkzd = a.data.data;
            console.log(data.tkzd);
        }).catch(function (error) {
            console.log(error);
        })
    }
    //根据id查询
    function selstname(questionbankid) {
        console.log("编号" + questionbankid)
        axios.get("http://localhost:8089/selstname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                questionbankid: questionbankid
            }
        }).then(function (a) {
            console.log(a.data.data);
            data.tkselst = a.data.data;
            console.log(data.tkselst);
        }).catch(function (error) {
            console.log(error);
        })
    }
    //修改
    function uptikus(tkzd) {
        console.log("修改后的数据" + tkzd)
        axios.put("http://localhost:8089/uptikus", tkzd).then(function (aa) {
            console.log(aa)
            if (aa.data.code != 200) {
                // ElMessage.error('修改失败 ')
                alert("修改失败！")
                // location.reload()
            }
            tkzd: ""
            alert("修改成功！")
            console.log("11111")
            // location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }

    onBeforeMount(() => {
        axios.get("http://localhost:8089/selname", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
            }
        }).then(function (response) {
            data.tks = response.data.data.list //后端返回的数据
            data.total = response.data.data.total
            console.log(data.tks)
        }).catch(function (error) {
            console.log(error)
        })
    })

    function inserttk() {
        router.push({
            name: 'insertk'
        })
    }

    function tzym() {
        router.push({
            path: '/testgl'
        })
    }
</script>
