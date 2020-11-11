<template>
  <div class="safe">
    <my-title>账号绑定</my-title>
    <my-float>
      <my-float-item>
        <my-description :width="50">
          <my-avatar slot="title" theme="warning" icon="el-icon-user" :size="50" shape="square"></my-avatar>
          <my-description :gutter="5" top title="绑定淘宝">当前未绑定淘宝账号</my-description>
        </my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">绑定</el-button>
      </my-float-item>
    </my-float>

    <my-float>
      <my-float-item>
        <my-description :width="50">
          <my-avatar slot="title" theme="danger" icon="el-icon-user" :size="50" shape="square"></my-avatar>
          <my-description :gutter="5" top title="绑定支付宝">当前未绑定支付宝账号</my-description>
        </my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">绑定</el-button>
      </my-float-item>
    </my-float>

    <my-float>
      <my-float-item>
        <my-description :width="50">
          <my-avatar slot="title" theme="success" icon="el-icon-user" :size="50" shape="square"></my-avatar>
          <my-description :gutter="5" top title="绑定钉钉">当前未绑定钉钉账号</my-description>
        </my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">绑定</el-button>
      </my-float-item>
    </my-float>
  </div>
</template>

<script>
  export default {}
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

  .el-button {
    margin-top: 14px;
  }
</style>
