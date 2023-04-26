<template>
    <div class="singer">
        <ul class="singer-header">
            <li v-for="(item,index) in authorLocation" :key="index" @click="handleChangeView(item.name)"
                :class="{active:item.name==activeLocation}">
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
import {getAllAuthor,getAuthorOfLocation} from '../api'
import {mixin} from '../mixins'
import {authorLocation} from '../assets/data/author'
export default{
    name:'author',
    mixins:[mixin],
    components:{
        ContentList,
    },
    data(){
        return{
            albumDatas:[],      //作者数据
            pageSize:15,        //页面大小，一页15条数据
            currentPage:1,      //当前页默认第一页
            authorLocation:[],           //作者地区
            activeLocation:'全部' //当前地区
        }
    },
    computed:{
        //计算当前表格数据
        data(){
            return this.albumDatas.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    mounted(){
        this.authorLocation=authorLocation
        this.getAuthorList()
    },
    methods:{
        getAuthorList(){
            getAllAuthor()
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
        //根据location显示对应的作者
        handleChangeView(location){
            this.albumDatas=[]
            this.activeLocation=location
            if(location=='全部'){
                this.getAuthorList()
            }else{
                this.getAuthorOfLocation(location)
            }
        },
        //根据地区查询作者
        getAuthorOfLocation(location){
            getAuthorOfLocation(location)
            .then(res=>{
                this.currentPage=1
                this.albumDatas=res
            })
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/singer.scss'
</style>