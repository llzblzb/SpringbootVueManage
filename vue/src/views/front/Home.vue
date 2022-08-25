<template>
  <div>
    <div style="margin: 10px 0">
      <div class="block">
        <el-carousel height="550px" :interval="10000">
          <el-carousel-item v-for="item in imgs" :key="item">
            <img :src="item" alt="" style="width: 100%">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="margin: 10px 0">
        <el-row :gutter="10">
          <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">
            <div style="border: 1px solid #ccc;padding-bottom: 10px" >
              <img :src="item.url" style="width: 100%">
              <div style="color: #666; padding: 10px">
                <span>{{item.name}}</span>
              </div>
              <div><el-button>购买</el-button></div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontHome",
  data(){
    return{
      imgs:[
          'https://img10.360buyimg.com/babel/s1580x830_jfs/t1/103874/34/25032/85519/62f21fa9E035c591a/7795887cbf84cbd0.jpg!cc_1580x830.webp',
          'https://img10.360buyimg.com/babel/s1580x830_jfs/t1/43768/17/17556/83867/62d92ed9E57d9c346/f7bfb8c56bb4f096.jpg!cc_1580x830.webp',
          'https://img10.360buyimg.com/babel/s1580x830_jfs/t1/126671/18/30400/90694/62f633bdE01ae333a/9fbf9fc5e8be6b5c.jpg!cc_1580x830.webp',
          'https://img13.360buyimg.com/babel/s1580x830_jfs/t1/140629/16/29191/85064/62fb44d6Ee7fc4ca3/3c5f24d58f064e83.jpg!cc_1580x830.webp',

      ],
      files: []
    }
  },
  created() {
    this.request.get("/echarts/file/front/all").then(res=>{
      console.log(res.data)
      this.files = res.data.filter(v =>v.type ==='png' || v.type==='jpg'||v.type==='webp')

    })
  }
}
</script>

<style >

</style>