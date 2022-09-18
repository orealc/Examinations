<template>
    <div style="padding:10px">
        <!-- 搜索区域 -->
        <div style="margin: 10px 0">
            <!--      clearable 清空的按钮-->
            <el-select v-model="data.search" placeholder="请选择分类" style="width: 15%" clearable>
                <el-option v-for="item in data.khname" :label="item.orderTitle" :value="item.orderTitle" />
            </el-select>
            <el-input v-model="data.zt" placeholder="搜索题库名称" style="width: 15%" clearable></el-input>
            <el-button type="primary" style="margin-left: 5px" @click="seltjcx()">查询</el-button>
        </div>
        <!-- 功能区域 -->
        <div style="margin: 10px 0">
            <el-button type="primary" >新增</el-button>
            <el-button type="primary" >修改</el-button>
            <el-button type="primary">删除</el-button>
            <!-- <el-button type="primary">删除</el-button> -->
        </div>
        <el-scrollbar height="540px" v-infinite-scroll="load">
            <el-table ref="multipleTable" :data="data.ordertables" border stripe style="width: 100%"
                      tooltip-effect="dark" @selection-change="handleSelectionChange" height="540px">
                <!-- sortable排序 -->
                <!-- <el-table-column type="selection" width="55" /> -->
                <el-table-column prop="orderId" label="ID"/>
                <el-table-column prop="orderTitle" label="主题"/>
                <el-table-column prop="customerName" label="对应客户"/>
                <el-table-column prop="fenlei" label="分类"/>
                <el-table-column prop="stateTime" label="开始日期"/>
                <el-table-column prop="endTime" label="结束日期"/>
                <el-table-column prop="zje" label="总金额"/>
                <!-- <el-table-column prop="" label="已回款" /> -->
                <el-table-column prop="state" label="状态"/>
                <el-table-column prop="cjr" label="创建人"/>
                <el-table-column label="操作" width="150px">
                    <template #default="scope" v-slot="scope">
                        <el-button size="small" @click="table=true,selxq(scope.row.orderId)">查询</el-button>
                        <el-button size="small" @click="ljdel(scope.row.orderId)">删除</el-button>
                        <el-button size="small" @click="showStaffForm = true;selectid(scope.row.orderId)">修改</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 应用Drawer 抽屉 -->
            <!-- 查看合同、客户详情 -->
            <el-drawer v-model="table" title="合同详情" direction="btt" size="60%">
                <el-table :data="data.orders">
                    <el-table-column property="customerName" label="对应客户"/>
                    <el-table-column property="orderTitle" label="合同订单主题"/>
                    <el-table-column property="orderBh" label="合同订单号"/>
                    <el-table-column property="fk" label="付款方式"/>
                    <el-table-column property="zje" label="总金额"/>
                    <el-table-column property="fenlei" label="分类"/>
                    <el-table-column property="state" label="状态"/>
                    <el-table-column property="stateTime" label="开始日期"/>
                    <el-table-column property="endTime" label="结束日期"/>
                    <el-table-column property="qyTime" label="签约日期"/>
                    <el-table-column property="address" label="交付地点"/>
                    <el-table-column property="khqyr" label="客户签约人"/>
                    <el-table-column property="qyr" label="我方签约人"/>
                    <el-table-column property="beizhu" label="备注"/>
                    <el-table-column property="cjr" label="创建人"/>
                    <el-table-column property="cjTime" label="创建时间"/>
                </el-table>
            </el-drawer>

        </el-scrollbar>

        <!-- 添加弹窗 -->
        <el-dialog v-model="dialogFormVisible" title="新增合同订单">
            <el-form ref="ruleFormRef" :model="ruleForm " :rules="rules" label-width="120px" :size="formSize"
                     :inline="true" class="demo-form-inline">
                <el-form-item label="对应客户" prop="customerName">
                    <el-select v-model="ruleForm.customerName " autocomplete="off">
                        <el-option v-for="item in data.khname" :label="item.customerName" :value="item.customerName"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="合同订单主题">
                    <el-input v-model="ruleForm.orderTitle" placeholder="请输入主题" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="合同订单号">
                    <el-input v-model="ruleForm.orderBh" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="报价id" prop="bjid">
                    <el-select v-model="ruleForm.bjid " autocomplete="off">
                        <el-option v-for="item in data.bjzje" :label="item.bjid" :value="item.bjid"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="付款方式" style="width:320px">
                    <el-select v-model="ruleForm.fk " autocomplete="off">
                        <el-option label="现金" value="现金"/>
                        <el-option label="微信转账" value="微信转账"/>
                        <el-option label="支付宝转账" value="支付宝转账"/>
                        <el-option label="支票" value="支票"/>
                        <el-option label="银行卡" value="银行卡"/>
                        <el-option label="银行卡转账" value="银行卡转账"/>
                        <el-option label="其他" value="其他"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="分类" style="width:320px">
                    <el-select v-model="ruleForm.fenlei">
                        <el-option label="产品销售" value="产品销售"/>
                        <el-option label="服务" value="服务"/>
                        <el-option label="业务合作" value="业务合作"/>
                        <el-option label="代理分销" value="代理分销"/>
                        <el-option label="其他" value="其他"/>
                    </el-select>
                </el-form-item>

                <el-form-item label="状态" prop="state">
                    <el-radio-group v-model="ruleForm.state">
                        <el-radio label="执行中"/>
                        <el-radio label="结束"/>
                        <el-radio label="意外终止"/>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="备注" prop="beizhu">
                    <el-input v-model="ruleForm.beizhu"/>
                </el-form-item>
                <el-form-item label="总金额" prop="zje">
                    <el-select v-model="ruleForm.zje " autocomplete="off">
                        <el-option v-for="item in data.bjzje" :key="item.bjid" :label="item.zje" :value="item.zje"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="开始日期">
                    <el-col :span="10">
                        <el-form-item prop="stateTime">
                            <el-date-picker v-model="ruleForm.stateTime" type="date" style="width: 100%"/>
                        </el-form-item>
                    </el-col>
                    <el-col class="text-center" :span="3">
                        结束日期
                    </el-col>
                    <el-col :span="11">
                        <el-date-picker v-model="ruleForm.endTime" type="date" style="width: 100%"/>
                    </el-col>
                </el-form-item>
                <el-form-item label="签约日期">
                    <el-col :span="20">
                        <el-form-item prop="qyTime">
                            <el-date-picker v-model="ruleForm.qyTime" type="date" style="width: 100%"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="客户签约人" prop="qyr">
                    <el-input v-model="ruleForm.khqyr"/>
                </el-form-item>
                <el-form-item label="我方签约人" prop="qyr">
                    <el-input v-model="ruleForm.qyr"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleFormRef),insert(ruleForm )">确认
                    </el-button>
                    <el-button @click="resetForm(ruleFormRef),qx()">取消</el-button>

                </el-form-item>
            </el-form>

        </el-dialog>
        <!-- 修改弹窗 -->
        <el-dialog v-model="showStaffForm" title="修改">
            <el-form :model="data.se" label-width="120px" :inline="true" class="demo-form-inline">
                <el-form-item label="对应客户" prop="customerName">
                    <el-input v-model="data.se.customerName"/>
                </el-form-item>
                <el-form-item label="合同订单主题">
                    <el-input v-model="data.se.orderTitle" placeholder="请输入主题" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="合同订单号">
                    <el-input v-model="data.se.orderBh" autocomplete="off" disabled/>
                </el-form-item>
                <el-form-item label="付款方式">
                    <el-select v-model="data.se.fk " autocomplete="off">
                        <el-option label="现金" value="现金"/>
                        <el-option label="微信转账" value="微信转账"/>
                        <el-option label="支付宝转账" value="支付宝转账"/>
                        <el-option label="支票" value="支票"/>
                        <el-option label="银行卡" value="银行卡"/>
                        <el-option label="银行卡转账" value="银行卡转账"/>
                        <el-option label="其他" value="其他"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="分类">
                    <el-select v-model="data.se.fenlei">
                        <el-option label="产品销售" value="产品销售"/>
                        <el-option label="服务" value="服务"/>
                        <el-option label="业务合作" value="业务合作"/>
                        <el-option label="代理分销" value="代理分销"/>
                        <el-option label="其他" value="其他"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="总金额">
                    <el-input v-model="data.se.zje"/>
                </el-form-item>
                <el-form-item label="状态" prop="state">
                    <el-radio-group v-model="data.se.state">
                        <el-radio label="执行中"/>
                        <el-radio label="结束"/>
                        <el-radio label="意外终止"/>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="开始日期">
                    <el-col :span="10">
                        <el-form-item prop="stateTime">
                            <el-date-picker v-model="data.se.stateTime" type="date" style="width: 100%" disabled/>
                        </el-form-item>
                    </el-col>
                    <el-col class="text-center" :span="3">
                        结束日期
                    </el-col>
                    <el-col :span="11">
                        <el-date-picker v-model="data.se.endTime" type="date" style="width: 100%"/>
                    </el-col>
                </el-form-item>
                <el-form-item label="签约日期">
                    <el-col :span="20">
                        <el-form-item prop="qyTime">
                            <el-date-picker v-model="data.se.qyTime" type="date" style="width: 100%"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="我方签约人" prop="qyr">
                    <el-input v-model="data.se.qyr"/>
                </el-form-item>
            </el-form>
            <template #footer>
				<span class="dialog-footer">
					<el-button @click="showStaffForm = false,qx()">取消</el-button>
					<el-button type="primary" @click="update(data.se),showStaffForm = false">确认</el-button>
				</span>
            </template>
        </el-dialog>
    </div>
