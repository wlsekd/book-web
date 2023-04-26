<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets"></i>书单书籍管理
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入书名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="getAllBook">添加书籍</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="id" width="80px" label="书籍id" align="center"></el-table-column>
            <el-table-column prop="name" label="作者-书名" align="center"></el-table-column>
            <el-table-column label="评论" width="80" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="getComment(scope.row.id)">评论</el-button>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination" style="margin-bottom: 15px;">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentPage" :page-size="pageSize" :total="tableData.length"></el-pagination>
            </div>
<!-- 添加书籍弹窗 -->
        <el-dialog title="书单管理" :visible.sync="centerDialogVisible" width="700px" center>
            <div class="container">
                <div class="handle-box">
                    <el-button type="danger" size="mini" @click="addManAll">批量添加</el-button>
                </div>
            </div>
            <el-table size="mini" ref="multipleTable" border style="width:100%" height="400px" :data="addData" @selection-change="handleAddSelection">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="id" label="书籍id" align="center"></el-table-column>
                <el-table-column prop="name" label="作者-书名" align="center"></el-table-column>
                <el-table-column label="操作" width="150" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" type="danger" @click="addListBook(scope.row.id)">添加</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-bottom: 15px;">
                <el-pagination @current-change="handleAddDataCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentAddPage" :page-size="pageSize" :total="allBook.length"></el-pagination>
            </div>
        </el-dialog>
        <el-dialog title="删除书籍" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复,是否确定删除</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="delRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { listBookAdd, bookOfBookName,listBookDetail,bookOfBookId,delListBook,getAllBook } from '../api/index'
import { mixin } from '../mixins/index'
export default{
    mixins:[mixin],
    data(){
        return{
            delVisible:false,   //显示删除弹窗
            centerDialogVisible:false,//添加弹窗
            registerForm:{  //添加框
                authorName:'',
                bookName:''
            },
            tableData:[],
            tempData:[],
            select_word:'',
            idx:-1,      //当前选择项
            multipleSelection:[],   //多选项数据
            bookListId:'',
            allBook:[],
            addSelection:[],
            pageSize:15,
            currentAddPage:1,
            currentPage:1,
        }
    },
    watch:{
        //搜索框内容匹配
        select_word:function(){
            if(this.select_word==''){
                this.tableData=this.tempData
            }else{
                this.tableData=[]
                for(let item of this.tempData){
                    if(item.name.includes(this.select_word)){
                        this.tableData.push(item)
                    }
                }
            }
        }
    },
    computed:{
        ...mapGetters([
            'loginIn'
        ]),
        addData(){
            return this.allBook.slice((this.currentAddPage-1)*this.pageSize,this.currentAddPage*this.pageSize)
        },
        data(){
            return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        },
    },
    created(){
        this.bookListId=this.$route.query.id
        this.getData()
        this.isLogin()
    },
    methods:{
        //转向该书的评论列表
        getComment(id){
            this.$router.push({path:'/BookComment',query:{id}})
        },
        //删除书单中的书
        delRow(){
            delListBook(this.idx,this.bookListId)
            .then(res=>{
                if(res){
                    this.getData()
                    this.notify('删除成功','success')
                }else{
                    this.notify('删除失败','error')
                }
            })
            .catch(err => {
                console.log(err)
            })
            this.delVisible=false
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/book/updateBookPic?id=${id}`
        },
        //查询所有书
        getData(){
            this.tableData=[]
            this.tempData=[]
            listBookDetail(this.bookListId).then(res=>{
                for(let item of res){
                    this.getBook(item.bookId)
                }
            })
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
        //根据书id查询对象，放到tableData和tempData里
        getBook(id){
            bookOfBookId(id)
            .then(res=>{
                this.tempData.push(res)
                this.tableData.push(res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        getAllBook(){
            this.centerDialogVisible=true
            this.allBook=[]
            getAllBook()
            .then(res=>{
                this.allBook=res
            })
        },
        handleAddSelection(val){
            this.addSelection=val
        },
        addListBook(id){
            let params=new URLSearchParams()
            params.append('bookId',id)
            params.append('bookListId',this.bookListId)
            listBookAdd(params)
            .then(res=>{
                if(res.code==1){
                    this.getData()
                    this.notify('提交成功','success')
                }else{
                    this.notify('提交失败','error')
                }
            })
            .catch(err=>{
                console.log(err)
            })
            
        },
        addManAll(){
            for(let item of this.addSelection){
                this.addListBook(item.id)
            }
            this.addSelection=[]
            this.$router.go(0)
        },
        handleAddDataCurrentChange(val){
            this.currentAddPage=val
        },
    }
}
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.song-img{
    width: 100%;
    height: 80px;
    border-radius: 5px;
    margin-bottom: 5px;
    overflow: hidden;
}
.handle-input{
    width: 300px;
    display: inline-block;
}
.pagination{
    display: flex;
    justify-content: center;
}
.play{
    position: absolute;
    z-index: 100;
    width: 80px;
    height:80px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    top: 18px;
    left:15px;
}
.icon{
    width: 2em;
    height: 2em;
    color: white;
    fill: currentColor;
    overflow: hidden;
}
</style>
