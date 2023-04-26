<template>
  <div class="home">
    <!-- 轮播图 -->
    <swiper />
    <div class="section" v-for="(item,index) in contentList" :key="index">
      <div class="section-title">{{ item.name }}</div>
      <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>

<script>
import Swiper from '../components/swiper.vue'
import contentList from '../components/ContentList.vue'
import {getAllAuthor,getAllBookList} from '../api/index'
export default {
  name: 'home',
  components: {
    Swiper,
    contentList
  },
  data () {
    return {
      //首页数据待传入contentlist组件
      contentList:[
        {name:'书单',list:[]},
        {name:'作者',list:[]}
      ]
    }
  },
  created () {
   this.getBookList()
   this.getAuthor()
  },
  methods: {
    getBookList(){    //获取十个书单
      getAllBookList().then(res=>{
        this.contentList[0].list=res.slice(0,10)
      }).catch(err=>{
        console.log(err)
      })
    },
    getAuthor(){    //获取十位作者
      getAllAuthor().then(res=>{
        this.contentList[1].list=res.slice(0,10)
      }).catch(err=>{
        console.log(err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
</style>