</template>

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
    import qs from 'qs'
    import type {
        FormInstance,
        FormRules
    } from 'element-plus'

    var data = reactive({
        ordertables: [],
        zt: '',
        search: '',
        zts: '',
        se: [],
        kh: '',
        delivery: false,
        orders: [],
        khname: [],
        bjzje: []
    })
    const table = ref(false)
    const showStaffForm = ref(false)
    const dialogFormVisible = ref(false)
    const isShow = ref(false)
    const orderId = ref(0)
    const formSize = ref('default')
    const radio = ref('selected and disabled')
    const ruleFormRef = ref<FormInstance>()

    //新增的字段
    const ruleForm = reactive({
        orderBh: '',
        bjid: '',
        customerName: '',
        fk: '',
        orderTitle: '',
        stateTime: '',
        qyTime: '',
        endTime: '',
        address: '',
        state: '执行中',
        beizhu: '',
        fenlei: '',
        qyr: '',
        cjr: '',
        delivery: false,
        zje: '',
        khqyr: ''
    })
    //提示
    const rules = reactive<FormRules>({
        orderTitle: [{
            require: true,
            message: '请输入合同主题',
            trigger: 'change'
        },],
        khqyr: [{
            require: true,
            message: '请输入客户签约人',
            trigger: 'change'
        },],
        qyr: [{
            require: true,
            message: '请输入签约人',
            trigger: 'change'
        },],
        stateTime: [{
            type: 'date',
            required: true,
            message: 'Please pick a date',
            trigger: 'change',
        },],
        qyTime: [{
            type: 'date',
            required: true,
            message: 'Please pick a date',
            trigger: 'change',
        },],
        endTime: [{
            type: 'date',
            required: true,
            message: 'Please pick a time',
            trigger: 'change',
        },],
    })

    //多表查询
    onBeforeMount(() => {
        axios.get("http://localhost:8088/demo/querySelContract/1").then(function (a) {
            console.log(a.data.data)
            data.ordertables = a.data.data
        })
    })
    //添加的表单验证
    const submitForm = async (formEl: FormInstance | undefined) => {
        if (!formEl) return
        await formEl.validate((valid, fields) => {
            if (valid) {
                alert('提交成功！')
            } else {
                alert('提交失败！')
            }
        })
    }

    //自动生成编号
    function Automatic() {
        //时间
        var time = new Date();
        //年
        var year = String(time.getFullYear());
        //月
        var mouth = String(time.getMonth() + 1);
        //日
        var day = String(time.getDate());
        console.log("1111111111111111111111")
        console.log("订单号", year + mouth + day)
        ruleForm.orderBh = 'YQD' + year + mouth + day
    }

    //新增
    function insert(ruleForm) {
        console.log(ruleForm)
        axios.post("http://localhost:8088/demo/xzht", ruleForm).then(function (a) {
            if (a.data.code != 200) {
                alert("新增失败！！！")
                location.reload()
            }
            ruleForm: ""
            location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }

    //取消
    const resetForm = (formEl: FormInstance | undefined) => {
        if (!formEl) return
        formEl.resetFields()
    }

    //条件查询
    function seltjcx() {
        axios.get("http://localhost:8088/demo/tjsel", {
            params: {
                orderTitle: data.zt,
                fenlei: data.search,
                state: data.zts,
                customerName: data.kh
            }
        }).then(function (response) {
            if (response.data.code != 200) {
                alert("查询失败")
            }
            data.ordertables = response.data.data //后端返回的数据
            console.log(data.ordertables)
        }).catch(function (error) {
            console.log(error)
        })
    }

    //修改
    function update(aa) {
        console.log("修改后的数据" + aa)
        axios.put("http://localhost:8088/demo/updordertable", aa).then(function (aa) {
            console.log(aa)
            if (aa.data.code != 200) {
                alert("修改失败！")
                location.reload()
            }
            se: ""
            alert("修改成功！")
            location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }


    function qx() {
        location.reload()
    }

    //物理删除
    function deletecg(ctrId) {
        console.log(ctrId);
        if (confirm("确认删除")) {
            axios.delete("http://localhost:8088/demo/contractdel", {
                params: {
                    ctrId: ctrId
                }
            }).then(function (response) {
                if (response.data.code != 200) {
                    console.log("删除失败" + response.data.message);
                    return
                }
                console.log("删除成功")
                location.reload()
            }).catch(function (error) {
                console.log(error)
            })
        } else {
            console.log("您按了取消");
        }
    }

    //逻辑删除
    function ljdel(orderId) {
        console.log(orderId)
        if (confirm("确认删除！")) {
            axios.get("http://localhost:8088/demo/ljdel", {
                params: {
                    orderId: orderId
                }
            }).then(function (a) {
                console.log(a)
                if (a.data.code != 200) {
                    alert("删除失败！！！")
                    return
                }
                orderId: ""
                alert("删除成功！！！")
                location.reload()
            }).catch(function (error) {
                console.log(error)
            })
        } else {
            console.log("您已取消删除！！")
        }
    }

    //根据id查询
    function selectid(orderId) {
        console.log("编号" + orderId)
        axios.get("http://localhost:8088/demo/findByid", {
            params: {
                orderId: orderId
            }
        }).then(function (a) {
            console.log(a.data.data);
            console.log(data.se);
            data.se = a.data.data;
            console.log(data.se)
        }).catch(function (error) {
            console.log(error);
        })
    }

    //查询客户
    function khsel() {
        axios.get("http://localhost:8088/demo/khcx").then(function (a) {
            console.log(a.data.data);
            data.khname = a.data.data;
            console.log(khname)
        }).catch(function (error) {
            console.log(error);
        })
    }

    //查询报价总金额、id
    function baojiasel() {
        axios.get("http://localhost:8088/demo/baojiasel").then(function (a) {
            console.log(a.data.data);
            data.bjzje = a.data.data;
            console.log(bjzje)
        }).catch(function (error) {
            console.log(error);
        })
    }

    //查询详情
    function selxq(o) {
        console.log("编号" + o)
        axios.get("http://localhost:8088/demo/selxq", {
            params: {
                orderId: o
            }
        }).then(function (a) {
            console.log(a.data.data);
            data.orders = a.data.data;
            console.log(data.orders)
        }).catch(function (error) {
            console.log(error);
        })
    }

    //滑轮函数
    const count = ref(0)
    const load = () => {
        count.value += 2
    }
</script>
