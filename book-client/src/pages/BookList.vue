<template>
    <div class="song-list">
        <ul class="song-list-header">
            <li v-for="(item,index) in styles" :key="index" @click="handleChangeView(item.name)"
                :class="{active:item.name==activeStyle}">
                {{ item.name }}
            </li>
        </ul>
        <div>
            <content-list :contentList="data"></content-list>
            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length"></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import ContentList from '../components/ContentList.vue'
import {getAllBookList,getBookListOfLikeStyle} from '../api'
import {mixin} from '../mixins'
import {style} from '../assets/data/style'
export default{
    name:'book-list',
    mixins:[mixin],
    components:{
        ContentList,
    },
    data(){
        return{
            albumDatas:[],      //书单或书数据
            pageSize:15,        //页面大小，一页15条数据
            currentPage:1,      //当前页默认第一页
            styles:[],           //风格
            activeStyle:'全部' //当前风格
        }
    },
    computed:{
        //计算当前表格数据
        data(){
            return this.albumDatas.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    mounted(){
        this.styles=style
        this.getBookList()
    },
    methods:{
        getBookList(){
            getAllBookList()
            .then(res=>{
                if(res){
                    this.currentPage=1
                    this.albumDatas=res
                }
            })
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
        //根据style显示对应的书单
        handleChangeView(style){
            this.albumDatas=[]
            this.activeStyle=style
            if(style=='全部'){
                this.getBookList()
            }else{
                this.getSongListOfStyle(style)
            }
        },
        //根据sytle查询歌单
        getSongListOfStyle(style){
            getBookListOfLikeStyle(style)
            .then(res=>{
                this.currentPage=1
                this.albumDatas=res
            })
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/song-list.scss'
</style>