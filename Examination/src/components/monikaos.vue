<script setup>
    import { Search } from '@element-plus/icons-vue'
</script>

<template>
    <div >
        <el-input
                v-model="input1"
                size="large"
                placeholder="搜索考试名称"
                :suffix-icon="Search"
                style="width: 200px;position: relative;left: -450px;"
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
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="date" label="考试名称" width="180" />
            <el-table-column prop="name" label="时长" width="180" />
            <el-table-column prop="address" label="总分" />
            <el-table-column prop="address" label="及格分" />
            <el-table-column prop="address" label="开放时间" />
            <el-table-column prop="address" label="售价" />
            <el-table-column prop="address" label="考试状态" />
        </el-table>
    </div>

</template>

<style scoped>

</style>
