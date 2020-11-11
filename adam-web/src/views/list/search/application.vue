---
title: 搜索列表（应用）
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
                  :page-size="16"
                  pager>
      <my-panel slot-scope="{item}"
                :header="null"
                shadow="hover"
                :actions="actions"
                @action="handleAction(item, $event)">
        <my-description :width="40">
          <my-avatar slot="title" theme="warning">{{item.name.substring(0,1)}}</my-avatar>
          <my-title :level="4" ellipsis :rows="1">{{item.title}}</my-title>
          <my-float>
            <my-float-item>
              <my-description top title="活跃用户">
                <my-title :level="2">{{item.views}}</my-title>
              </my-description>
            </my-float-item>
            <my-float-item float="right">
              <my-description top title="新增用户">
                <my-title :level="2">{{item.views}}</my-title>
              </my-description>
            </my-float-item>
          </my-float>
        </my-description>
        <template v-slot:action="{name, more}">
          <el-dropdown v-if="more" placement="bottom-start">
            <my-icon :name="name"></my-icon>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>黄金糕</el-dropdown-item>
              <el-dropdown-item>狮子头</el-dropdown-item>
              <el-dropdown-item>螺蛳粉</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <my-icon v-else :name="name"></my-icon>
        </template>
      </my-panel>
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
        list: [],
        actions: [
          {name: 'el-icon-download'},
          {name: 'el-icon-edit'},
          {name: 'el-icon-share'},
          {name: 'el-icon-more', more: true}
        ]
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
      },
      handleAction(item, action) {
        console.log(item, action)
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
