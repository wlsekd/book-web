<template>
    <div class="search-songs">
        <album-content :bookList="listOfBooks"></album-content>
    </div>
</template>

<script>
import {mapGetters} from "vuex"
import {mixin} from "../../mixins"
import AlbumContent from '../AlbumContent.vue'
export default{
    name:'search-books',
    mixins:[mixin],
    components: { 
        AlbumContent
    },
    computed:{
        ...mapGetters([
            'listOfBooks'
        ])
    },
    mounted(){
        this.getSearch()
    },
    
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/search-songs.scss'
</style>