<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="筛选关键字" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加书单</el-button>
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
                    <el-button size="mini" @click="bookEdit(scope.row.id)">书籍管理</el-button>
                </template>
            </el-table-column>
            <el-table-column label="评论" width="80" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="getComment(scope.row.id)">评论</el-button>
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
<!-- 添加书单弹窗 -->
        <el-dialog title="添加书单" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="title" label="标题" size="mini">
                    <el-input v-model="registerForm.title" placeholder="书单标题"></el-input>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
                <el-form-item prop="style" label="类型">
                    <el-select  v-model="registerForm.style" placeholder="类型" style="width: 100%;">
                        <el-option v-for="item in styles" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addBookList">确定</el-button>
            </span>
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
                        <el-option v-for="item in styles" :key="item.value" :label="item.label" :value="item.value"></el-option>
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
import {setBookList,getAllBookList,updateBookList,delBookList} from '../api/index'
import { mixin } from '../mixins/index'
import { mapGetters } from 'vuex'
import { styles } from '../assets/data/form'
export default{
    mixins:[mixin],
    data(){
        return{
            delVisible:false,   //显示删除弹窗
            centerDialogVisible:false,//添加弹窗
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
            idx:-1,      //当前选择项
            multipleSelection:[],    //多选项数据
            styles:[]
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
                    if(item.title.includes(this.select_word)){
                        this.tableData.push(item)
                    }
                }
            }
        }
    },
    created(){
        this.getData()
        this.isLogin()
        this.styles=styles
    },
    methods:{
        //转向书籍管理页面
        bookEdit(id){
            this.$router.push({path:'/listBook',query:{id}})
        },
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
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/bookList/updateBookListPic?id=${id}`
        },
        //查询所有书单
        getData(){
            this.tableData=[]
            this.tempData=[]
            getAllBookList().then(res=>{
                this.tableData=res
                this.tempData=res
                this.currentPage=1
            })
        },
        //添加书单
        addBookList(){
            let params=new URLSearchParams()
            //params.append('id','')
            params.append('title',this.registerForm.title)
            params.append('pic','/img/bookListPic/hhh.jpg')
            params.append('introduction',this.registerForm.introduction)
            params.append('style',this.registerForm.style)
            setBookList(params)
            .then(res=>{
                if(res.code == 1){
                    this.getData()
                    this.notify('添加成功','success')
                }else{
                    this.notify('添加失败','error')
                }
            })
            .catch(err => {
                console.log(err)
            })
            this.centerDialogVisible=false
            this.registerForm.title=''
            this.registerForm.style=''
            this.registerForm.introduction=''
        },
        //转向该书单的评论列表
        getComment(id){
            this.$router.push({path:'/BookListComment',query:{id}})
        }
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
