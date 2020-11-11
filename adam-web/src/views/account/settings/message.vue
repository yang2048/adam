<template>
  <div class="safe">
    <my-title>新消息通知</my-title>
    <my-float>
      <my-float-item>
        <my-description :gutter="5" top title="账户密码">其他用户的消息将以站内信的形式通知</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-switch v-model="value1"></el-switch>
      </my-float-item>
    </my-float>

    <my-float>
      <my-float-item>
        <my-description :gutter="5" top title="系统消息">系统消息将以站内信的形式通知</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-switch v-model="value2"></el-switch>
      </my-float-item>
    </my-float>

    <my-float>
      <my-float-item>
        <my-description :gutter="5" top title="待办任务">待办任务将以站内信的形式通知</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-switch v-model="value3"></el-switch>
      </my-float-item>
    </my-float>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        value1: true,
        value2: true,
        value3: true
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .safe {
    padding-left: 30px;
  }

  .my-float {
    margin: 10px 0;
    padding: 10px 0;
    border-bottom: 1px solid $--color-background;
  }

  .my-description {
    margin-top: 5px;
  }

  .el-switch {
    margin-top: 14px;
  }
</style>
