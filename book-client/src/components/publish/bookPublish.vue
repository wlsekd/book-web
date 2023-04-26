<template>
    <div>
        <div class="info">
            <div class="title">
                <span>上传书籍资源</span>
            </div>
            <hr/>
            <div class="personal">
                <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf" style="margin-left: 15px;">
                <div style="margin-bottom: 20px;margin-top: -10px;">
                    <label>作品名</label>
                    <el-input style="margin-top: 5px;" type="text" v-model="registerForm.name" name="name" placeholder="作品名"></el-input>
                </div>
                <div style="margin-bottom: 20px;">
                    <label>作者</label>
                    <el-input style="margin-top: 5px;" type="text" v-model="registerForm.author" name="author" placeholder="作者"></el-input>
                </div>
                <div style="margin-bottom: 20px;">
                    <label>简介</label>
                    <el-input style="margin-top: 5px;" type="textarea" v-model="registerForm.introduction" name="introduction" placeholder="简介"></el-input>
                </div>
                <div style="margin-bottom: 20px;">
                    <label>作品上传</label>
                    <input style="margin-top: 5px;" type="file" name="file" accept=".txt,.pdf">
                </div>
            </el-form>
                <div class="btn">
                    <div @click="addBook">添加</div>
                    <div @click="goback">取消</div>
                    
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>
import {mixin} from '../../mixins'
import { mapGetters } from 'vuex'
export default {
    name:'book-publish',
    mixins:[mixin],
    data(){
        return{
            registerForm:{
                name:'',
                introduction:'',
                author:'',
            },
        }
    },
    computed:{
        ...mapGetters([
            'userId'
        ])
    },
    methods:{
        addBook(){
            let _this = this
            var form = new FormData(document.getElementById('tf'))
            form.append('userId',this.userId)
            form.set('name',form.get('name')+'-'+form.get('author'))
            var req = new XMLHttpRequest()
            req.onreadystatechange = function(){
                //req.readyState == 4 获取到返回的完整数据
                //req.status == 200 和后台正常交互完成
                if(req.readyState == 4 && req.status == 200){
                    let res = JSON.parse(req.response)
                    if(res.code){
                        this.registerForm = {}
                        _this.notify(res.msg,'success')
                    }else{
                        _this.notify('保存失败','error')
                    }
                }
            }
            req.open('post',`http://127.0.0.1:8888/book/add`,false)
            req.send(form)
            location.reload()
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