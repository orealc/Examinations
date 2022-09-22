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
            <div style="border: 1px solid black;width: 400px;height: 60px;position: relative;top: -63px;left: 500px; " class="a">
                <ul>
                    <li>
                        在线考试
                    </li>
                    <li>
                        <span @click="route()">题库训练</span>
                    </li>
                </ul>
            </div >
            <!--个人信息-->
            <div style="border: 1px solid black;width: 200px;height: 60px;position: relative;top: -123px;left: 1220px; " class="a" >
                <ul>
                    <li>
                        <el-dropdown>
    <span class="el-dropdown-link">
      张三
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item><span @click="dialogTableVisible = true,selectjl()">考试记录</span></el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </li>
                    <li>
                        退出
                    </li>
                </ul>

            </div>
        </div>
        <!--主体-->
        <div style="width: 50%;height: 520px;border: 1px solid black;margin:0 auto;position: relative;top: 40px;background-color: white">
            <el-scrollbar height="500px">
                <div style="border: 1px solid;width: 96%;height:140px;margin: 20px;background-color: #ECF2F8 "
                     v-for="(item,index) in data.shuzu ">
                    <div style="width: 100%;height: 30px;border: 1px solid red">第{{index+1}}题:{{item.tcontent}}
                        <span style="margin-left: 200px">{{item.tqtype}}</span>
                        <div style="width: 100%;height: 30px;border: 1px solid blue;margin-top: 20px;">
                            <el-radio-group v-model="item.testid" class="ml-4"  @change="handleRadioChanges" >
                                <el-radio :label="item.answers[0]" size="large" >{{item.answers[0]}}</el-radio>
                                <el-radio :label="item.answers[1]" size="large">{{item.answers[1]}}</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                </div>
            </el-scrollbar>
            <el-button type="primary" style="position: relative;left: 800px;top: -500px;" @click="tj(),dialogFormVisible = true">结束训练</el-button>
        </div>
        <el-dialog v-model="dialogFormVisible" title="考试结果">
            一共{{data.coun}}题,恭喜你答对{{data.row}}题
            <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false,tz(),updatetkzt()"
        >确定</el-button
        >
      </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
    import {useStore} from "vuex"
    import axios from 'axios'
    import { ref,onBeforeMount,reactive} from 'vue'
    import {useRouter} from "vue-router";
    const store=useStore();
    const router=useRouter();
    const dialogFormVisible = ref(false)
    var data=reactive({
        shuzu:[],
        jg:[],
        coun:"",
        row:""
    })
    onBeforeMount(() => {
        console.log("题库id",store.getters.tid);
        console.log("题库类型",store.getters.lx)
        axios.get("http://localhost:8085/StaffController/selectstid",{
            params:{
                questionbankid:store.getters.tid,
                tqtype:store.getters.lx,
            }
        }).then(function(response) {
            console.log(response.data.data)
            data.shuzu=response.data.data
        }).catch(function(error) {
            console.log(error)
        })
        axios.get("http://localhost:8085/StaffController/selecttjst",{
            params:{
                questionbankid:store.getters.tid
            }
        }).then(function (res) {
            console.log("统计",res.data.data)
            data.coun=res.data.data.coun;
        })
    })

    function handleRadioChanges(value){
        data.jg.push(value);
        console.log(data.jg);
    }
    function tj() {
        axios.get("http://localhost:8085/StaffController/selectjg?jg="+data.jg).then(function (response) {
            console.log(response.data.data);
            data.row=response.data.data;
        })
    }
    function updatetkzt() {
        axios.put("http://localhost:8085/StaffController/updatetkzt/"+store.getters.tid).then(function (res) {
            console.log("修改成功")
        })
    }
    function tz() {
        router.push({path:"/tiku"})
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