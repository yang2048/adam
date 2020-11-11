<template>
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

<style scoped>

</style>
