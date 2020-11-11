<template>
  <div class="trade">
    <el-row :gutter="14">
      <el-col :xs="12" :sm="12" :md="6" :lg="6">
        <my-description title="今日交易总额" top>
          <my-title>
            <my-number suffix="元" :value="124543233" sup></my-number>
          </my-title>
        </my-description>
      </el-col>
      <el-col :xs="12" :sm="12" :md="6" :lg="6">
        <my-description title="销售目标完成率" top>
          <my-title>
            <my-number :value="0.92" percentage></my-number>
          </my-title>
        </my-description>
      </el-col>
      <el-col :xs="12" :sm="12" :md="6" :lg="6">
        <my-description title="活动剩余时间" top>
          <my-title>
            <my-timer countdown target="24:00:00" auto :interval="10" format="H:mm:ss.SSS"></my-timer>
          </my-title>
        </my-description>
      </el-col>
      <el-col :xs="12" :sm="12" :md="6" :lg="6">
        <my-description title="每秒交易总额" top>
          <my-title>
            <my-number suffix="元" :value="1223" sup></my-number>
          </my-title>
        </my-description>
      </el-col>
    </el-row>
    <el-row>
      <my-chart-map height="420px" :data="data" :register="register" map="china"></my-chart-map>
    </el-row>

  </div>
</template>

<script>
  import china from '$ui/charts/geo/china.json'

  export default {
    data() {
      return {
        register: china,
        data: {
          columns: ['省份', '交易额'],
          rows: [
            ['广东', 100],
            ['北京', 60],
            ['广西', 78],
            ['湖南', 90],
            ['湖北', 110],
            ['福建', 87],
            ['新疆', 90],
            ['贵州', 75],
            ['江苏', 63],
            ['山西', 80],
            ['山东', 90],
            ['黑龙江', 105]
          ]
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  .my-description {
    height: 65px;
  }
</style>
