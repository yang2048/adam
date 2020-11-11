---
title: 失败页
---
<template>
  <my-wrapper fit>
    <my-container>
      <my-result status="error"
                 title="提交失败"
                 subtitle="请核对并修改以下信息后，再重新提交。">
        <my-title level="4">您提交的内容有如下错误：</my-title>
        <my-paragraph>
          <my-icon name="el-icon-circle-close" theme="danger"></my-icon>
          您的账户已被冻结
          <my-text type="primary">立即解冻 &gt;</my-text>
        </my-paragraph>
        <my-paragraph>
          <my-icon name="el-icon-circle-close" theme="danger"></my-icon>
          您的账户还不具备申请资格
          <my-text type="primary">立即升级 &gt;</my-text>
        </my-paragraph>
        <template v-slot:extra>
          <el-button type="primary">返回修改</el-button>
        </template>
      </my-result>
    </my-container>
  </my-wrapper>
</template>

<script>
  export default {}
</script>

