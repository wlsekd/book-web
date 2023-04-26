<template>
    <div class="setting">
        <div class="leftCol">
            <div class="settingsMainHeader">设置</div>
            <ul class="setting-aside">
                <li v-for="(item,index) in settingList" :key="index" :class="{activeColor:activeSet==item.name}"
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
import Info from '../components/setting/Info.vue'
import Upload from '../components/setting/Upload.vue'
import Comment from '../components/Comment.vue'
export default{
    name:'setting',
    mixins:[mixin],
    components:{
        Info,
        Upload,
        Comment,
    },
    data(){
        return{
            settingList:[
                {name:'个人信息',path:'Info'},
                {name:'修改头像',path:'Upload'},
            ],
            activeSet:'个人信息',
            componentSrc:'Info',
        }
    },
    methods:{
        handleClick(item){
            this.activeSet=item.name
            this.componentSrc=item.path
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/setting.scss'
</style>