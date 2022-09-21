<template>
    <div style="border-radius:10px;border: 1px solid blue;width: 1200px;height:900px;position: relative;left: 250px">


    <el-form :inline="true" :model="inst" class="demo-form-inline">
        <el-form-item label="试题类型">
            <el-input v-model="inst.tqtype" placeholder="Approved by"/>
        </el-form-item>
        <el-form-item label="所属题库" style="">
            <el-select v-model="inst.questionbankid" placeholder="请选择题库">
                <el-option label="1" value="1"/>
                <el-option label="2" value="2"/>
            </el-select>
        </el-form-item>
        <el-form-item label="难度等级" style="">
            <el-select v-model="inst.tqlevel" placeholder="请选择难度等级">
                <el-option label="简单" value="简单"/>
                <el-option label="一般" value="一般"/>
                <el-option label="较难" value="较难"/>
            </el-select>
        </el-form-item>
        <el-form-item label="试题内容" style="">
            <el-input type="textarea" v-model="inst.tcontent" style=""/>
        </el-form-item>
    </el-form>
    <el-button type="primary" @click="insst(inst)">保存</el-button>
    <el-button>返回</el-button>
    </div>
</template>

<script lang="ts" setup>
    import {
        reactive
    } from 'vue'
    import axios from 'axios'

    const inst = reactive({
        tqtype: '',
        questionbankid: '',
        tqlevel: '',
        tcontent: ''
    })

    //新增题库
    function insst(inst) {
        axios.post("http://localhost:8089/tkxz", inst).then(function (a) {
            if (a.data.code != 200) {
            	// ElMessage.error("新增失败")
            	// location.reload()
                alert("新增失败")
            }
            // ElMessage.success("新增成功")
            alert("新增成功")
            tkform: ""
            // location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }
</script>
