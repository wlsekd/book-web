<template>
    <div class="search-song-lists">
        <content-list :contentList="albumDatas"></content-list>
        <div class="pagination">
            <el-pagination
                background
                layout="total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="albumDatas.length"
                @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
import ContentList from '../ContentList.vue'
import {getBookListLikeTitle} from '../../api'
import {mixin} from '../../mixins'
export default{
    name:'search-book-lists',
    mixins:[mixin],
    components:{
        ContentList,
    },
    data(){
        return{
            albumDatas:[],
            pageSize:5, //分页大小
            currentPage:1,
        }
    },
    mounted(){
        this.getSearchList()
    },
    computed:{
         //计算当前搜索结果的数据
         data(){
            return this.albumDatas.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        },
    },
    methods:{
        getSearchList(){
            if(!this.$route.query.keywords){
                this.notify('您输入的内容为空','warning')
            }else{
                getBookListLikeTitle(this.$route.query.keywords)
                .then(res=>{
                    if(res){
                        this.albumDatas=res
                    }else{
                        this.notify('暂无书单','warning')
                    }
                })
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
@import '../../assets/css/search-song-Lists.scss'
</style>