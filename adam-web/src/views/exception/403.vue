---
title: 403
---
<template>
  <my-wrapper>
    <my-container>
      <my-result status="403"
                 title="403"
                 subtitle="抱歉！您无权限访问该页面。">
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

