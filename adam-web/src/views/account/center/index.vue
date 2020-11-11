---
title: 个人中心
---
<template>
  <my-list ref="list" :loader="loader" split :pagination="{align:'center'}">
    <template v-slot="{item}">
      <my-title :level="4">
        <el-link type="primary">
          {{item.title}}
        </el-link>
      </my-title>
      <my-paragraph>
        <el-tag v-for="(tag,index) in item.tags"
                :key="`${tag}-${index}`"
                type="success"
                size="small">{{tag}}
        </el-tag>
      </my-paragraph>
      <my-paragraph ellipsis :rows="2">{{item.info}}</my-paragraph>
      <my-description inline title="作者：" :gutter="0">{{item.name}}</my-description>
      <my-description inline title="发布时间：" :gutter="0">{{item.created}}</my-description>
      <my-paragraph class="actions">
        <el-link :underline="false"><i class="el-icon-star-off"></i>{{item.views}}</el-link>
        <el-divider direction="vertical"></el-divider>
        <el-link :underline="false"><i class="el-icon-chat-dot-square"></i>{{item.shares}}</el-link>
        <el-divider direction="vertical"></el-divider>
        <el-link :underline="false"><i class="el-icon-pie-chart"></i>{{item.comments}}</el-link>
      </my-paragraph>

    </template>
  </my-list>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    methods: {
      loader(page, limit) {
        return this.fetchMockForExample({
          data: {
            page,
            limit
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .actions {
    font-size: 16px;
    margin-left: -20px;
    text-align: right;

    .el-link {
      padding: 0 20px;

      i {
        margin-right: 6px;
      }
    }
  }
</style>
