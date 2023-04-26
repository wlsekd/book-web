<template>
    <div>
        <login-logo/>
        <div class="signUp">
            <div class="signUp-head">
                <span>用户登录</span>
            </div>
            <el-form :model="registerFrom" ref="registerFrom" label-width="70px" class="demo-ruleFrom" :rules="rules">
                <el-form-item prop="username" label="用户名">
                    <el-input v-model="registerFrom.username" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input type="password" v-model="registerFrom.password" placeholder="密码"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button @click="goSignUp">注册</el-button>
                    <el-button type="primary" @click="handleLoginIn">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
    
</template>

<script>
import LoginLogo from '../components/LoginLogo'
import {mixin} from '../mixins'
import {loginIn} from '../api/index'
export default {
    components: { LoginLogo },
    name:'login-in',
    mixins:[mixin],
    data(){
        return{
            registerFrom:{
                username:'',    //用户名
                password:'',    //密码
            },
            rules:{
                username: [
                    { required: true, trigger: 'blur',message:'请输入用户名' }
                ],
                password: [
                    { required: true, trigger: 'blur',message:'请输入密码' }
                ],
            }
        }
    },
    mounted(){
        this.changeIndex('登录')
    },
    methods:{
        //登录按钮
        handleLoginIn(){
            let _this=this
            let params =new URLSearchParams()
            params.append('username',this.registerFrom.username)
            params.append('password',this.registerFrom.password)
            loginIn(params)      //api中的方法
            .then(res=>{
                if(res.code==1){
                    _this.notify('登录成功','success');
                    _this.$store.commit('setLoginIn',true);
                    _this.$store.commit('setUserId',res.userMsg.id);
                    _this.$store.commit('setUsername',res.userMsg.username);
                    _this.$store.commit('setAvator',res.userMsg.avator);                                                
                    setTimeout(function(){
                        _this.changeIndex('首页');
                        _this.$router.push({path: '/'});
                    },2000);
                }else{
                    _this.notify('登录失败','error')
                }
            })
            .catch(err=>{
                _this.notify('用户名和密码错误','error');
            })
        },
        //跳转到首页
        goSignUp(){
            this.changeIndex('注册')
            this.$router.push({path:'/sign-up'})
        },
        //转向首页后高亮
        changeIndex(value){
            this.$store.commit('setActiveName',value);
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/sign-up.scss'
</style>