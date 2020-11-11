---
title: 500
---
<template>
  <my-wrapper>
    <my-container>
      <my-result status="500"
                 title="500"
                 subtitle="抱歉！服务出现了异常，请稍后再试或联系管理员。">
        <template v-slot:extra>
          <el-button type="primary">返 回</el-button>
        </template>
      </my-result>
    </my-container>
  </my-wrapper>
</template>

<script>
  export default {}
</script>

