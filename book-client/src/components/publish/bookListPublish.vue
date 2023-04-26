<template>
    <div>
        <div class="info">
            <div class="title">
                <span>上传书籍资源</span>
            </div>
            <hr/>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" style="margin-top: 30px;margin-right: 50px;">
                <el-form-item prop="title" label="标题" size="mini">
                    <el-input v-model="registerForm.title" placeholder="书单标题"></el-input>
                </el-form-item>
                <el-form-item prop="style" label="类型">
                    <el-select  v-model="registerForm.style" placeholder="类型" style="width: 100%;">
                        <el-option v-for="item in styles" :key="item.name" :label="item.name" :value="item.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <div class="btn">
                    <div @click="addBookList">添加</div>
                    <div @click="goback">取消</div>
                </div>
            </span>
        </div>
    </div>

</template>

<script>
import { mapGetters } from 'vuex'
import {styles} from '../../assets/data/style'
import {setBookList} from '../../api/index'
import {mixin} from '../../mixins'
export default{
    name:'book-list-publish',
    computed:{
        ...mapGetters([
            'userId'
        ])
    },
    mixins:[mixin],
    data(){
        return{
            registerForm:{
                title:'',
                introduction:'',
                style:'',
            },
            styles:[],
        }
    },
    created(){
        this.styles=styles
    },
    methods:{
        addBookList(){
            let _this=this
            let params=new URLSearchParams()
            params.append('title',this.registerForm.title)
            params.append('pic','/img/bookListPic/hhh.jpg')
            params.append('introduction',this.registerForm.introduction)
            params.append('style',this.registerForm.style)
            params.append('userId',this.userId)
            setBookList(params)
            .then(res=>{
                if(res.code == 1){
                    _this.notify('添加成功','success')
                }else{
                    _this.notify('添加失败','error')
                }
            })
            .catch(err => {
                console.log(err)
            })
            this.registerForm.title=''
            this.registerForm.style=''
            this.registerForm.introduction=''
        },
        goback(){
            this.$router.go(0)
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/info.scss'
</style>