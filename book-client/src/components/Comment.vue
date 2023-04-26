<template>
    <div>
        <div class="comment">
            <h2>评论</h2>
            <div class="comment-msg">
                <div class="comment-img">
                    <img :src="attachImageUrl(avator)">
                </div>
                <el-input class="comment-input" type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea">
                </el-input>
            </div>
            <el-button type="primary" class="sub-btn" @click="postComment">评论</el-button>
        </div>
        <p>用户评论：共{{this.commentList.length}}条评论</p>
        <hr/>
        <ul class="popular" v-for="(item,index) in data" :key="index">
            <li>
                <div class="popular-img">
                    <img :src="attachImageUrl(getUserByCommentUserId(item.userId).avator)">
                </div>
                <div class="popular-msg">
                    <ul>
                        <li class="name">{{ getUserByCommentUserId(item.userId).username }}</li>
                        <li class="time">{{ item.commentTime }}</li>
                        <li class="content">{{ item.content }}</li>
                    </ul>
                </div>
                <div class="up" ref="up" @click="postUp(item.id,item.up,index)">
                    <svg class="icon">
                        <use xlink:href="#icon-zan"></use>
                    </svg>
                    {{ item.up }}
                </div>
            </li>
        </ul>
        <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentPage" :page-size="pageSize" :total="commentList.length"></el-pagination>
            </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { addComment,getAllComment, getUserOfId,addLike } from '../api';
import {mixin} from '../mixins'
export default{
    name:'comment',
    mixins:[mixin],
    props:[
        'playId',       //书或书单id
        'type',         //0书籍1书单
    ],
    computed:{
        ...mapGetters([
            'loginIn',      //用户是否已经登录
            'userId',       //当前登录用户的id
            'id',           //书籍或书单id
            'avator',       //当前登录用户头像
        ]),
        data(){
            return this.commentList.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    data(){
        return{
            textarea:'',        //存放输入评论内容
            commentList:[],     //存放评论列表
            commentUser:[],     //评论用户数据
            pageSize:15,        //页面大小，一页15条数据
            currentPage:1,      //当前页默认第一页
        }
    },
    mounted(){
        this.getComment()
    },
    methods:{
        //根据评论的用户id查询用户信息
        getUserByCommentUserId(id){
            for(let item of this.commentUser){
                if(item.id==id){
                    return item
                }
            }
        },
        //提交评论
        postComment(){
            if(this.loginIn){
                let params=new URLSearchParams()
                if(this.type==0){   //通过type判断提交的是书还是书单的评论,0为书籍,1为书单
                    params.append('bookId',this.playId)
                }else{
                    params.append('bookListId',this.playId)
                }
                params.append('userId',this.userId)
                params.append('type',this.type)
                params.append('content',this.textarea)
                addComment(params)
                .then(res=>{
                    if(res.code==1){
                        this.notify('评论成功','success')
                        this.textarea=''
                        this.commentUser=[]
                        this.getComment()
                        //this.$router.go(0);
                    }else{
                        this.notify('评论失败','error')
                    }
                })
                .catch(err=>{
                    this.notify('评论失败','error')
                })
            }else{
                this.textarea=''
                this.notify('请先登录','warning')
            }
        },
        //评论列表
        getComment(){
            getAllComment(this.type,this.playId)
                .then(res => {
                    this.commentList = res;
                    for(let item of res){
                        this.getUsers(item.userId);
                    }
                })
                .catch(err =>{
                    this.notify('评论加载失败','error');
                })
        },
        //获取用户头像和昵称
        getUsers(id){
            getUserOfId(id)
            .then(res=>{
                this.commentUser.push(res)
            })
            .catch(err=>{
                this.notify('评论加载失败','error')
            })
        },
        //给某个评论点赞
        postUp(id,up,index){
            if(this.loginIn){
                let params=new URLSearchParams()
                params.append('id',id)
                params.append('up',up+1)
                addLike(params)
                .then(res=>{
                    if(res.code==1){
                        this.$refs.up[index].children[0].style.color='#2796cd'
                        this.getComment()
                    }else{
                        this.notify('点赞失败','error')
                    }
                })
                .catch(err=>{
                    this.notify('点赞失败','error')
                })
            }else{
                this.notify('请先登录','warning')
            }
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
    }
}

</script>
<style lang="scss" scoped>
@import '../assets/css/comment.scss'

</style>