<template>
    <div>
        <div class="info">
            <div class="title">
                <span>编辑个人资料</span>
            </div>
            <hr/>
            <div class="personal">
                <el-form :model="registerFrom" ref="registerFrom" label-width="70px" class="demo-ruleFrom" :rules="rules">
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="registerFrom.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input type="password" v-model="registerFrom.password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item prop="sex" label="性别">
                        <el-radio-group v-model="registerFrom.sex">
                            <el-radio :label="0">女</el-radio>
                            <el-radio :label="1">男</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="phone" label="手机">
                        <el-input  v-model="registerFrom.phone" placeholder="手机"></el-input>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input  v-model="registerFrom.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item prop="birthday" label="生日">
                        <el-date-picker type="date" :editable="false" v-model="registerFrom.birthday" placeholder="选择日期" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="introduction" label="签名">
                        <el-input  v-model="registerFrom.introduction" placeholder="签名"></el-input>
                    </el-form-item>
                    <el-form-item prop="location" label="地区">
                        <el-select  v-model="registerFrom.location" placeholder="地区" style="width: 100%;">
                            <el-option v-for="item in cities" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div class="btn">
                    <div @click="saveMsg">保存</div>
                    <div @click="goback(-1)">取消</div>
                    
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>
import { rules,cities } from '../../assets/data/form'
import {mixin} from '../../mixins'
import {signUp,getUserOfId,updateUserOfuserId} from '../../api/index'
import { mapGetters } from 'vuex'
export default {
    name:'info',
    mixins:[mixin],
    data(){
        return{
            registerFrom:{
                username:'',    //用户名
                password:'',    //密码
                sex:'',         //性别
                phone:'',    //手机号
                birthday:'',     //生日
                introduction:'',    //签名
                location:'',        //地区
                email:'',       //邮箱
            },
            cities:[],      //所有的地区--省和直辖市
            rukes:{         //表单提交的规则

            },
        }
    },
    created(){
        this.rules=rules
        this.cities=cities
    },
    mounted(){
        this.getUserMsg(this.userId)
    },
    computed:{
        ...mapGetters([
            'userId'
        ])
    },
    methods:{
        //获取用户信息
        getUserMsg(userId){
            getUserOfId(userId)
            .then(res=>{
                this.registerFrom.username=res.username
                this.registerFrom.password=res.password
                this.registerFrom.sex=res.sex
                this.registerFrom.phone=res.phone
                this.registerFrom.birthday=res.birthday
                this.registerFrom.introduction=res.introduction
                this.registerFrom.location=res.location
                this.registerFrom.email=res.email
            })
        },
        //保存按钮
        saveMsg(){
            let _this=this
            let d=new Date(this.registerFrom.birthday)
            let datetime=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()
            let params =new URLSearchParams()
            params.append('id',this.userId)
            params.append('username',this.registerFrom.username)
            params.append('password',this.registerFrom.password)
            params.append('sex',this.registerFrom.sex)
            params.append('phone',this.registerFrom.phone)
            params.append('email',this.registerFrom.email)
            params.append('birthday',datetime)
            params.append('introduction',this.registerFrom.introduction)
            params.append('location',this.registerFrom.location)
            updateUserOfuserId(params)      //api中的方法
            .then(res=>{
                if(res.code==1){
                    _this.$store.commit('setUsername',_this.registerFrom.username)
                    _this.notify('修改成功','success')
                    // setTimeout(function(){
                    //     _this.$router.push({path:'/'})
                    //     _this.$store.commit('setActiveName','首页')
                    // },1000)
                }else{
                    _this.notify('修改失败','error')
                }
            })
            .catch(err=>{
                _this.notify('修改失败','error');
            })
        },
        goback(index){
            this.$router.go(index)
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/info.scss'
</style>