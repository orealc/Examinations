import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate"

const store=createStore({
    state:{
        userInfo:{
            userid:"",
            username:"",
            token:""
        },
        sjid:"",//考试id
        tkid:"",//题库id
        tklx:"",
    },
    getters:{
        token:(state)=>{
            return state.userInfo.token
        },
        id:(state)=>{
            return state.sjid
        },
        tid:(state)=>{
            return state.tkid
        },
        lx:(state)=>{
            return state.tklx
        }
    },
    mutations:{
        updateUserInfo(state,user){
            if(user==null){
                sessionStorage.removeItem('state')
                console.log("清空用户信息")
                state.userInfo.username=""
                state.userInfo.token=""
                state.userInfo.userid=""

            }else{
                console.log("更新用户状态.......用户名:%s",user.username)
                state.userInfo.username=user.userName
                state.userInfo.userid=user.userid
                state.userInfo.token=user.token
                console.log("更新用户状态.......用户信息:%o",state.userInfo.menus)
                sessionStorage.setItem('state', JSON.stringify(state))
            }

        },
        bcid(state,id){
            console.log("------vuex")
            state.sjid=id;
            console.log("---id",state.sjid);
            sessionStorage.setItem('state', JSON.stringify(state))
        },
        tikuid(state,id){
            console.log("题库id",id)
            state.tkid=id;
            sessionStorage.setItem('state', JSON.stringify(state))
        },
        tklx(state,name){
            console.log("题库类型",name)
            state.tklx=name;
            sessionStorage.setItem('state', JSON.stringify(state))
        }

    }
})

export default store
