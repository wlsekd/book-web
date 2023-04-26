<template>
  <div class="login-wrap">
    <div class="ms-title">book 后台管理登录</div>
    <div class="ms-login">
      <el-form :model="ruleForm" :rules="rules" ref="ruleFrom">
        <el-form-item prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="ruleForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
import {getLoginStatus} from '../api/index.js'
import {mixin} from '../mixins/index'
import { mapGetters } from 'vuex'
export default {
  mixins:[mixin],
  computed:{
    ...mapGetters([
      'loginIn'
    ])
  },
  data: function () {
    return {
      ruleForm: {
        username: 'admin',
        password: '123'
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm () {
      let params=new URLSearchParams()
      params.append('name',this.ruleForm.username)
      params.append('password',this.ruleForm.password)
      getLoginStatus(params)
        .then((res)=>{
          if(res.code == 1){
            this.$store.commit('setLoginIn',true)
            localStorage.setItem('username',this.ruleForm.username)
            this.notify('登录成功','success')
            this.$router.push('/Info')
          }else{
            this.notify('登录失败','error')
          }
        })
    }
  }
}
</script>

<style scoped>
.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  height: 160px;
  margin-left: -150px;
  margin-top: -150px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}

.login-wrap {
  position: relative;
  background: url("../assets/img/background.jpeg");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}

.ms-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}</style>
