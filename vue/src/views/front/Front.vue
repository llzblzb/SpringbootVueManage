<template>
  <div>
    <!--头部-->
    <div style="display: flex;height: 60px;line-height: 60px;border-bottom: 1px solid #ccc">
      <div style="width: 300px;display: flex;padding-left: 30px">
        <div style="width: 60px">
          <img src="../../assets/edg.png" alt="" style="width: 50px;position: relative;top: 5px;right: 0">
        </div>
        <div style="flex: 1">欢迎来到xx系统</div>
      </div>
      <div style="flex: 1">
        <!--导航菜单-->
        <!--        <ul style="list-style: none;background-color:	#B0E0E6;">-->
        <!--          <li class="item">-->
        <!--            <el-dropdown size="medium">-->
        <!--              <span>更多菜单<i class="el-icon-arrow-down el-icon-sort-down"></i></span>-->
        <!--              <el-dropdown-menu slot="dropdown">-->
        <!--                <el-dropdown-item>黄金糕</el-dropdown-item>-->
        <!--                <el-dropdown-item>狮子头</el-dropdown-item>-->
        <!--                <el-dropdown-item>螺蛳粉</el-dropdown-item>-->
        <!--                <el-dropdown-item disabled>双皮奶</el-dropdown-item>-->
        <!--                <el-dropdown-item divided>蚵仔煎</el-dropdown-item>-->
        <!--              </el-dropdown-menu>-->
        <!--            </el-dropdown>-->
        <!--          </li>-->
        <!--          <li class="item">-->
        <!--            <a href="/login">-->
        <!--              菜单二-->
        <!--            </a></li>-->
        <!--          <li class="item">-->
        <!--            <a href="/login">-->
        <!--              菜单三-->
        <!--            </a></li>-->
        <!--          <li class="item">-->
        <!--            <a href="/login">-->
        <!--              菜单四-->
        <!--            </a></li>-->
        <!--        </ul>-->
        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item index="/front/item1">item1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项1</el-menu-item>
              <el-menu-item index="2-4-2">选项2</el-menu-item>
              <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="3" disabled>消息中心</el-menu-item>
          <el-menu-item index="/home" v-if="user.username">后台主页</el-menu-item>
        </el-menu>
        <!--        -->
      </div>
      <div style="width: 200px">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>

        <div v-if="user.username">
          <el-dropdown style="width: 150px;cursor: pointer;text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width: 30px;border-radius: 50%;position:relative;top:10px;right: 5px">
              <span>{{ user.nickname }}</span>
              <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
              <el-dropdown-item style="font-size: 15px;padding: 5px 0">
                <router-link to="/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px;padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>


      </div>
    </div>

    <div style="width: 1000px; margin: 0 auto">
      <router-view></router-view>
    </div>

  </div>
</template>

<script>
export default {
  name: "Front",
  props: {},
  computed: {},
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods: {
    logout() {
      this.$store.commit("logout")
      this.$message.success("退出成功")
    }
  },
}
</script>

<style>
.item {
  display: inline-block;
  width: 150px;
  text-align: center;
  cursor: pointer;
}

.item a {
  color: #1E90FF;
}

.item:hover {
  background-color: LightPink;
}

</style>