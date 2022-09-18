<template>
    <el-form label-width="100px" style="width: 380px;" v-model="loginData">
        <el-form-item label="用户名" prop="userName">
            <el-input v-model="loginData.userName" />
        </el-form-item>
        <el-form-item label="性别" prop="userName">
            <el-radio-group v-model="radio">
                <el-radio :label="男">男</el-radio>
                <el-radio :label="女">女</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="输入密码" prop="userPass">
            <el-input v-model="loginData.userPass" type="password" />
        </el-form-item>
        <el-form-item label="确认密码" prop="userPass">
            <el-input v-model="loginData.userPass2" type="password" />
        </el-form-item>
        <el-form-item  prop="userPass">
            <el-button type="primary">确认</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup>
    import {ref,reactive} from 'vue'
    import {useRouter} from 'vue-router'
    const loginData=reactive({
        userName:'',
        userPass:'',
        userPass2:'',
    })
    const router=useRouter()

    function qx(){
        router.push({name:'LoginView'})
    }

</script>

<style scoped>

</style>