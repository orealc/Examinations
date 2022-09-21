
<template>
	<!--<el-row>
		<el-col :span="16" :offset="8">
			<el-form ref="loginForm" :model="loginData" label-width="100px" style="width:380px;">
				<el-form-item label="用户名" prop="userName">
					<el-input v-model="loginData.userName"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="userPass">
					<el-input v-model="loginData.userPass"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="toLogin">登录</el-button>&nbsp;
					<el-button type="primary" @click="rest">重置</el-button>
				</el-form-item>
			</el-form>
		</el-col>
	</el-row>-->
	<div>
		<span style="border: 1px sandybrown " >
			考试系统
		</span>
			<el-form label-width="100px" style="width: 380px;" v-model="loginData" >
				<el-form-item label="用户名" prop="userName">
					<el-input v-model="loginData.userName"  />
				</el-form-item>
				<el-form-item label="密码" prop="userPass">
					<el-input v-model="loginData.userPass"  type="password" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary">登录</el-button>
					<el-button type="primary" @click="qx()" >注册</el-button>
					<el-button type="primary" @click="yg()" >员工登录</el-button>
          <el-button type="primary" @click="yy()" >试卷页面</el-button>
          <el-button type="primary" @click="ks()" >考试页面</el-button>
          <el-button type="primary" @click="hv()" >题库页面</el-button>
          <el-button type="primary" @click="tegl()" >试题页面</el-button>
          <el-button type="primary" @click="uptest()" >修改试题页面</el-button>
				</el-form-item>
			</el-form>
	</div>
</template>

<script setup>
	import {useRouter} from 'vue-router'
	const router=useRouter()
	import {ref,reactive} from 'vue'
	const loginData=reactive({
		userName:'',
		userPass:'',
		userPass2:'',
	})
	function qx(){
		router.push({name:'zhuce'})
	}
	function yg() {
		router.push({path:'/yg'})
	}
  function yy(){
    router.push({name:'ShiJuan'});
  }
  function ks(){
    router.push({name:'KaoShi'});
  }
  function hv(){
    router.push({name:'HomeView'});
  }function tegl(){
    router.push({path:'/testgl'});
  }function uptest(){
    router.push({path:'/uptest'});
  }
</script>
<style>

</style>
