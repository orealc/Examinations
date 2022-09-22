
<template>
	<div style="border: 1px solid #4377FB;width: 400px"></div>
	<div style="width: 400px;height: 50px;;margin-left: 460px;margin-top:100px;color: sandybrown" id="login3" >
			<h1>
				人力资源系统----考试系统
			</h1>
	</div>
<div style="width: 350px;margin-left:480px;" id="login2"  >
	<el-form label-width="100px" style="width: 380px;" v-model="loginData"  >
		<el-form-item label="用户名" prop="userName">
			<el-input v-model="loginData.userName" style="width: 150px" />
		</el-form-item>
		<el-form-item label="密码" prop="userPass">
			<el-input v-model="loginData.password"  type="password" style="width: 150px"  />
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="toLogin()">登录</el-button>
			<el-button type="primary" @click="qx()">注册</el-button>

		</el-form-item>
	</el-form>
</div>
</template>

<script setup>
	import {useRouter} from 'vue-router'
	import {useStore} from "vuex"
	const router=useRouter()
	const store=useStore()
	import {h,ref,reactive,onBeforeMount,onMounted,getCurrentInstance} from 'vue'
	import axios from "axios";
	import {ElMessage} from "element-plus";
	const loginData=reactive({
		userName:'',
		password:''
	})

	function qx(){
		router.push({name:'zhuce'})
	}

	function toLogin(){
		console.log("aaaaaaaaa"+loginData.userName,loginData.password)

		axios.get("http://localhost:8082/user/login", {
			params: {
				userName: loginData.userName,
				password: loginData.password
			}
		}).then(function(res) {
			console.log(res)
			if(res.data.data==null){
				ElMessage.error('密码或者账号有误!!!')
			}else{
				if(res.data.data.roleid ==1){
					store.commit("updateUserInfo",res.data.data)
					console.log("所有的值",res.data.data.roleid)
					console.log("token值+++",store.state.userInfo.token)
					console.log("userid+++",store.state.userInfo.userid)
					ElMessage.success('登录成功,欢迎您')
					router.push({name: 'yg'})
				}else {
					store.commit("updateUserInfo",res.data.data)
					console.log("所有的值",res.data.data.roleid)
					console.log("token值+++",store.state.userInfo.token)
					console.log("userid+++",store.state.userInfo.userid)
					ElMessage.success('登录成功,欢迎您')
					router.push({name: 'toubu'})
				}

			}
		}).catch(function(err) {
			console.log(err)
		})
	}



</script>
<style>
	#login{
		background-image: url("../assets/3.png");

	}
	#login2{
		background-image: url("../assets/6.jpg");
		border-radius:30px;
	}

</style>
