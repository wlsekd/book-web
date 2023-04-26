import { addCollect, bookLikeName,collectOfUserId,existCollect,delCollect } from "../api/index"
import {mapGetters} from 'vuex';
export const mixin={
    computed:{
        ...mapGetters([
            'loginIn',
            'userId',
            'isCollect',
            'id',
            'HOST'
        ])
    },
    methods:{
        //提示信息
        notify(title,type) {
            this.$notify({
                title: title,
                type: type
            })
        },
        //获取图片地址
        attachImageUrl(srcUrl){
            return srcUrl?this.$store.state.config.HOST+srcUrl:this.$store.state.config.HOST+'/img/authorPic/hhh.jpg'
        },
        //根据搜索内容模糊查询
        getSearch(){
            if(!this.$route.query.keywords){
                this.$store.commit('setListOfBooks',[])
                this.notify('您输入的内容为空','warning')
            }else{
                bookLikeName(this.$route.query.keywords).then(res=>{
                    if(!res.length){
                        this.$store.commit('setListOfBooks',[])
                        this.notify('系统暂无符合条件的书本','warning')
                    }else{
                        this.$store.commit('setListOfBooks',res)
                    }
                }).catch(err=>{
                    console.log(err)
                })
            }
        },
        //获取名字前半部分作者名
        replaceFName(str){
            let arr=str.split('-')
            return arr[0]
        },
        //获取名字后半部分书名
        replaceLName(str){
            let arr=str.split('-')
            return arr[1]
        },
        //播放
        toplay:function(id,url,pic,index,name,lyric){
            this.$store.commit('setId',id)
            this.$store.commit('setUrl',this.$store.state.config.HOST+url)
            this.$store.commit('setPicUrl',this.$store.state.config.HOST+pic)
            this.$store.commit('setListIndex',index)
            this.$store.commit('setTitle',this.replaceLName(name))
            this.$store.commit('setArtist',this.replaceFName(name))
            this.$store.commit('setLyric',this.parseLyric(lyric))
            this.$store.commit('setIsCollect',false)
            if(this.loginIn){
                collectOfUserId(this.userId)
                .then(res=>{
                    for(let item of res){
                        if(item.songId==id){
                            this.$store.commit('setIsCollect',true)
                            break
                        }
                    }
                })
            }
        },
        //解析歌词
        parseLyric(text){
            let lines=text.split("\n")      //将歌词按行分解为数组
            let pattern =/\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g   //时间格式的正则
            let result=[]       //返回值
            //对于歌词格式不对的直接返回
            if(!(/\[.+\]/.test(text))){
                return [[0,text]]
            }
            //去掉前面格式不正确的行为
            while(!pattern.test(lines[0])){
                lines=lines.slice(1)
            }
            //遍历每一行,形成一个带着两个元素的数组，第一个元素是时间，第二个元素是歌词
            for(let item of lines){
                //存前面的时间段
                let time=item.match(pattern)
                //存后面的歌词
                let value=item.replace(pattern,'')//遇到[00:00:00]等格式替换为空
                for(let item1 of time){
                    let t=item1.slice(1,-1).split(":")  //取时间从第一个到倒数第一个,分为分和秒+毫秒
                    if(value!=''){
                        result.push([parseInt(t[0],10)*60+parseFloat(t[1]),value])          //按十进制转换
                    }
                }
            }
            //按照第一个元素--时间--排序
            result.sort(function(a,b){
                return a[0]-b[0]
            })
            return result
        },
        //获取生日
        attachBirth(val){
            return val.substr(0,10);
        },
        //用户收藏
        collect(userId,id,type){
            if(this.loginIn){
                var params=new URLSearchParams()
                if(type==0){//用户收藏书
                    params.append('bookId',id)
                    params.append('type',0)
                }else if(type==1){//收藏书单
                    params.append('bookListId',id)
                    params.append('type',1)
                }else{
                    params.append('authorId',id)
                    params.append('type',2)
                }
                params.append('userId',userId)
                addCollect(params)
                .then(res=>{
                    if(res.code==1){
                        this.notify('收藏成功','success')
                        this.haveCollect(userId,id,type)
                    }else if(res.code==2){
                        this.notify('已收藏','warning')
                    }else{
                        this.notify('收藏失败','error')
                    }
                })
            }else{
                this.notify('请先登录','warning')
            }
        },
        //是否收藏
        haveCollect(userId,id,type){
            if(this.loginIn){
                var params=new URLSearchParams()
                if(type==0){//用户收藏书
                    params.append('bookId',id)
                    params.append('type',0)
                }else if(type==1){//收藏书单
                    params.append('bookListId',id)
                    params.append('type',1)
                }else{
                    params.append('authorId',id)
                    params.append('type',2)
                }
                params.append('userId',userId)
                existCollect(params)
                .then(res=>{
                    if(res.code==1){
                        this.$store.commit('setIsCollect',true)
                    }else{
                        this.$store.commit('setIsCollect',false)
                    }
                })
            }
        },
        //删除收藏
        delCollect(userId,id,type){
            if(this.loginIn){
                var params=new URLSearchParams()
                if(type==0){//用户收藏书
                    params.append('bookId',id)
                    params.append('type',0)
                }else if(type==1){//收藏书单
                    params.append('bookListId',id)
                    params.append('type',1)
                }else{
                    params.append('authorId',id)
                    params.append('type',2)
                }
                params.append('userId',userId)
                delCollect(params)
                .then(res=>{
                    if(res.code==1){
                        this.notify('取消收藏成功','success')
                        this.haveCollect(userId,id,type)
                    }else{
                        this.notify('取消收藏失败','error')
                    }
                })
            }
        },
        //批量删除已选择的项
        delAll(){
            for(let item of this.multipleSelection){
                this.handleDelete(item.id)
                this.delRow()
            }
            this.multipleSelection=[]
        },
        //将已选择的项复制给multipleSelection
        handleSelectionChange(val){
            this.multipleSelection=val
        },
        //根据相对地址获取绝对地址
        getUrl(url){
            return `${this.$store.state.config.HOST}${url}`
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
    },
    
}