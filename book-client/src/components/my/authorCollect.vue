<template>
    <div style="margin-top: 50px;min-height: 500px;">
        <content-list :contentList="data"></content-list>
        <div class="pagination" style="margin-bottom: 15px;">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length"></el-pagination>
            </div>
    </div>
</template>

<script>
import ContentList from '../ContentList.vue'
import { mapGetters } from 'vuex';
import {collectOfUserId,getAuthorByAuthorId} from '../../api/index'
export default{
    name:'authorCollect',
    components:{
        ContentList,
    },
    computed:{
        ...mapGetters([
            'userId',
        ]),
        //计算当前表格数据
        data(){
            return this.albumDatas.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    data(){
        return{
            albumDatas:[],//关注的列表
            pageSize:15,        //页面大小，一页15条数据
            currentPage:1,      //当前页默认第一页
        }
    },
    mounted(){
        this.getCollectAuthor()
    },
    methods:{
        //获取用户的收藏列表
        getCollectAuthor(){
            this.albumDatas=[]
            collectOfUserId(this.userId)
            .then(res=>{
                for(let item of res){
                    if(item.authorId!=''&&item.authorId!=null){
                        //alert(item.authorId)
                        this.getAuthor(item.authorId)
                    }
                }
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //将收藏列表中有作者id的数据进行查询
        getAuthor(id){
            getAuthorByAuthorId(id)
            .then(res=>{
                this.albumDatas.push(res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },

    }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/global.scss';
.pagination {
  @include layout(center);
}
</style>