import Vue from 'vue'
import Vuex from 'vuex'
import config from './config'
import user from './user'
import book from './book'
import VuexPersistence from 'vuex-persist'
Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})
const store=new Vuex.Store({
    modules:{
        config,
        user,
        book
    },
    plugins: [vuexLocal.plugin]
})

export default store