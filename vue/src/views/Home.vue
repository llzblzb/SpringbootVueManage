<template>
  <div>
    <el-card style="font-size: 30px">
      <el-row style="color: red">
        欢迎来到我的主页！
        <el-button @click="toFront">前台测试首页</el-button>
      </el-row>
      <el-row style="color: #3F5EF8">
        Welcome to my homepage!
      </el-row>
      <el-row style="color: #67C23A">
        Bienvenue sur mon site!
      </el-row>
      <el-row style="color: #E6A23C">
        Willkommen auf meiner seite!
      </el-row>
      <el-row style="color: #F56C6C">
        Добро пожаловать на мою страничку!
      </el-row>
    </el-card>
    <el-row>
      <el-card style="background: #8c939d">
        本站源码github: https://github.com/llzblzb/SpringbootVueManage
      </el-card>
    </el-row>
    <el-row>
        本站仅供学习测试 不断更新ing
    </el-row>
  </div>

</template>

<script>

export default {
  name: "Home",
  data() {
    return {}
  },
  methods:{
    toFront(){
      this.$router.replace('/front/home')
    },
  }
}

</script>

<style>

</style>