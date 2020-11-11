<template>
  <div class="safe">
    <my-title>安全设置</my-title>
    <my-float>
      <my-float-item>
        账户密码
        <my-description title="当前密码强度：">强</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">修改</el-button>
      </my-float-item>
    </my-float>
    <my-float>
      <my-float-item>
        密保手机
        <my-description title="已绑定手机：">138****8293</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">修改</el-button>
      </my-float-item>
    </my-float>
    <my-float>
      <my-float-item>
        密保问题
        <my-description>未设置密保问题，密保问题可有效保护账户安全</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">修改</el-button>
      </my-float-item>
    </my-float>
    <my-float>
      <my-float-item>
        备用邮箱
        <my-description title="已绑定邮箱：">ant***sign.com</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">修改</el-button>
      </my-float-item>
    </my-float>
    <my-float>
      <my-float-item>
        MFA 设备
        <my-description>未绑定 MFA 设备，绑定后，可以进行二次确认</my-description>
      </my-float-item>
      <my-float-item float="right">
        <el-button type="text">修改</el-button>
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
