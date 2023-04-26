<template>
    <div class="song-list-album">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(bookContent.pic)">
            </div>
            <div class="album-info">
                <el-button type="danger" style="margin-bottom: 10px;" @click="collect(userId,bookId,0)" v-show="!isCollect">收藏</el-button>
                <el-button type="info" style="margin-bottom: 10px;" @click="delCollect(userId,bookId,0)" v-show="isCollect">已收藏</el-button>
                <el-button type="success" style="margin-bottom: 10px;" @click="download">下载</el-button>
                <el-button type="primary" style="margin-bottom: 10px;" @click="showFile">预览</el-button>
                <h2>{{ replaceLName(bookContent.name) }}</h2>
                <h2>作者: {{ replaceFName(bookContent.name) }}</h2>
                <h2>简介</h2>
                <span>
                    {{ bookContent.introduction }}
                </span>
            </div>
        </div>
        <div class="album-content">
            <div class="album-title">
                <p>{{ bookContent.title }}</p>
            </div>
            <div class="album-score">
                <div>
                    <h3>书籍评分</h3>
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
                <comment :playId="bookId" :type="0"></comment>
            </div>
        </div>
        <el-dialog
        :visible="show === true"
        :append-to-body='true'
        :modal-append-to-body='true'
        :lock-scroll='true'
        :destroy-on-close='true'
        :close-on-click-modal='false'
        :width="'80%'"
        class="dialog-div-pre-style"
        :before-close="closePreviewClick"
        top="50px"
        center>
            <div v-if="show === true" class="dialog-body-content-base-style"
                style="justify-content: center; align-items: center">
                <embed :src="url" style="width: 100%; height: 600px"/>
            </div>
    </el-dialog>
    </div>
</template>

<script>
import {mixin} from '../mixins'
import {mapGetters} from 'vuex'
import {bookOfBookId,rankAdd,avaBookScore,download} from '../api/index'
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
            bookId:'',     //上个页面传来的书单id
            average:0,          //书评分
            rank:0,             //提交评价的分数
            bookContent:{},     //查询的书信息
            url:'',          //预览的url
            show:false,     // 显示预览
        }
    },
    computed:{
        ...mapGetters([
            'loginIn',          //用户是否登录
            'userId',           //当前登录的用户id
            'isCollect',        //是否收藏
        ]),
    },
    created(){
        this.bookId=this.$route.params.id   //上个页面传来的id
        this.getBook(this.bookId)
        this.getAverage(this.bookId)
        this.haveCollect(this.userId,this.bookId,0)
    },
    watch:{
        bookContent(){
            this.url=this.$store.state.config.HOST+this.bookContent.url
        }
    },
    methods:{
        //根据书id获取书信息
        getBook(id){
            bookOfBookId(id)
            .then(res=>{
                this.bookContent=res
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //获取评分
        getAverage(id){
            avaBookScore(id)
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
                params.append('bookId',this.bookId)
                params.append('userId',this.userId)
                params.append('score',this.rank*2)
                rankAdd(params)
                .then(res=>{
                    if(res.code==1){
                        this.notify('评分成功','success')
                        this.getAverage(this.bookId)
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
        },
        //下载
        download(){
            download(this.bookContent.url)
            .then(res=>{
                let content=res.data
                let eleLink=document.createElement('a')
                 if(this.bookContent.url.substring(this.bookContent.url.length-3)=='pdf'){
                    eleLink.download=`${this.bookContent.name}.pdf`
                 }else if(this.bookContent.url.substring(this.bookContent.url.length-4)=='epub'){
                    eleLink.download=`${this.bookContent.name}.epub`
                 }else{
                    eleLink.download=`${this.bookContent.name}.txt`
                 }
                // alert(this.bookContent.url.substring(this.bookContent.url.length-3))
                eleLink.style.display='none'
                //把字符内容转换为blob地址
                let blob=new Blob([content])
                eleLink.href=URL.createObjectURL(blob)
                //把链接地址加入document
                document.body.appendChild(eleLink)
                //触发点击事件
                eleLink.click()
                //然后移除掉新加的控件
                document.body.removeChild(eleLink)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //文件预览
        showFile(){
            if(this.bookContent.url.substring(this.bookContent.url.length-4)=='epub'){
                this.notify('无法预览epub文件,请下载后打开','error')
            }else{
                this.show=true
            }
        },
        //关闭预览
        closePreviewClick(){
            this.show=false
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss'
</style>
