<template>
  <div>
    <my-chart-line :data="data" :settings="settings" :extend="extend" height="200px"></my-chart-line>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: {
          columns: ['月份', '直接访问', '搜索引擎'],
          rows: [
            ['一月', 100, 30],
            ['二月', 60, 20],
            ['三月', 78, 25],
            ['四月', 90, 40],
            ['五月', 110, 70],
            ['六月', 87, 60],
            ['七月', 90, 76],
            ['八月', 75, 45],
            ['九月', 63, 46],
            ['十月', 80, 70],
            ['十一月', 90, 85],
            ['十二月', 105, 96]
          ]
        },
        settings: {
          stack: ['直接访问', '搜索引擎']
        },
        extend: {
          grid: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 20
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          yAxis: {
            show: false
          }
        }
      }
    }
  }
</script>

<style scoped>

</style>
