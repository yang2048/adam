<template>
  <div>
    <my-chart-pie :data="data" height="200px" :extend="extend"></my-chart-pie>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: {
          columns: ['渠道', '访问量'],
          rows: [
            ['直接访问', 320],
            ['邮件营销', 302],
            ['联盟广告', 334],
            ['视频广告', 390],
            ['搜索引擎', 330]
          ]
        },
        extend: {
          'legend.show': false,
          'series.label.position': 'inside',
          'series.radius': [0, '90%'],
          grid: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0
          }
        }
      }
    }
  }
</script>

<style scoped>

</style>
