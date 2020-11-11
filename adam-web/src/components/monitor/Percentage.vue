<template>
  <el-row>
    <el-col :span="8">
      <el-progress type="circle" :percentage="15" :stroke-width="10"></el-progress>
    </el-col>
    <el-col :span="8">
      <el-progress type="circle" :percentage="50" :stroke-width="10"></el-progress>
    </el-col>
    <el-col :span="8">
      <el-progress type="circle" :percentage="35" :stroke-width="10"></el-progress>
    </el-col>
  </el-row>
</template>

<script>
  export default {}
</script>

<style lang="scss" scoped>
  .el-col {
    text-align: center;
    height: 200px;
  }

  .el-progress {
    margin-top: 40px;
  }
</style>
