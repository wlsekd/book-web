<template>
    <div class="setting">
        <div class="leftCol">
            <div class="settingsMainHeader">发布</div>
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
import BookPublish from '../components/publish/bookPublish'
import BookListPublish from '../components/publish/bookListPublish'
export default{
    name:'publish',
    mixins:[mixin],
    components:{
        BookPublish,
        BookListPublish,
    },
    data(){
        return{
            settingList:[
                {name:'书籍上传',path:'bookPublish'},
                {name:'书单发布',path:'bookListPublish'},
            ],
            activeName:'书籍上传',
            componentSrc:'bookPublish',
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