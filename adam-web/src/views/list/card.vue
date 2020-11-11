---
title: 卡片列表
---
<template>
  <my-wrapper title="卡片列表">
    <template v-slot:extra>
      卡片类型的列表，配合栅格实现响应式布局。
    </template>
    <template v-slot:actions>
      <el-button type="primary" size="medium" icon="el-icon-plus">新增</el-button>
    </template>
    <div v-infinite-scroll="loader"
         :infinite-scroll-disabled="disabled">
      <my-card-list :data="list"
                    :columns="{xs:1,sm:1,md:2,lg:2,xl:3,xxl:4}">
        <my-panel slot-scope="{item}"
                  shadow="hover"
                  :actions="actions"
                  :header="false">
          <my-corner-mark :type="{ '逃': 'danger', '毒': 'warning'}[item.label]">{{item.label}}</my-corner-mark>
          <div class="data-item">
            <el-image :src="item.avatar" fit="cover"></el-image>
            <div class="text">
              <my-title :level="3">{{item.name}}</my-title>
              <my-paragraph ellipsis :rows="3">
                {{item.info}}
              </my-paragraph>
            </div>
          </div>
        </my-panel>
      </my-card-list>
      <div class="loading" v-if="loading">
        <my-spin loading tip="正在努力加载..."></my-spin>
      </div>
      <div class="empty" v-if="noMore">没有更多了</div>
    </div>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        list: [],
        page: 0,
        limit: 15,
        total: null,
        loading: false,
        actions: [
          {
            text: '查看',
            icon: 'el-icon-view'
          },
          {
            text: '编辑',
            icon: 'el-icon-edit'
          },
          {
            text: '删除',
            icon: 'el-icon-delete'
          }
        ]
      }
    },
    computed: {
      noMore() {
        return this.total === null
          ? false
          : this.page >= Math.ceil(this.total / this.limit)
      },
      disabled() {
        return this.loading || this.noMore
      }
    },
    methods: {
      loader() {
        this.loading = true
        ++this.page
        this.fetchMockForExample({
          data: {
            page: this.page,
            limit: this.limit
          }
        }).then(res => {
          this.total = res.total
          this.list = this.list.concat(res.list)
        }).finally(() => {
          this.loading = false
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .data-item {
    height: 100px;

    .el-image {
      width: 120px;
      height: 100px;
      float: left;
    }

    .my-title {
      margin-top: 0;
    }

    .my-paragraph {
      margin-bottom: 0;
    }

    .text {
      padding-left: 130px;
    }
  }

  .loading, .empty {
    padding: 14px;
    text-align: center;
  }

  .empty {
    border-top: 1px dotted $--color-divider;
    color: $--color-secondary-text;
  }
</style>
