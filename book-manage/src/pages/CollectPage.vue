<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets"></i>收藏信息
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="筛选收藏的关键词" class="handle-input"></el-input>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="name" label="作者-书名" align="center"></el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                background
                layout="total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>

        <el-dialog title="删除收藏" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复,是否确定删除</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="delRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { bookOfBookId,delCollection,collectOfUserId } from '../api/index'
import { mixin } from '../mixins/index'
import { mapGetters } from "vuex"
export default{
    mixins:[mixin],
    props:['id'],
    data(){
        return{
            delVisible:false,   //显示删除弹窗
            tableData:[],
            tempData:[],
            select_word:'',
            idx:-1,      //当前选择项
            multipleSelection:[],   //多选项数据
            pageSize:15,
            currentPage:1,
        }
    },
    computed:{
        //计算当前搜索结果的数据
        data(){
            return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        },
        ...mapGetters([
            'loginIn'
        ])
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
    created(){
        this.getData()
        this.isLogin()
    },
    methods:{
        //删除一个收藏
        delRow(){
            let params=new URLSearchParams()
            params.append('userId',this.$route.query.id)
            params.append('bookId',this.idx)
            delCollection(params)
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
        //查询该用户所有收藏的歌曲
        getData(){
            this.tableData=[]
            this.tempData=[]
            collectOfUserId(this.$route.query.id).then(res=>{
                for(let item of res){
                    this.getBook(item.bookId)
                }
            })
        },
        //根据用户id查询收藏的对象，放到tableData和tempData里
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
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
    }
}
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}

.handle-input{
    width: 300px;
    display: inline-block;
}
.pagination{
    display: flex;
    justify-content: center;
}
</style>
