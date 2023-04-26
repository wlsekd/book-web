<template>
    <div class="content">
        <h1 class="title">
            <slot name="title"></slot>
            <hr/>
        </h1>
        <ul>
            <li>
                <div class="song-item">
                    <span class="item-index"></span>
                    <span class="item-title">作品名</span>
                    <span class="item-name">作者</span>
                    <span class="item-intro">简介</span>
                </div>
            </li>
            <li v-for="(item,index) in data" :key="index">
                <div class="song-item" @click="toBookAlbum(item.id)">
                    <span class="item-index">{{ index+1 }}</span>
                    <span class="item-title">{{ replaceLName(item.name) }}</span>
                    <span class="item-name">{{ replaceFName(item.name) }}</span>
                    <span class="item-intro">{{ item.introduction }}</span>
                </div>
            </li>
        </ul>
        <div class="pagination" style="margin-bottom: 15px;">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentPage" :page-size="pageSize" :total="bookList.length"></el-pagination>
            </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { mixin } from '../mixins';
export default{
    name:'album-content',
    mixins:[mixin],
    props:[
        'bookList'
    ],
    computed:{
        ...mapGetters([
            'activeName'
        ]),
        //计算当前表格数据
        data(){
            return this.bookList.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        }
    },
    data(){
        return{
            pageSize:15,        //页面大小，一页15条数据
            currentPage:1,      //当前页默认第一页
        }
    },
    methods:{
        toBookAlbum(id){
            this.$store.commit('setActiveName','')
            this.$router.push({path:`/book-album/${id}`})
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/album-content.scss'

</style>