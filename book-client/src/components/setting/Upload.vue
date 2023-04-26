<template>
    <div class="upload">
        <p class="title">修改头像</p>
        <hr/>
        <div class="section">
            <el-upload drag :action="uploadUrl()" :show-file-list="false" :on-success="handleAvatorSuccess"
                :before-upload="beforeAvatorUpload">
                <i class="el-icon-upload"></i>
                <div>
                    将文件拖到此处，或<span style="color:aquamarine">修改头像</span>
                </div>
                <div slot="tip" style="text-align: center;">只能上传jpg/png文件，且不能超过10MB</div>
            </el-upload>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex'
import {mixin} from '../../mixins'
export default{
    name:'upload',
    mixins:[mixin],
    computed:{
        ...mapGetters([
            'userId',
        ])
    },
    methods:{
        //上传地址
        uploadUrl(){
            return `${this.$store.state.config.HOST}/user/updateUserPic?id=${this.userId}`
        },
        //上传成功
        handleAvatorSuccess(res,file){
            if(res.code==1){
                this.$store.commit('setAvator',res.avator)
                this.notify('头像修改成功','success')
            }else{
                this.notify('头像修改失败','error')
            }
        },
        //上传之前的校验
        beforeAvatorUpload(file){
            const isJPG=file.type=='image/jpeg'
            const isLt10M=(file.size/1024/1024)<10
            if(!isJPG){
                this.notify('上传头像图片格式只能是jpg','error')
            }
            if(!isLt10M){
                this.notify('上传的头像不能大于10M','error')
                return false
            }
            return true
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../../assets/css/upload.scss'
</style>