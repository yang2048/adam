---
title: 搜索列表（文章）
---
<template>
  <div>
    <my-container>
      <my-form :model="query"
               size="small"
               label-width="100px"
               :footer="null"
               inline
               @change="handleChange">
        <my-tag-select label="所属类目："
                       name="tag"
                       width="100%"
                       :options="tags"
                       :props="{multiple:true, checkAll:true, collapsible:true}"></my-tag-select>
        <my-select label="作者："
                   name="owner"
                   width="calc(50% - 20px)"
                   :loader="optionLoader"
                   :key-map="{label:'name',value:'id'}"
                   :props="{multiple:true,  collapseTags:true}"></my-select>
        <my-range label="日期："
                  name="date"
                  mode="date"
                  width="calc(50% - 20px)"
                  :props="{valueFormat:'yyyy-MM-dd'}"></my-range>
      </my-form>
    </my-container>

    <my-container>
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
    </my-container>
  </div>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        tags: [],
        query: {},
        keyword: this.$route.query.keyword,
        list: []
      }
    },
    watch: {
      query() {
        this.$refs.list.refresh(1)
      },
      $route: {
        handler(val) {
          this.keyword = val.query.keyword
        }
      },
      keyword() {
        this.$refs.list.refresh(1)
      }
    },
    methods: {
      getTags() {
        const tags = []
        for (let i = 0; i < 20; i++) {
          tags.push(`类目${i + 1}`)
        }
        return tags
      },
      optionLoader() {
        return this.fetchMockForExample().then(res => {
          return res.list
        })
      },
      handleChange(query) {
        this.query = query
      },
      loader(page, limit) {
        return this.fetchMockForExample({
          data: {
            keyword: this.keyword,
            ...this.query,
            page,
            limit
          }
        })
      }
    },
    created() {
      this.tags = this.getTags()
    }
  }
</script>

<style lang="scss" scoped>
  .actions {
    font-size: 16px;
    text-align: right;

    .el-link {
      padding: 0 20px;

      i {
        margin-right: 6px;
      }
    }
  }
</style>
