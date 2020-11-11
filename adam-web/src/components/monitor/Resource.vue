<template>
  <div>
    <my-chart-fill debug :data="data" :extend="extend" height="200px"></my-chart-fill>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: [0.6],

        extend: {
          'series.0.radius': '90%',
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
