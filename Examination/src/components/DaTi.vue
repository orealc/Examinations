<template>
    <div style="width: 100%;height: 700px;border: 1px solid red;">
        <div style="border: 1px solid blue;width: 15%;height: 680px;margin: 10px;">
            <div style="border: 1px solid;width: 100%;height: 30px;margin-top: 10px;" >
                <span style="font-size: 20px;">单选题</span>
                <span style="font-size: 15px;padding-left: 45px;">总{{data.tj}}题/共{{data.zf}}分</span>
                <div style="border: 1px solid red;width: 120px;height: 600px;margin-top: 30px;">
                    <template v-for="(item,index) in data.tj">
                        <div style="border: 1px solid red;width: 30px;height: 20px;margin: 10px">
                            {{item}}
                        </div>
                    </template>
                </div>
            </div>
        </div>
        <div style="border: 1px solid blue;width: 70%;height: 680px;margin: 10px;">
            <div style="border: 1px solid;width: 96%;height: 40px;margin: 20px;background-color: #ECF2F8 ">
                <span style="line-height: 40px;">单选题(本题分值: {{data.jisuan}}分)</span>
            </div>
            <div style="border: 1px solid;width: 96%;height:550px;margin: 20px;background-color: white ">
                   <div style="border: 1px solid;width: 96%;height:140px;margin: 20px;background-color: #ECF2F8 "
                        v-for="(item,index) in data.shuzu ">
                       <div style="width: 100%;height: 30px;border: 1px solid red">第{{index+1}}题:{{item.tcontent}}
                           <div style="width: 100%;height: 30px;border: 1px solid blue;margin-top: 20px;">
                               <el-radio-group v-model="item.testid" class="ml-4"  @change="handleRadioChanges" >
                                   <el-radio :label="item.answers[0]" size="large" >{{item.answers[0]}}</el-radio>
                                   <el-radio :label="item.answers[1]" size="large">{{item.answers[1]}}</el-radio>
                               </el-radio-group>
                           </div>
                       </div>
                   </div>
                <el-dialog v-model="dialogFormVisible" title="考试结果">
                   一共{{data.tj}}题,恭喜你答对{{data.row}}题,分数{{data.fs}}
                    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false,tz(),update(),insertjl()"
        >确定</el-button
        >
      </span>
                    </template>
                </el-dialog>

            </div>

        </div>
        <div style="border: 1px solid gold;width: 10%;height: 680px;margin: 10px;">
            <el-button type="primary" @click="dialogFormVisible = true,tj()">提交试卷</el-button>
        </div>
    </div>
</template>

<script setup>
    import {ref,reactive,onBeforeMount} from 'vue'
    import { useRoute ,useRouter, onBeforeRouteUpdate} from 'vue-router'
    import {useStore} from "vuex"
    import axios from 'axios'//引入axioss
    const store=useStore();
    const router=useRouter()
    const radio1 = ref('')
    const checkList = ref([])
    const dialogFormVisible = ref(false)
    var data=reactive({
        tj:"",//试题数量
        zf:"",//总分
        jisuan:"",//计算
        shuzu:[],//数组
        jg:[],//结果
        row:"",//答对几题
        fs:"",//得几分
    });
    var form=reactive({
        userid:"1",
        testname:"",
        highestscore:"",
        recenttime:"",
    })
    const active = ref(0)
    const next = () => {
        if (active.value++ > 2) active.value = 0
    }

    //生成一个数组[接收所有答案；以题目id为键，答案为值]
    //将数组返回至后端
    function handleRadioChanges(value){
        // console.log(item.testid)
        // console.log(id)
        data.jg.push(value);
        console.log(data.jg);
        //添加答案以及试题编号至数组【判断该键值是否以及存在，若存在则修改值，若不存在，则添加值】
    }

    onBeforeMount(() => {
        axios.get("http://localhost:8085/StaffController/selecttj", {
            params: {
                examinationid: store.getters.id
            }
        }).then(function (res) {

            console.log(res.data.data)
            data.tj=res.data.data.tj;
            data.zf=res.data.data.questionscore;
            data.jisuan=data.zf/data.tj;
        })
        axios.get("http://localhost:8085/StaffController/selecttm",{
            params:{
                examinationid: store.getters.id
            }
        }).then(function (response) {
            data.shuzu=response.data.data;
            console.log("数组",data.shuzu);
        })
    })
function tj() {
axios.get("http://localhost:8085/StaffController/selectjg?jg="+data.jg).then(function (response) {
    console.log(response.data.data);
        data.row=response.data.data;
        console.log("答对"+data.row)
        data.fs=data.jisuan*data.row;
        form.highestscore=data.fs;
})
}
function tz() {
    router.push({path:"/yg"})
}
function update() {
        console.log("11111")
axios.put("http://localhost:8085/StaffController/update/"+store.getters.id).then(function (response) {
console.log(response.data.data)
})
}
function insertjl() {
axios.post("http://localhost:8085/StaffController/insertjl/"+store.getters.id,form).then(function (response) {
console.log("1111111")
})
}
</script>

<style scoped>
div{
    float: left;
}
</style>