<template>
	<div style="padding:10px">
		<!-- 搜索区域 -->
		<div style="margin: 10px 0">
			<!--      clearable 清空的按钮-->
			<el-select v-model="data.ht " placeholder="选择或搜索题库" autocomplete="off">
				<el-option v-for="item in data.khname" :label="item.orderTitle" :value="item.orderTitle" />
			</el-select>
			<el-select v-model="data.kh" placeholder="选择章节" style="width: 15%" clearable>
				<el-option v-for="item in data.khname" :label="item.customerName" :value="item.customerName" />
			</el-select>
			<el-select v-model="data.zts" placeholder="选择题型" style="width: 15%" clearable>
				<el-option label="单选题" value="单选题" />
				<el-option label="多选题" value="多选题" />
				<el-option label="判断题" value="判断题" />
			</el-select>
			<el-input v-model="data.zt" placeholder="试题内容" style="width: 15%" clearable></el-input>
			<el-button type="primary" style="margin-left: 5px" @click="seltjcx()">查询</el-button>
		</div>
		<!-- 功能区域 -->
		<div style="margin: 10px 0">
			<el-button type="primary" @click="xzForm = true">新增</el-button>
			<!-- <el-button type="primary">删除</el-button> -->
		</div>
		<el-scrollbar height="540px" v-infinite-scroll="load">
			<el-table ref="multipleTable" :data="data.jiaofus" border stripe style="width: 100%" tooltip-effect="dark"
				@selection-change="handleSelectionChange" height="540px">
				<!-- sortable排序 -->
				<el-table-column prop="jfId" label="ID" />
				<!-- <el-table-column type="selection" width="55" /> -->
				<el-table-column prop="planJftime" label="计划交付时间" />
				<el-table-column prop="orderTitle" label="所属订单合同" />
				<el-table-column prop="customerName" label="对应客户" />
				<el-table-column prop="proName" label="产品" />
				<el-table-column prop="proMoney" label="单价"  />
				<el-table-column prop="number" label="数量"/>
				<el-table-column prop="total" label="总价" >
					<template v-slot="scp">
						<span>
								{{scp.row.proMoney * scp.row.number}}
						</span>
					</template>
				</el-table-column>
				<el-table-column prop="state" label="是否交付" />
				<el-table-column prop="cjr" label="创建人" />
				<el-table-column label="操作" width="150px">
					<template #default=scope v-slot="scope">
						<el-button size="small" @click="ljdel(scope.row.jfId)">删除</el-button>
						<el-button size="small" @click="xiugaiForm= true;selectid(scope.row.jfId)">修改</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-scrollbar>
		<!-- 添加弹窗 -->
		<el-dialog v-model="xzForm" title="新增交付计划">
			<el-form ref="ruleFormRef" :model="jiaofubiao " :rules="rules" label-width="120px" :size="formSize"
				:inline="true" class="demo-form-inline">
				<el-form-item label="所属合同订单:" prop="orderTitle">
					<el-select v-model="jiaofubiao.orderTitle " autocomplete="off">
						<el-option v-for="item in data.khname" :label="item.orderTitle" :value="item.orderTitle" />
					</el-select>
				</el-form-item>
				<el-form-item label="对应产品:" prop="proName">
					<el-select v-model="jiaofubiao.proName " autocomplete="off">
						<el-option v-for="item in data.khname" :label="item.proName" :value="item.proName" />
					</el-select>
				</el-form-item>
				<el-form-item label="计划交付时间:">
					<el-col :span="20">
						<el-form-item prop="planJftime">
							<el-date-picker v-model="jiaofubiao.planJftime" type="date" style="width: 100%" />
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item label="交付状态:" prop="state">
					<el-radio-group v-model="jiaofubiao.state">
						<el-radio label="未交付" />
						<el-radio label="已交付" />
						<el-radio label="意外终止" />
					</el-radio-group>
				</el-form-item>
				<el-form-item label="单价:">
					<el-input v-model="jiaofubiao.proMoney" />
				</el-form-item>
				<el-form-item label="数量:">
					<el-input v-model="jiaofubiao.number" />
				</el-form-item>
				<el-form-item label="总价:">
					<el-input v-model="jiaofubiao.total" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="xzForm= true" :validate-event="false">确认
					</el-button>
					<el-button @click="resetForm(ruleFormRef),xzForm = false,qx()">取消</el-button>

				</el-form-item>
			</el-form>

		</el-dialog>
		<!-- 修改弹窗 -->
		<el-dialog v-model="xiugaiForm" title="修改">
			<el-form :model="data.xg" :rules="rules" label-width="120px" :inline="true" class="demo-form-inline">
				<!-- <el-form-item label="对应客户" prop="cId">
					<el-input v-model="data.se.cId" />
				</el-form-item> -->
				<el-form-item label="合同订单主题">
					<el-input v-model="data.xg.orderTitle" placeholder="请输入主题" autocomplete="off" />
				</el-form-item>
				<!-- <el-form-item label="合同订单号">
					<el-input v-model="data.se.conNumber" autocomplete="off" />
				</el-form-item> -->
				<el-form-item label="付款方式">
					<el-select v-model="data.xg.fk " autocomplete="off">
						<el-option label="现金" value="现金" />
						<el-option label="微信转账" value="微信转账" />
						<el-option label="支付宝转账" value="支付宝转账" />
						<el-option label="支票" value="支票" />
						<el-option label="银行卡" value="银行卡" />
						<el-option label="银行卡转账" value="银行卡转账" />
						<el-option label="其他" value="其他" />
					</el-select>
				</el-form-item>
				<el-form-item label="分类">
					<el-select v-model="data.xg.fenlei">
						<el-option label="产品销售" value="产品销售" />
						<el-option label="服务" value="服务" />
						<el-option label="业务合作" value="业务合作" />
						<el-option label="代理分销" value="代理分销" />
						<el-option label="其他" value="其他" />
					</el-select>
				</el-form-item>
				<!-- <el-form-item label="总金额">
					<el-input v-model="data.se.conSendOutMoney" />
				</el-form-item>-->
				<el-form-item label="状态" prop="state">
					<el-radio-group v-model="data.xg.state">
						<el-radio label="执行中" />
						<el-radio label="结束" />
						<el-radio label="意外终止" />
					</el-radio-group>
				</el-form-item>
				<el-form-item label="开始日期">
					<el-col :span="10">
						<el-form-item prop="stateTime">
							<el-date-picker v-model="data.xg.stateTime" type="date" style="width: 100%" />
						</el-form-item>
					</el-col>
					<el-col class="text-center" :span="3">
						结束日期
					</el-col>
					<el-col :span="11">
						<el-date-picker v-model="data.xg.endTime" type="date" style="width: 100%" />
					</el-col>
				</el-form-item>
				<!-- <el-form-item label="签约日期" required>
					<el-col :span="20">
						<el-form-item prop="conSigningDate">
							<el-date-picker v-model="data.se.conSigningDate" type="date" style="width: 100%" />
						</el-form-item>
					</el-col>
				</el-form-item> -->
				<!-- <el-form-item label="我方签约人" prop="staffId">
					<el-input v-model="data.se.staffId" />
				</el-form-item> -->
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="showStaffForm = false,qx()">取消</el-button>
					<el-button type="primary" @click="update(data.xg),showStaffForm = false">确认</el-button>
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
		jiaofus: [],
		ht: '',
		zts: '',
		xg: [],
		cp: '',
		kh: '',
		hts: [],
		cps: [],
		zt: []

	})
	const xiugaiForm = ref(false)
	const xzForm = ref(false)
	const isShow = ref(false)
	const jfId = ref(0)
	const formSize = ref('default')
	const radio = ref('selected and disabled')
	const ruleFormRef = ref < FormInstance > ()
	//新增的字段
	const jiaofubiao = reactive({
		proName: '',
		planJftime: '',
		state: '',
		orderTitle: '',
		proMoney: '',
		number: '',
		total: ''
	})
	//多表查询
	onBeforeMount(() => {
		axios.get("http://localhost:8088/demo/querySelJiaofu/1").then(function(a) {
			console.log(a.data.data)
			data.jiaofus = a.data.data
		})
	})
	//滑轮函数
	const count = ref(0)
	const load = () => {
		count.value += 2
	}
</script>
