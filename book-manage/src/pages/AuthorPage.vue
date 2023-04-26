<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入作者名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加作者</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="700px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="作者图片" width="110" align="center">
                <template slot-scope="scope">
                    <div class="singer-img">
                        <img :src="getUrl(scope.row.pic)" style="width:100%"/>
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatorUpload" 
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新图片</el-button>
                    </el-upload>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="作者" width="120" align="center"></el-table-column>
            <el-table-column label="性别" align="center" width="60">
                <template slot-scope="scope">
                    {{ changeSex(scope.row.sex) }}
                </template>
            </el-table-column>
            <el-table-column prop="birthday_time" label="生日" width="120" align="center">
                <template slot-scope="scope">
                    {{ attachBirth(scope.row.birthday_time) }}
                </template>
            </el-table-column>
            <el-table-column prop="location" label="地区" width="120" align="center"></el-table-column>
            <el-table-column label="简介" class="sidebar">
                <template slot-scope="scope">
                    <p class="testp" >
                        {{ scope.row.introduction }}
                    </p>
                </template>
            </el-table-column>
            <el-table-column label="作品管理" width="110" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="songEdit(scope.row.id,scope.row.name)">作品管理</el-button>
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
<!-- 添加作者弹窗 -->
        <el-dialog title="添加作者" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="name" label="作者名" size="mini">
                    <el-input v-model="registerForm.name" placeholder="作者名"></el-input>
                </el-form-item>
                <el-form-item label="性别" size="mini">
                    <el-radio-group v-model="registerForm.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                        <el-radio :label="2">不明</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="birthday_time" label="生日" size="mini">
                    <!-- 可以value-format="yyyy-MM-dd" -->
                    <el-date-picker type="date" editable="false" placeholder="选择日期" v-model="registerForm.birthday_time" style="width:100%"></el-date-picker>
                </el-form-item>
                <el-form-item prop="location" label="地区">
                    <el-select  v-model="registerForm.location" placeholder="地区" style="width: 100%;">
                        <el-option v-for="item in cities" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addAuthor">确定</el-button>
            </span>
        </el-dialog>
<!-- 编辑作者信息弹窗 -->
        <el-dialog title="编辑作者" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="from" label-width="80px">
                <el-form-item prop="name" label="作者名" size="mini">
                    <el-input v-model="form.name" placeholder="作者名"></el-input>
                </el-form-item>
                <el-form-item label="性别" size="mini">
                    <el-radio-group v-model="form.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                        <el-radio :label="2">不明</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="生日" size="mini">
                    <!-- 可以value-format="yyyy-MM-dd" -->
                    <el-date-picker type="date" editable="false" placeholder="选择日期" v-model="form.birthday_time" style="width:100%"></el-date-picker>
                </el-form-item>
                <el-form-item prop="location" label="地区">
                    <el-select  v-model="form.location" placeholder="地区" style="width: 100%;">
                        <el-option v-for="item in cities" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="form.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="删除作者" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复,是否确定删除</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="delRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {setAuthor,getAllAuthor,updateAuthor,delAuthor} from '../api/index'
import { mixin } from '../mixins/index'
import { mapGetters } from 'vuex'
import {cities} from '../assets/data/form'
export default{
    mixins:[mixin],
    data(){
        return{
            delVisible:false,   //显示删除弹窗
            centerDialogVisible:false,//添加弹窗
            registerForm:{  //添加框
                name:'',
                sex:'',
                birthday_time:'',
                location:'',
                introduction:''
            },
            editVisible:false,   //编辑弹窗是否显示
            form:{  //编辑框
                id:'',
                name:'',
                sex:'',
                birthday_time:'',
                location:'',
                introduction:''
            },
            tableData:[],
            tempData:[],
            select_word:'',
            pageSize:5, //分页大小
            currentPage:1,
            idx:-1,      //当前选择项
            multipleSelection:[],    //多选项数据
            cities:[]
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
        this.cities=cities
    },
    methods:{
        //转向书本管理页面
        songEdit(id,name){
            this.$router.push({path:'/Book',query:{id,name}})
        },
        //删除一个作者
        delRow(){
            delAuthor(this.idx)
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
            let d=new Date(this.form.birthday_time) 
            let datetime=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()
            let params=new URLSearchParams()
            params.append('id',this.form.id)
            params.append('name',this.form.name)
            params.append('sex',this.form.sex)
            params.append('birthday_time',datetime)
            params.append('location',this.form.location)
            params.append('introduction',this.form.introduction)
            updateAuthor(params)
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
        //弹出编辑框并编辑作者信息
        handleEdit(row){
            this.editVisible=true
            this.form={
                id:row.id,
                name:row.name,
                sex:row.sex,
                birthday_time:row.birthday_time,
                location:row.location,
                introduction:row.introduction
            }
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/author/updateAuthorPic?id=${id}`
        },
        //查询所有作者
        getData(){
            this.tableData=[]
            this.tempData=[]
            getAllAuthor().then(res=>{
                this.tableData=res
                this.tempData=res
                this.currentPage=1
            })
        },
        //添加作者
        addAuthor(){
            let d=this.registerForm.birthday_time
            let datetime=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()
            let params=new URLSearchParams()
            //params.append('id','')
            params.append('name',this.registerForm.name)
            params.append('sex',this.registerForm.sex)
            params.append('pic','/img/authorPic/hhh.jpg')
            params.append('birthday_time',datetime)
            params.append('location',this.registerForm.location)
            params.append('introduction',this.registerForm.introduction)
            setAuthor(params)
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
            this.registerForm.name=''
            this.registerForm.sex=''
            this.registerForm.birthday_time=''
            this.registerForm.location=''
            this.registerForm.introduction=''
        }
    }
}
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.singer-img{
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
