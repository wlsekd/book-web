<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRouters"
            :collapse="collapse"
            background-color="#334256"
            text-color="#ffffff"
            active-text-color="#20afff"
            router
        >
            <template v-for="item in items">
                <template>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>
                        {{ item.title }}
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
import bus from "../assets/js/bus"
export default{
    data(){
        return{
            collapse:false,
            items:[
                {
                    icon:'el-icon-document',
                    index:'Info',
                    title:'系统首页'
                },
                {
                    icon:'el-icon-document',
                    index:'User',
                    title:'用户管理'
                },
                {
                    icon:'el-icon-document',
                    index:'Author',
                    title:'作者管理'
                },
                {
                    icon:'el-icon-document',
                    index:'BookList',
                    title:'书单管理'
                },
                
            ]
        }
    },
    computed:{
        onRouters(){
            return this.$route.path.replace('/','')
        }
        
    },
    created(){
            //通过bus进行组件间的通信，来折叠侧边栏
            bus.$on('collapse',(msg)=>{
                this.collapse=msg
            })
        }
}
</script>

<style scoped>
.sidebar{
    display: block;
    position: absolute;
    left:0;
    top:70px;
    bottom: 0;
    background-color: #334256;
    overflow-y:scroll;
}
.sidebar::-webkit-scrollbar{
    width:0;
}
.sidebar-el-menu:not(.el-menu--collapse){
    width: 150px;
}
.sidebar >ul{
    height: 100%;
}
</style>