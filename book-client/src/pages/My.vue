<template>
    <div class="setting">
        <div class="leftCol">
            <div class="settingsMainHeader">我的</div>
            <ul class="setting-aside">
                <li v-for="(item,index) in settingList" :key="index" :class="{activeColor:activeName==item.name}"
                    @click="handleClick(item)">
                    {{ item.name }}
                </li>
            </ul>
        </div>
        <div class="contentCol">
            <comment :is="componentSrc"></comment>
        </div>
    </div>
</template>
<script>
import {mixin} from '../mixins'
import {mapGetters} from 'vuex'
import authorCollect from '../components/my/AuthorCollect'
import bookCollect from '../components/my/bookCollect.vue'
import bookListCollect from '../components/my/bookListCollect.vue'
import bookManage from '../components/my/bookManage.vue'
import bookListManage from '../components/my/bookListManage.vue'
export default{
    name:'my',
    mixins:[mixin],
    components:{
        authorCollect,
        bookCollect,
        bookListCollect,
        bookManage,
        bookListManage,
    },
    data(){
        return{
            settingList:[
                {name:'作者关注',path:'authorCollect'},
                {name:'书籍收藏',path:'bookCollect'},
                {name:'书单收藏',path:'bookListCollect'},
                {name:'书籍管理',path:'bookManage'},
                {name:'书单管理',path:'bookListManage'},
            ],
            activeName:'作者关注',
            componentSrc:'authorCollect',
        }
    },
    methods:{
        handleClick(item){
            this.activeName=item.name
            this.componentSrc=item.path
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/setting.scss'
</style>