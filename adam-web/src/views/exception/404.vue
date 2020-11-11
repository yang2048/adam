---
title: 404
---
<template>
  <my-wrapper>
    <my-container>
      <my-result status="404"
                 title="404"
                 subtitle="抱歉！您访问的页面不存在。">
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

