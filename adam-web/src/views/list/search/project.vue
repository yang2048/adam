---
title: 搜索列表（项目）
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

    <my-card-list ref="list"
                  class="card-list"
                  :loader="loader"
                  :columns="{xs:1,sm:1,md:2,lg:3,xl:4,xxl:6}"
                  pager>
      <el-card slot-scope="{item}"
               shadow="hover"
               :body-style="{padding:0}">
        <el-image :src="item.avatar" style="height: 180px; width:100%" fit="cover"></el-image>
        <my-container>
          <my-title :level="4">{{item.name}}</my-title>
          <my-paragraph ellipsis :rows="2">{{item.info}}</my-paragraph>
          <my-float>
            <my-float-item>
              <my-text type="secondary">{{item.date}}</my-text>
            </my-float-item>
            <my-float-item float="right">
              <el-link :underline="false"><i class="el-icon-star-off"></i>
                {{item.views}}
              </el-link>
            </my-float-item>
          </my-float>
        </my-container>
      </el-card>
    </my-card-list>

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
  .card-list {
    margin-top: 14px;
  }
</style>
