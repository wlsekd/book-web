<template>
    <div class="search">
        <nav class="searchList-nav" ref="change">
            <span :class="{isActive:toggle=='Authors'}" @click="handleChangeView('Authors')">作者</span>
            <span :class="{isActive:toggle=='Books'}" @click="handleChangeView('Books')">书籍</span>
            <span :class="{isActive:toggle=='BookLists'}" @click="handleChangeView('BookLists')">书单</span>
        </nav>
        <component :is="currentView" ></component>
    </div>
</template>

<script>
import SearchBooks from '../components/search/SearchBooks.vue'
import SearchBookLists from '../components/search/SearchBookLists.vue'
import SearchAuthors from '../components/search/SearchAuthors.vue'
export default {
    name:'search',
    components:{
        SearchBooks,
        SearchBookLists,
        SearchAuthors
    },
    data(){
        return{
            toggle:'Books',
            currentView:'SearchBooks'
        }
    },
    watch:{
        $route(to,from){
            this.$router.go(0)
        }
    },
    methods:{
        //组件切换
        handleChangeView(component){
            this.currentView='Search'+component
            this.toggle=component
        }
    },
    
}
</script>

<style lang="scss" scoped>
@import '../assets/css/search.scss'
</style>