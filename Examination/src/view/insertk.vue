<template>
	<div style="padding:200px; padding-left: 500px">
		<div>
			<!-- 基本信息 -->
			<div style="font-weight:bold;">
				基本信息
			</div>
			<div style="border-radius: 10px; border: 1px solid gainsboro; padding-top: 10px; width: 560px; height: 240px;float: left;">
				<el-form :model="tkform" label-width="90px">
					<el-form-item label="题库名称">
						<el-input v-model="tkform.qbname" style="width:240px" clearable />
					</el-form-item>
					<el-form-item label="题库分类" style="width:280px;">
						<el-select v-model="tkform.qbclassification" placeholder="请选择分类" clearable>
							<el-option label="Zone one" value="shanghai" />
							<el-option label="Zone two" value="beijing" />
						</el-select>
					</el-form-item>
					<el-form-item label="题库描述">
						<el-input v-model="tkform.desc" type="textarea" style="width: 450px;" />
					</el-form-item>
				</el-form>
			</div>
		</div>
		<p style="position: relative; right: 350px;top: 280px;">
			<el-button type="primary" @click="inspcplanfq(tkform)">确定</el-button>
			<el-button>取消</el-button>
		</p>
	</div>
</template>

<script lang="ts" setup>
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
		computed
	} from 'vue'
	import {
		ElTable,
		FormInstance
	} from 'element-plus'
	import axios from 'axios'
	//题库字段
	const tkform = reactive({
		questionbankid: '',
		qbname: '',
		qbclassification: '',
		qbdescription: '',
		qbnuqbtimember: '',
		qbtime: ''
	})
	const onSubmit = () => {
		alert("确定")
	}
	const submitForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.validate((valid) => {
			if (valid) {
				console.log('submit!')
			} else {
				console.log('error submit!')
				return false
			}
		})
	}

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.resetFields()
	}
	//新增题库
	function inspcplanfq(tkform) {
		axios.post("http://localhost:8089/insertk", tkform).then(function(a) {
			if (a.data.code != 200) {
				// ElMessage.error("新增失败")
				// location.reload()
				alert("添加失败")
			}
			// ElMessage.success("新增成功")
			alert("新增成功")
			tkform: ""
			// location.reload()
		}).catch(function(error) {
			console.log(error)
		})
	}
</script>
