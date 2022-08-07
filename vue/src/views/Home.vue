<template>


  <div>
    <el-row :gutter="10" style="margin-bottom: 20px;height: 150px">
      <el-col :span="6">
        <el-card>
          <div  style="color: #409EFF"><i class="el-icon-user-solid"></i>用户总数</div>
          <div style="padding:10px 0;text-align: center;font-weight: bold">
           100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #F56C6C"><i class="el-icon-money"></i>销售总量</div>
          <div style="padding:10px 0;text-align: center;font-weight: bold">
            ￥100000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #67C23A"><i class="el-icon-bank-card"></i>收益总额</div>
          <div style="padding:10px 0;text-align: center;font-weight: bold">
            ￥30000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #E6A23C"><i class="el-icon-s-shop"></i>门店总数</div>
          <div style="padding:10px 0;text-align: center;font-weight: bold">
            20
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px;height: 450px;"></div>
      </el-col>
      <el-col :span="12">
        <div id="pie" style="width: 500px;height: 450px;"></div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home",
  data() {
    return {}
  },
  mounted() {   //页面元素渲染之后再触发


    var option = {
      title: {
        text: '各季度会员统计',
        subtext: '趋势图',
        left: 'center'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度", "第二季度", "第三季度", "第四季度"]
      },
      tooltip: {
        trigger: 'item'
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name:"星巴克",
          data: [],
          type: 'line'
        },
        {
          name:"星巴克",
          data: [],
          type: 'bar'
        },
        {
          name:"瑞幸咖啡",
          data: [],
          type: 'line'
        },
        {
          name:"瑞幸咖啡",
          data: [],
          type: 'bar'
        },

      ]
    };
//
    var pieoption = {
      title: {
        text: '各季度会员统计',
        subtext: '比例图',
        left: 'center'
      },

      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/> {b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name:"星巴克",
          type: 'pie',
          center:['25%','50%'],
          radius: '50%',
          label:{
            normal:{
            show:true,
            position:'inner',
            textStyle:{
              fontWeight: 300,
              fontsize: 14,
              color: "#fff"
            },
              formatter: '{c} ({d}%)'

            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        {
          name:"瑞幸咖啡",
          type: 'pie',
          radius: '50%',
          center:['75%','50%'],
          label:{
            normal:{
              show:true,
              position:'inner',
              textStyle:{
                fontWeight: 300,
                fontsize: 14,
                color: "#fff"
              },
              formatter: '{d}%'

            }
          },
          data: [
              {name:"第一季度",value: 5},
            {name:"第二季度",value: 6},
            {name:"第三季度",value: 7},
            {name:"第四季度",value: 8},
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    //饼图
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);


    this.request.get("/echarts/members").then(res => {
      //填空
      // option.xAxis.data=res.data.x
      option.series[0].data = res.data
      option.series[1].data = res.data
      option.series[2].data =[5,6,7,8]
      option.series[3].data =[5,6,7,8]
      //数据准备完毕后set
      myChart.setOption(option);


      pieoption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},

      ]
      pieChart.setOption(pieoption);
    })



  }
}

</script>

<style>

</style>