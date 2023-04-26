<template>
    <div class="song-list-album">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(tempList.pic)">
            </div>
            <div class="album-info">
                <el-button type="danger" style="margin-bottom: 10px;" @click="collect(userId,bookListId,1)" v-show="!isCollect">收藏</el-button>
                <el-button type="info" style="margin-bottom: 10px;" @click="delCollect(userId,bookListId,1)" v-show="isCollect">已收藏</el-button>
                <h2>简介</h2>
                <span>
                    {{ tempList.introduction }}
                </span>
            </div>
        </div>
        <div class="album-content">
            <div class="album-title">
                <p>{{ tempList.title }}</p>
            </div>
            <div class="album-score">
                <div>
                    <h3>书单评分</h3>
                    <div>
                        <el-rate v-model="average" disabled></el-rate>
                    </div>
                </div>
                <span>{{ average*2 }}</span>
                <div>
                    <h3>用户评分</h3>
                    <div @click="setRank">
                        <el-rate v-model="rank" allow-half show-text></el-rate>
                    </div>
                </div>
            </div>
            <div class="songs-body">
                <album-content :bookList="listOfBooks">
                    <template slot="title">书单</template>
                </album-content>
                <comment :playId="bookListId" :type="1"></comment>
            </div>
        </div>
    </div>
</template>

<script>
import {mixin} from '../mixins'
import {mapGetters} from 'vuex'
import {listBookDetail,bookOfBookId,rankAdd,avaScore} from '../api/index'
import AlbumContent from '../components/AlbumContent.vue'
import Comment from '../components/Comment.vue'
export default{
    name:'book-list-album',
    mixins:[mixin],
    components:{
        AlbumContent,
        Comment,
    },
    data(){
        return{
            bookLists:[],       //当前页面需要展示的书列表
            bookListId:'',     //上个页面传来的书单id
            average:0,          //书单评分
            rank:0,             //提交评价的分数
        }
    },
    computed:{
        ...mapGetters([
            'listOfBooks',      //当前书单的书籍列表
            'tempList',         //当前书单对象
            'loginIn',          //用户是否登录
            'userId',           //当前登录的用户id
            'isCollect',        //是否收藏
        ]),
    },
    created(){
        this.bookListId=this.$route.params.id   //上个页面传来的id
        this.getBookId()
        this.getAverage(this.bookListId)
        this.haveCollect(this.userId,this.bookListId,1)
    },
    methods:{
        //获取当前书单的书本列表
        getBookId(){
            listBookDetail(this.bookListId)
            .then(res=>{
                for(let item of res){
                    this.getBookList(item.bookId)
                }
                this.$store.commit('setListOfBooks',this.bookLists)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //根据书id获取书信息
        getBookList(id){
            bookOfBookId(id)
            .then(res=>{
                this.bookLists.push(res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //获取书单评分
        getAverage(id){
            avaScore(id)
            .then(res=>{
                this.average=res/2
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //提交评分
        setRank(){
            if(this.loginIn){
                let params=new URLSearchParams()
                params.append('bookListId',this.bookListId)
                params.append('userId',this.userId)
                params.append('score',this.rank*2)
                rankAdd(params)
                .then(res=>{
                    if(res.code==1){
                        this.notify('评分成功','success')
                        this.getAverage(this.bookListId)
                    }else{
                        this.notify('评分失败','error')
                    }
                })
                .catch(err=>{
                    this.notify('你已经进行过评分','error')
                })
            }else{
                this.rank=null
                this.notify('请先登录','warning')
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss'
</style>
