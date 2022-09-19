<template>
        <!--<el-container>-->
               <!-- <el-header v-show="store.state.userInfo.isValidate">
                        <el-menu :default-active="activeMenu" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                                 background-color="#989898" text-color="#fff" active-text-color="#ffd04b" width="100%"
                                 style="margin-left: -20px;" :ellipsis=false>
                                <el-menu-item index="1" v-show="store.state.userInfo.isValidate">crm客户管理</el-menu-item>
                                <el-sub-menu v-show="store.state.userInfo.isValidate" style="margin-left: 1332px;">
                                        <template #title id="mz">{{store.state.userInfo.userName}}</template>
                                        &lt;!&ndash;          <el-button type="primary" @click="dialogFormVisible = true" >个人信息</el-button>&ndash;&gt;
                                        &lt;!&ndash;					<el-menu-item type="primary" @click="dialogFormVisible = true">个人信息</el-menu-item>&ndash;&gt;
                                        <el-menu-item index="/logout" v-show="store.state.userInfo.isValidate" @click="sx()" >退出系统</el-menu-item>
                                </el-sub-menu>

                        </el-menu>
                </el-header>-->
                <!--<el-container>
                        <el-aside width="200px" v-show="store.state.userInfo.isValidate">
                                <el-row class="tac">
                                        <el-col :span="24">
                                                <el-scrollbar max-height="670px">
                                                        <el-menu :default-active="activeMenu" class="el-menu-demo" mode="vertical"
                                                                 @select="handleSelect" background-color="#545c64" text-color="#fff"
                                                                 active-text-color="#ffd04b" unique-opened :ellipsis=false>
                                                                <el-sub-menu v-for="nav in store.state.userInfo.menus"
                                                                             v-show="store.state.userInfo.isValidate" :index="nav.url"
                                                                             style="background-color: aquamarine;">
                                                                        <template #title>
                                                                                <span>{{nav.menuName}}&#45;&#45;{{nav.componentPath}}</span>
                                                                        </template>
                                                                        <el-menu-item v-for="children in nav.asideChildren" :index="children.url">
                                                                                {{children.menuName}}
                                                                        </el-menu-item>
                                                                </el-sub-menu>
                                                        </el-menu>
                                                </el-scrollbar>
                                        </el-col>
                                </el-row>
                        </el-aside>
                        <el-main >-->
                                <router-view></router-view>
                    <!--    </el-main>
                </el-container>
        </el-container>-->

</template>