//图片地址缓存
import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'
Vue.use(Vuex)

const vuexLocal=new VuexPersistence({
    storage:window.localStorage
})
const store=new Vuex.Store({
    plugins:[vuexLocal.plugin],
    state:{
        HOST:'http://127.0.0.1:8888',
        url:'',     //地址
        id:'',
        loginIn:false,//是否登录
    },
    getters:{
        url:state=>state.url,
        id:state=>state.id,
        loginIn:state=>state.loginIn
    },
    mutations:{
        setUrl:(state,url)=>{state.url=url},
        setImmediate:(state,id)=>{state.id=id},
        setLoginIn:(state,loginIn)=>{state.loginIn=loginIn}
    }
})

export default store