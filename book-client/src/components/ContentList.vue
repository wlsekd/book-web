<template>
    <div class="content-list">
        <ul class="section-content">
            <li class="content-item" v-for="(item,index) in contentList" :key="index">
                <div class="kuo" @click="goAlbum(item,item.name)">
                    <img class="item-img" :src="attachImageUrl(item.pic)"/>
                </div>
                <p class="item-name">{{ item.name||item.title }}</p>
            </li>
        </ul>
    </div>
</template>
<script>
import {mixin} from '../mixins'
export default ({
    name:'content-list',
    props:['contentList'],
    mixins:[mixin],
    methods:{
        goAlbum(item,type){
            //点击后将书单或作者对象传入store
            this.$store.commit('setTempList',item)
            if(type){       //goalbum通过传入对象name判断传入的是书单还是作者，书单使用的是title不是name
                this.$router.push({path:`author-album/${item.id}`})
            }else{          //转向书单
                this.$router.push({path:`book-list-album/${item.id}`})
            }
        }
    }
})
</script>

<style lang="scss" scoped>
@import '../assets/css/content-list.scss'
</style>
