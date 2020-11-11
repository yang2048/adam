<template>
  <div>
    <my-chart-cloud debug :data="data" :extend="extend" height="200px"></my-chart-cloud>
  </div>
</template>

<script>
  const words = ['阿德', '紧凑', '语句', '区分', '高级', '实用', '阿德', '紧凑', '语句', '区分', '高级', '实用']
  export default {
    data() {
      return {
        data: {
          columns: ['标签'],
          rows: words.map(text => [text])
        },
        extend: {
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
