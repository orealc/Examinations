import {createStore} from 'vuex'
const store=createStore({
    state:{
        userInfo:{
            userName:"",
            isValidate:false,
            token:"",
            menus:[]
        },
        sjid:"",//考试id
        tkid:"",//题库id
        tklx:"",
    },
    getters:{
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