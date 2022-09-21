import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate"

const store=createStore({
    state:{
        userInfo:{
            userid:"",
            username:"",
            token:""
        },
    },
    getters:{
        token:(state)=>{
            return state.userInfo.token
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

        }
    }
})

export default store
