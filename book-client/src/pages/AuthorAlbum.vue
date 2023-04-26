<template>
    <div class="singer-album">
        <div class="album-slide">
            <div class="singer-img">
                <img  :src="attachImageUrl(author.pic)">
            </div>
            <ul class="info">
                <el-button type="success" style="margin-bottom: 10px;" @click="collect(userId,authorId,2)" v-show="!isCollect">关注</el-button>
                <el-button type="info" style="margin-bottom: 10px;" @click="delCollect(userId,authorId,2)" v-show="isCollect">已关注</el-button>
                <li v-if="author.sex==0||author.sex==1">{{ attachSex(author.sex) }}</li>
                <li>生日：{{ attachBirth(author.birthday_time) }}</li>
                <li>故乡：{{author.location}}</li>
            </ul>
        </div>
        <div class="album-content">
            <div class="intro">
                <h2>{{ author.name }}</h2>
                <span>{{ author.introduction }}</span>
            </div>
            <div class="songs-body">
                <album-content :bookList="listOfBooks">
                    <template slot="title">书单</template>
                </album-content>
            </div>
        </div>
    </div>
</template>

<script>
import {mixin} from '../mixins'
import {mapGetters} from 'vuex'
import {bookOfAuthorId} from '../api/index'
import AlbumContent from '../components/AlbumContent.vue'
export default{
    name:'author-album',
    mixins:[mixin],
    components:{
        AlbumContent,
    },
    data(){
        return{
            authorId:'',        //从route传来的AuthorId
            author:{},          //从store的tempList读取的作者信息

        }
    },
    computed:{
        ...mapGetters([
            'listOfBooks',      //当前书籍 列表
            'tempList',         //当前作者信息
            'loginIn',          //用户是否已登录
            'userId',           //当前用户id
            'isCollect',        //是否收藏
        ]),
    },
    created(){
        this.authorId=this.$route.params.id
        this.author=this.tempList
        this.getBookByAuthorId()
        this.haveCollect(this.userId,this.authorId,2)
    },
    methods:{
        //根据作者id查询作品
        getBookByAuthorId(){
            bookOfAuthorId(this.authorId)
            .then(res=>{
                this.$store.commit('setListOfBooks',res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //获取性别
        attachSex(value){
            if(value==0){
                return '女'
            }else if(value==1){
                return '男'
            }
            return ''
        },
        //获取生日
        attachBirth(val){
            return val.substr(0,10)
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/singer-album.scss'
</style>