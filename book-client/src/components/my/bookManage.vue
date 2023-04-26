<template>
    <div class="table" style="margin-top: 35px;">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入作品名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="goPublish">添加作品</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="700px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="作品图片" width="110" align="center">
                <template slot-scope="scope">
                    <div class="song-img">
                        <img :src="getUrl(scope.row.pic)" style="width:100%"/>
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="作者-书名" width="120" align="center"></el-table-column>
            <el-table-column label="简介" class="sidebar">
                <template slot-scope="scope">
                    <p class="testp" >
                        {{ scope.row.introduction }}
                    </p>
                </template>
            </el-table-column>
            <el-table-column label="详情" width="80" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="getContent(scope.row.id)">详情</el-button>
                </template>
            </el-table-column>
            <el-table-column label="更新资源" align="center" width="100">
                <template slot-scope="scope">
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatorUpload" 
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新图片</el-button>
                    </el-upload>
                    <br>
                    <el-upload :action="uploadBookUrl(scope.row.id)" :before-upload="beforeBookUpload"
                        :on-success="handleBookSuccess">
                        <el-button size="mini">更新资源</el-button>
                    </el-upload>
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
<!-- 编辑作品弹窗 -->
        <el-dialog title="编辑作品" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="from" label-width="80px">
                <el-form-item prop="name" label="作者-作品" size="mini">
                    <el-input v-model="form.name" placeholder="作者-作品"></el-input>
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
        <el-dialog title="删除作品" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复,是否确定删除</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="delRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {bookOfUserId,updateBook,delBook} from '../../api/index'
import { mixin } from '../../mixins'
import  '@/assets/js/iconfont.js'
import { mapGetters } from 'vuex'
export default{
    mixins:[mixin],
    data(){
        return{
            authorId:'',//其他页面传过来的作者id
            authorName:'',//其他页面传的作者名
            delVisible:false,   //显示删除弹窗
            registerForm:{  //添加框
                name:'',
                authorName:'',
                introduction:'',
            },
            editVisible:false,   //编辑弹窗是否显示
            form:{  //编辑框
                id:'',
                name:'',
                introduction:'',
            },
            tableData:[],
            tempData:[],
            select_word:'',
            pageSize:5, //分页大小
            currentPage:1,
            idx:-1,      //当前选择项
            multipleSelection:[],   //多选项数据
            toggle:false,       //播放器图标状态
        }
    },
    computed:{
        ...mapGetters([
            'loginIn',
            'userId'
        ]),
        //计算当前搜索结果的数据
        data(){
            return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
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
    created(){
        this.authorId=this.$route.query.id
        this.authorName=this.$route.query.name
        this.getData()
    },
    destroyed(){
    },
    methods:{
        //转向该书详情
        getContent(id){
            this.$router.push({path:`/book-album/${id}`})
        },
        //更新资源Url
        uploadBookUrl(id){
            return `${this.$store.state.config.HOST}/book/updateBookUrl?id=${id}`
        },
        //上传资源前的校验
        beforeBookUpload(file){
            var testMsg=file.name.substring(file.name.lastIndexOf('.')+1)
            if(testMsg!='pdf'&&testMsg!='txt'){
                this.$message({
                    message:'上传文件只能是txt或pdf格式',
                    type:'error'
                })
                return false
            }
            return true
        },
        //上传资源成功后的操作
        handleBookSuccess(res){
            let _this=this
            if(res.code== 1){
                _this.getData()
                _this.$notify({
                    title:'上传成功',
                    type:'success'
                })
            }else{
                _this.$notify({
                    title:'上传失败',
                    type:'error'
                })
            }
        },
        //删除作品
        delRow(){
            delBook(this.idx)
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
            params.append('name',this.form.name)
            params.append('introduction',this.form.introduction)
            updateBook(params)
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
        //弹出编辑框并编辑作品信息
        handleEdit(row){
            this.editVisible=true
            this.form={
                id:row.id,
                name:row.name,
                introduction:row.introduction,
                lyric:row.lyric
            }
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.config.HOST}/book/updateBookPic?id=${id}`
        },
        //查询所有作品
        getData(){
            this.tableData=[]
            this.tempData=[]
            bookOfUserId(this.userId).then(res=>{
                this.tableData=res
                this.tempData=res
                this.currentPage=1
            })
        },
        //跳转发布
        goPublish(){
            this.$router.push({path:'/publish'})
        }
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
.text-display{
    text-overflow: ellipsis;
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
