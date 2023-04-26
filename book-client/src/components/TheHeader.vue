<template>
    <div class="the-header">
        <div class="header-logo" @click="goHome">
            <i class="el-icon-s-management"></i>
            <span>BOOK</span>
        </div>
        <ul class="navbar">
            <li :class="{active:item.name==activeName}" v-for="item in navMsg" :key="item.path" @click="goPage(item.path,item.name)">
                {{ item.name }}
            </li>
            <li>
              <div class="header-search">
                <input type="text" placeholder="输入搜索" @keyup.enter="goSearch()" v-model="keywords">
                <div class="search-btn" @click="goSearch()">
                  <svg class="icon">
                    <use xlink:href="#icon-sousuo"></use>
                  </svg>
                </div>
              </div>
            </li>
            <li v-show="!loginIn" :class="{active:item.name==activeName}" v-for="item in loginMsg" :key="item.path" @click="goPage(item.path,item.name)">
                {{ item.name }}
            </li>
        </ul>
        <div class="header-right" v-show="loginIn">
            <div id="user" >
              <img :src="attachImageUrl(avator)"/>
            </div>
            <ul class="menu">
              <li v-for="(item,index) in menuList" :key="index" @click.stop="goMenuList(item.path)">{{ item.name }}</li>
            </ul>
        </div>
    </div>
</template>

<script>
import { mixin } from '../mixins';
import {mapGetters} from 'vuex'
import {navMsg,loginMag,menuList} from '../assets/data/header';
export default {
  name: 'the-header',
  mixins:[mixin],
  data(){
    return{
        navMsg:[],       //左侧导航栏
        keywords:'',     //关键字搜索
        loginMsg:[],     //登录导航栏
        menuList:[],     //用户下拉菜单
        isShow:false
    }
  },
  created(){
    this.navMsg=navMsg
    this.loginMsg=loginMag
    this.menuList=menuList
  },
  mounted(){
    //下拉菜单显示，不要改动
    document.querySelector('#user').addEventListener('click',function(e){
          document.querySelector('.menu').classList.add("show");
          e.stopPropagation()           //关键在于阻止冒泡
      },false);
      document.querySelector('.menu').addEventListener('click',function(e){
          e.stopPropagation()           //点击菜单内部时，阻止时间冒泡，这样，点击内部时，菜单不会关闭
      },false);
      document.addEventListener('click',function(){
        document.querySelector('.menu').classList.remove('show');
      },false);
  },
  computed:{
    ...mapGetters([
        'activeName',
        'loginIn',
        'avator',
    ])
  },
  methods:{
      goHome(){
          this.$router.push({path:'/'})
          this.$store.commit('setActiveName','首页')
      },
      goPage(path,name){
        if(!this.loginIn&&path=='/my'){
          this.notify('请登录后访问','warning')
          this.$router.push({path:'/login-in'})
          this.$store.commit('setActiveName','登录')
        }else{
          this.$router.push({path:path}),
          this.$store.commit('setActiveName',name)
        }
        
      },
      goSearch(){
        this.$store.commit('setActiveName','')
        this.$router.push({path:'/search',query:{'keywords':this.keywords}})
      },
      goMenuList(path){
        if(path==0){
          this.$store.commit('setLoginIn',false)
          this.$router.push({path:'/'})
          this.$store.commit('setActiveName','首页')
          this.$store.commit('setIsCollect',false)
          this.$store.commit('setUserId','');//退出后清空vuex保存的登录数据
          this.$store.commit('setUsername','');//退出后清空vuex保存的登录数据
          this.$store.commit('setAvator',''); //退出后清空vuex保存的登录数据
        }else(
          this.$router.push({path:path})
        )
      },
  }
}
</script>

<style  lang="scss" scoped>
@import '../assets/css/the-header.scss';
</style>
