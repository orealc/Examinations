<template>
    <el-container class="home-container">
        <el-container class="home-container">
            <el-header style="background-color: cornflowerblue" >
                <img src="https://cdn.jeegen.com/2022/7/7/1657157655268-c53b50b7.png" style="height: 38px;">
                <div class="title">人力资源系统----考试系统</div>
                <a style="margin-left: 600px" id="login4" @click="qx()" >退出</a>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu unique-opened='false' active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
                             default-active="2" text-color="#fff" @open="handleOpen" @close="handleClose"
                             @select="menuClick">
                        <el-sub-menu  index="1" router>
                            <template #title><span>考试管理</span></template>
                            <el-menu-item index="/Kaoshi">考试管理</el-menu-item>
                            <el-menu-item index="/Shijuan">试卷管理</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="2" router>
                            <template #title><span>员工管理</span></template>
                            <el-menu-item index="/genrenxx">个人信息</el-menu-item>
                            <el-menu-item index="/BuMen1">员工信息</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="3" router>
                            <template #title><span>题库管理</span></template>
                            <el-menu-item index="/YuanGong1">题库管理</el-menu-item>
                            <el-menu-item index="/YuanGong1">试题管理</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="4" router>
                            <template #title><span>系统管理</span></template>
                            <el-menu-item index="/XinZi1">操作日志</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view />
                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "toubu",
        methods: {
            menuClick(index) {
                this.$router.push(index);
            }
        }
    }
</script>

<style scoped="scoped">
    .el-header {
        background-color: #86c4e9;
        display: flex;
        align-items: center;

    }
    .el-header .title{
        font-size: 38px;
        font-family: "华文楷体";
        color: white;
    }
    .el-main {
        background-color: white;
    }

    .home-container {
        height: 100%;
    }
    #login4:hover{
        cursor:pointer

    }
</style>
