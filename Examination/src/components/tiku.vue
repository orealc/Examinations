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
            <div style="border: 1px solid black;width: 400px;height: 60px;position: relative;top: -63px;left: 500px;" class="a">
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
            <div style="border: 1px solid black;width: 200px;height: 60px;position: relative;top: -123px;left: 1220px;" class="a">
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
            <div >
                <el-input
                        v-model="data.qbname"
                        size="large"
                        placeholder="搜索考试名称"
                        :suffix-icon="Search"
                        style="width: 200px;position: relative;left:-50px;"
                />
                <el-select v-model="data.qbclassification" class="m-2" placeholder="Select"  style="position: relative;left:-30px;" size="large">
                    <el-option
                            v-for="item in data.fl"
                            :key="item.questionbankid"
                            :label="item.qbclassification"
                            :value="item.qbclassification"
                    />
                </el-select>
                <el-button type="primary" style="margin-left: -10px;" @click="page()">搜索</el-button>
            </div>
                    <div style="width: 95%;height: 370px;border: 1px solid blue;margin:0px auto;position: relative;top: 10px;">
                        <el-table :data="data.shz" style="width: 100%">

                            <el-table-column prop="questionbankid" label="题库id" v-if="false"/>
                            <el-table-column prop="qbname" label="题库名称" />
                            <el-table-column prop="qbclassification" label="题库分类"  />
                            <el-table-column prop="qbnumber" label="试题总数" />
                            <el-table-column prop="tkzt" label="状态" >
                            </el-table-column>
                            <el-table-column prop="qbtime" label="创建时间" v-slot="scope">
                                <span>{{scope.row.qbtime.substring(0,10)}}</span>
                            </el-table-column>
                            <el-table-column label="操作" v-slot="scope">
                                <el-button type="primary" @click="tiaoz(scope.row.questionbankid,scope.row.qbname)
                                ,dialogTableVisible = true">详情</el-button>
                                <el-button v-if="scope.row.tkzt=='训练完成'" @click="dialogTableVisible2 = true,daan(scope.row.questionbankid)" >答案</el-button>
                            </el-table-column>
                        </el-table>
                    </div>
        </div>
        <div style="width: 80%;height: 30px;border: 1px solid black;margin:0 auto;position: relative;top: 60px;">
            <el-pagination v-model:currentPage="this.data.pageNum" v-model:page-size="this.data.pageSize" layout="prev, pager, next"
                           :total="this.data.total" @current-change="page" prev-text="上一页" next-text="下一页" style="margin-left: 500px" />
        </div>
        <el-dialog v-model="dialogTableVisible" title="题库详情">
            题库名称:
            <p>{{data.name}}</p>
            <hr>
            练习模式:
            <p>
                <el-button @click="tz()">
                    顺序练习
                </el-button>
            </p>
            <hr>
            <p>
                题型练习
            </p>
            <p>
                <el-button
                v-for="(item,index) in data.butten"
                @click="tzz(item.tqtype)"
                >
                    {{item.tqtype}}({{item.con}}个)
                </el-button>
            </p>
        </el-dialog>
        <el-dialog v-model="dialogTableVisible2" title="题库答案">
            <el-scrollbar height="400px">
                <div style="border: 1px solid;width: 96%;height:140px;margin: 20px;background-color: #ECF2F8 "
                     v-for="(item,index) in data.shuzu ">
                    <div style="width: 100%;height: 30px;border: 1px solid red">第{{index+1}}题:{{item.tcontent}}
                        <span style="margin-left: 200px">{{item.tqtype}}</span>

                        <div style="width: 100%;height: 30px;border: 1px solid blue;margin-top: 20px;">
                            <el-radio-group v-model="item.testid" class="ml-4"   >
                                <el-radio :label="item.answers[0]" size="large" disabled >{{item.answers[0]}}&nbsp;&nbsp;&nbsp;是否为答案：{{item.isanswers[0]}}</el-radio>
                                <el-radio :label="item.answers[1]" size="large"  disabled >{{item.answers[1]}}&nbsp;&nbsp;&nbsp;是否为答案：{{item.isanswers[1]}}</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                </div>
            </el-scrollbar>
        </el-dialog>
    </div>

</template>

<script lang="ts" setup>

    import { ref,onBeforeMount,reactive } from 'vue'
    import {useStore} from "vuex"
    import { Search } from '@element-plus/icons-vue'
    const handleSelect = (key: string, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const dialogTableVisible = ref(false)
    const dialogTableVisible2 = ref(false)
    const input1 = ref('')
    const value2 = ref('')
    const store=useStore();
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
    var data=reactive({
        pageNum: 1, //第一页
        pageSize: 4, //一页有几条数据
        total:0,
        fl:[],
        qbname:"",
        qbclassification:"",
        shz:[],
        name:"",
        id:"",
        butten:[],
        shuzu:[],

    })
    const activeName = ref("first")
    import Hello from "./monikaos.vue"
    import {useStore} from "vuex"
    import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router'
    import axios from "axios";
    const router=useRouter()
    function route() {
        router.push({path:"/tiku"})
    }
    function route1() {
        router.push({path:"/yg"})
    }
    onBeforeMount(() => {
        axios.get("http://localhost:8085/StaffController/selectfl").then(function(response) {
            data.fl=response.data.data;
            console.log(data.fl);
        }).catch(function(error) {
            console.log(error)
        })
        axios.get("http://localhost:8085/StaffController/selecttk",{
            params:{
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                qbname:data.qbname,
                qbclassification:data.qbclassification,
            }
        }).then(function(response) {
            data.shz=response.data.data.list;
            data.total = response.data.data.total
            console.log(response.data.data);
        }).catch(function(error) {
            console.log(error)
        })

    })
    function page() {
        axios.get("http://localhost:8085/StaffController/selecttk",{
            params:{
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                qbname:data.qbname,
                qbclassification:data.qbclassification,
            }
        }).then(function(response) {
            data.shz=response.data.data.list;
            data.total = response.data.data.total
        }).catch(function(error) {
            console.log(error)
        })
    }
    function tiaoz(tkid,name) {
        console.log("题库id",tkid);
        // store.commit("tikuid",tkid);
        data.name=name;
        data.id=tkid;
        console.log("题库名称",data.name)
        axios.get("http://localhost:8085/StaffController/selectcount",{
            params:{
                questionbankid:tkid
            }
        }).then(function (response) {
        console.log(response.data.data)
            data.butten=response.data.data;
        })
        // router.push({path:"/Tikuxq"})
    }
    function tz() {
        store.commit("tikuid",data.id)
        router.push({path:"/TiKuxq"})
    }
    function tzz(type) {
        store.commit("tikuid",data.id)
        store.commit("tklx",type)
        router.push({path:"/TiKuxq"})
    }
    function daan(id) {
        axios.get("http://localhost:8085/StaffController/selectstid",{
            params:{
                questionbankid:id,
                tqtype:"",
            }
        }).then(function(response) {
            console.log(response.data.data)
            data.shuzu=response.data.data;

        }).catch(function(error) {
            console.log(error)
        })
    }
</script>

<style scoped>
   .a ul li{
        list-style:none;
        float: left;
        margin-left: 30px;
        color: white;
    }
</style>