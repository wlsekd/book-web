<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="筛选关键字" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="goPublish">添加书单</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="700px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="书单图片" width="110" align="center">
                <template slot-scope="scope">
                    <div class="songList-img">
                        <img :src="getUrl(scope.row.pic)" style="width:100%"/>
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatorUpload" 
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新图片</el-button>
                    </el-upload>
                </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" width="120" align="center"></el-table-column>
            <el-table-column label="简介" class="sidebar">
                <template slot-scope="scope">
                    <p class="testp" >
                        {{ scope.row.introduction }}
                    </p>
                </template>
            </el-table-column>
            <el-table-column prop="style" label="风格" width="120" align="center"></el-table-column>
            <el-table-column label="书籍管理" width="110" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="listBookMan(scope.row.id)">书籍管理</el-button>
                </template>
            </el-table-column>
            <el-table-column label="详情" width="80" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="getContent(scope.row.id)">详情</el-button>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
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
<!-- 书籍管理页面 -->
        <el-dialog title="书单管理" :visible.sync="manVisible" width="700px" center>
            <div class="container">
                <div class="handle-box">
                    <el-button type="danger" size="mini" @click="delManAll">批量删除</el-button>
                    <el-button type="primary" size="mini" @click="getAllBook">添加书籍</el-button>
                </div>
            </div>
            <el-table size="mini" ref="multipleTable" border style="width:100%" height="400px" :data="manData" @selection-change="handleSelection">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="id" label="书籍id" align="center"></el-table-column>
                <el-table-column prop="name" label="作者-书名" align="center"></el-table-column>
                <el-table-column label="详情" width="80" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="getBookContent(scope.row.id)">详情</el-button>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="150" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" type="danger" @click="delListBook(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-bottom: 15px;">
                <el-pagination @current-change="handleManDataCurrentChange" background layout="total,prev,pager,next"
                    :current-page="currentManPage" :page-size="pageSize" :total="listBookDate.length"></el-pagination>
            </div>
        </el-dialog>
<!-- 管理书籍添加弹窗 -->
        <el-dialog title="书单管理" :visible.sync="addVisible" width="700px" center>
            <div class="container">
                <div class="handle-box">
                    <el-button type="danger" size="mini" @click="addManAll">批量添加</el-button>
                </div>
            </div>
            <el-table size="mini" ref="multipleTable" border style="width:100%" height="400px" :data="addData" @selection-change="handleAddSelection">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="id" label="书籍id" align="center"></el-table-column>
                <el-table-column prop="name" label="作者-书名" align="center"></el-table-column>
                <el-table-column label="详情" width="80" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="getBookContent(scope.row.id)">详情</el-button>
                    </template>
                </el-table-column>
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
<!-- 编辑书单弹窗 -->
        <el-dialog title="编辑书单" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px">
                <el-form-item prop="title" label="标题" size="mini">
                    <el-input v-model="form.title" placeholder="书单标题"></el-input>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="form.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
                <el-form-item prop="style" label="类型">
                    <el-select  v-model="form.style" placeholder="类型" style="width: 100%;">
                        <el-option v-for="item in styles" :key="item.name" :label="item.name" :value="item.name"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="删除歌单" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复,是否确定删除</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="delRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from '../../mixins'
import { mapGetters } from 'vuex'
import { styles } from '../../assets/data/style'
import { getBookListByUserId,updateBookList,getBookListById,delBookList,bookOfBookId,listBookDetail,delListBook,getAllBook,listBookAdd } from '../../api'
export default{
    name:'book-list-manage',
    mixins:[mixin],
    data(){
        return{
            manVisible:false,   //显示书单管理
            delVisible:false,   //显示删除弹窗
            registerForm:{  //添加框
                title:'',
                introduction:'',
                style:''
            },
            editVisible:false,   //编辑弹窗是否显示
            form:{  //编辑框
                id:'',
                title:'',
                introduction:'',
                style:''
            },
            tableData:[],
            tempData:[],
            select_word:'',
            pageSize:5, //分页大小
            currentPage:1,
            currentAddPage:1,
            currentManPage:1,
            idx:-1,      //当前选择项
            multipleSelection:[],    //多选项数据
            styles:[],
            listBookDate:[],        //书单书籍详情
            delSelection:[],    //书籍管理多选
            manageListId:'',    //当前管理的书籍id
            allBook:[],         //书籍管理添加书籍预览
            addSelection:[],    //书籍管理添加多选
            addVisible:false,   //添加书籍弹窗
        }
    },
    computed:{
        //计算当前搜索结果的数据
        data(){
            return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
        },
        addData(){
            return this.allBook.slice((this.currentAddPage-1)*this.pageSize,this.currentAddPage*this.pageSize)
        },
        manData(){
            return this.listBookDate.slice((this.currentManPage-1)*this.pageSize,this.currentManPage*this.pageSize)
        },
        ...mapGetters([
            'loginIn',
            'userId',
            'tempList'
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
                    if(item.title.includes(this.select_word)){
                        this.tableData.push(item)
                    }
                }
            }
        }
    },
    created(){
        this.getData()
        this.styles=styles
    },
    methods:{
        //删除一个歌单
        delRow(){
            delBookList(this.idx)
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
        //保存编辑
        editSave(){
            let params=new URLSearchParams()
            params.append('id',this.form.id)
            params.append('title',this.form.title)
            params.append('introduction',this.form.introduction)
            params.append('style',this.form.style)
            updateBookList(params)
            .then(res=>{
                if(res.code == 1){
                    this.getData()
                    this.notify('修改成功','success')
                }else{
                    this.notify('修改失败','error')
                }
            })
            .catch(err => {
                console.log(err)
            })
            this.editVisible=false
        },
        //弹出编辑框并编辑歌单信息
        handleEdit(row){
            this.editVisible=true
            this.form={
                id:row.id,
                title:row.title,
                introduction:row.introduction,
                style:row.style
            }
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
        handleManDataCurrentChange(val){
            this.currentManPage=val
        },
        handleAddDataCurrentChange(val){
            this.currentAddPage=val
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.config.HOST}/bookList/updateBookListPic?id=${id}`
        },
        //查询所有书单
        getData(){
            this.tableData=[]
            this.tempData=[]
            getBookListByUserId(this.userId).then(res=>{
                this.tableData=res
                this.tempData=res
                this.currentPage=1
            })
        },
        //转向该书单的评论列表,转向该页时需要添加templist数据
        getContent(id){
            getBookListById(id).then(res=>{
                let bookList=res
                this.$store.commit('setTempList',bookList)
            })
            this.$router.push({path:`book-list-album/${id}`})
        },
        //跳转发布
        goPublish(){
            this.$router.push({path:'/publish'})
        },
        listBookMan(id){
            this.manVisible=true
            this.listBookDate=[]
            listBookDetail(id).then(res=>{
                for(let item of res){
                    this.getBook(item.bookId)
                }
            })
            this.manageListId=id
        },
        /*****书单管理 */
        //查询书单书籍
        getBook(id){
            bookOfBookId(id)
            .then(res=>{
                this.listBookDate.push(res)
            })
            .catch(err=>{
                console.log(err)
            })
        },
        //转向该书详情
        getBookContent(id){
            this.$router.push({path:`/book-album/${id}`})
        },
        //删除书单中的书
        delListBook(id){
            delListBook(id,this.manageListId)
            .then(res=>{
                if(res){
                    this.listBookMan(this.manageListId)
                    this.notify('删除成功','success')
                }else{
                    this.notify('删除失败','error')
                }
            })
            .catch(err => {
                console.log(err)
            })
        },
        handleSelection(val){
            this.delSelection=val
        },
        delManAll(){
            for(let item of this.delSelection){
                this.delListBook(item.id)
            }
            this.delSelection=[]
            //this.$router.go(0)
        },
        /*****添加书籍管理 */
        getAllBook(){
            this.addVisible = true
            this.delVisible=false
            this.allBook=[]
            getAllBook()
            .then(res=>{
                this.allBook=res
            })
        },
        addManAll(){
            for(let item of this.addSelection){
                this.addListBook(item.id)
            }
            this.addSelection=[]
            this.$router.go(0)
        },
        handleAddSelection(val){
            this.addSelection=val
        },
        addListBook(id){
            let params=new URLSearchParams()
            params.append('bookId',id)
            params.append('bookListId',this.manageListId)
            listBookAdd(params)
            .then(res=>{
                if(res.code==1){
                    this.listBookMan(this.manageListId)
                    this.notify('提交成功','success')
                }else{
                    this.notify('提交失败','error')
                }
            })
            .catch(err=>{
                console.log(err)
            })
            
        },
    }
}
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.songList-img{
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
.testp{
    width: 100%;
overflow : hidden;
text-overflow: ellipsis;
display: -webkit-box;
-webkit-line-clamp: 3;
-webkit-box-orient: vertical;
}

</style>
