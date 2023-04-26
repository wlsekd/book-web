<template>
    <div style="margin-top: 50px;min-height: 500px;">
        <!-- <content-list :contentList="listOfBooks"></content-list> -->
        <album-content :bookList="listOfBooks"></album-content>
    </div>
</template>

<script>
// import ContentList from '../ContentList.vue'
import { mapGetters } from 'vuex'
import { collectOfUserId,bookOfBookId } from '../../api'
import AlbumContent from '../AlbumContent.vue'
export default{
    name:'bookCollect',
    components:{
        //ContentList,
        AlbumContent,
    },
    data(){
        return{
            listOfBooks:[],
        }
    },
    computed:{
        ...mapGetters([
            'userId',
        ]),
    },
    mounted(){
        this.getCollectBooks()
    },
    methods:{
        getCollectBooks(){
            this.listOfBooks=[]
            collectOfUserId(this.userId)
            .then(res=>{
                for(let item of res){
                    if(item.bookId!=''&&item.bookId!=null){
                        //alert(item.authorId)
                        this.getBook(item.bookId)
                    }
                }
            })
            .catch(err=>{
                console.log(err)
            })
        },
        getBook(id){
            bookOfBookId(id)
            .then(res=>{
                this.listOfBooks.push(res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
    }
}
</script>