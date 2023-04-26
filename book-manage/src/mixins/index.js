export const mixin={
    methods:{
        //提示信息
        notify(title,type){
            this.$notify({
                title:title,
                type:type
            })
        },
        //根据相对地址获取绝对地址
        getUrl(url){
            return `${this.$store.state.HOST}${url}`
        },
        //获取性别，进行转换
        changeSex(value){
            if(value==0){
                return '女'
            }
            if(value==1){
                return '男'
            }
            if(value==2){
                return '不明'
            }
            return value
        },
        //生日转换去除时分秒
        attachBirth(val){
            return String(val).substring(0,10)
        },
        //上传图片前的校验
        beforeAvatorUpload(file){
            const isJPG=(file.type === 'image/jpeg')||(file.type === 'image/png')
            if(!isJPG){
                this.$message.error('上传图片只能是jpg或png格式')
                return false
            }
            const isLt2M=(file.size/1024/1024) < 2
            if(!isLt2M){
                this.$message.error('图片大小不能超过2M')
                return false
            }
            return true
        },
        //上传图片成功后的操作
        handleAvatorSuccess(res){
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
        //弹出删除窗口
        handleDelete(id){
            this.idx=id
            this.delVisible=true
        },
        //将已选择的项复制给multipleSelection
        handleSelectionChange(val){
            this.multipleSelection=val
        },
        //批量删除已选择的项
        delAll(){
            for(let item of this.multipleSelection){
                this.handleDelete(item.id)
                this.delRow()
            }
            this.multipleSelection=[]
        },
        //是否登录
        isLogin(){
            if(!this.loginIn){
                this.$router.push({path:'/'})
                this.notify('请登录后访问','error')
            }
        },
    }
}