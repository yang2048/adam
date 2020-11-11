<template>
  <my-card-list ref="list"
                class="card-list"
                :loader="loader"
                :columns="{xs:1,sm:1,md:2,lg:3,xl:3,xxl:4}"
                :page-size="15"
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
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        actions: [
          {name: 'el-icon-download'},
          {name: 'el-icon-edit'},
          {name: 'el-icon-share'},
          {name: 'el-icon-more', more: true}
        ]
      }
    },
    methods: {
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
    }
  }
</script>

<style scoped>

</style>
