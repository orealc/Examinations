<script setup>
    import {onBeforeMount, reactive} from "vue";
    import axios from "axios";
    import {useStore} from "vuex"
    const store=useStore();
    import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router'
    const router=useRouter()
    var data=reactive({
        shuju:[],
        jl:"",
        pageNum: 1, //第一页
        pageSize: 4, //一页有几条数据
        total:0,
        examname:'',
    })
    function DaTi(id) {
        console.log("id",id);
        store.commit("bcid",id);
        router.push({path:"/DaTi"})
    }
    onBeforeMount(() => {
        axios.get("http://localhost:8085/StaffController/selectmn",{
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
        axios.get("http://localhost:8085/StaffController/selectmn",{
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

</script>

<template>
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
            <el-table-column prop="examinationid" label="序列" width="180" >
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

</template>

<style scoped>

</style>
