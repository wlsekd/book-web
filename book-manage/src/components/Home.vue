<template>
    <div>
        <the-header></the-header>
        <the-aside></the-aside>
        <div class="content-box" :class="{'content-collapse':collapse}">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import TheHeader from './TheHeader.vue'
import TheAside from './TheAside.vue'
import bus from "../assets/js/bus"
export default{
    components:{
        TheHeader,
        TheAside
    },
    data(){
        return{
            collapse:false
        }
    },
    created(){
        //通过collapse修改content-box
        bus.$on('collapse',(msg)=>{
                this.collapse=msg
            })
    }
}
</script>
