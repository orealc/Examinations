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
                        <span @click="route1()">在线考试</span>
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
                        张三
                    </li>
                    <li>
                        退出
                    </li>
                </ul>

            </div>
        </div>
        <!--主体-->
        <div style="width: 80%;height: 520px;border: 1px solid black;margin:0 auto;position: relative;top: 40px;background-color: white">
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
        </div>
        <div style="width: 80%;height: 30px;border: 1px solid black;margin:0 auto;position: relative;top: 60px;">
            分页
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { ref } from 'vue'
    import { Search } from '@element-plus/icons-vue'
    const handleSelect = (key: string, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const input1 = ref('')
    const value2 = ref('')

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
    const tableData = [
        {
            date: '2016-05-03',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-02',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-04',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
        {
            date: '2016-05-01',
            name: 'Tom',
            address: 'No. 189, Grove St, Los Angeles',
        },
    ]
    const activeName = ref("first")
    import Hello from "./monikaos.vue"
    import {useStore} from "vuex"
    import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router'
    const router=useRouter()
    function route() {
        router.push({path:"/tiku"})
    }
    function route1() {
        router.push({path:"/yg"})
    }
</script>

<style scoped>
    ul li{
        list-style:none;
        float: left;
        margin-left: 30px;
        color: white;
    }
</style>