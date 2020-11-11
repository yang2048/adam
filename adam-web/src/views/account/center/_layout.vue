<template>
  <my-wrapper title="个人中心">
    <el-row :gutter="14">
      <el-col :span="6" :xs="24" :sm="24" :md="7">
        <my-panel v-if="userInfo" shadow="never" :border="false" :header="null">
          <my-description top align="center">
            <my-avatar slot="title" :src="userInfo.avatar" :size="100"></my-avatar>
            <my-title :level="3">{{userInfo.name}}</my-title>
            <my-paragraph>{{userInfo.title}}</my-paragraph>
          </my-description>
          <el-divider></el-divider>
          <my-description title="地址" :width="50" align="right">{{userInfo.county}}</my-description>
          <my-description title="邮编" :width="50" align="right">{{userInfo.zip}}</my-description>
          <my-description title="年龄" :width="50" align="right">{{userInfo.age}}</my-description>
          <my-description title="生日" :width="50" align="right">{{userInfo.date}}</my-description>
          <my-description title="简介" :width="50" align="right">{{userInfo.info}}</my-description>
          <el-divider></el-divider>
          <my-description title="标签" top :width="50" align="left">
            <el-tag v-for="tag in userInfo.tags" :key="tag" size="small">{{tag}}</el-tag>
          </my-description>
        </my-panel>
      </el-col>
      <el-col :span="18" :xs="24" :sm="24" :md="17">
        <my-panel shadow="never" :border="false" :tabs="tabs" size="large" @tab-change="handleTabChange">
          <template v-slot:label="tab">
            {{tab.label}}
            <my-text style="font-size: 12px;" type="secondary">({{tab.count}})</my-text>
          </template>
          <router-view></router-view>
        </my-panel>
      </el-col>
    </el-row>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    inject: ['myPro'],
    data() {
      return {
        tabs: [
          {label: '文章', name: 'article', count: 323},
          {label: '项目', name: 'project', count: 483},
          {label: '应用', name: 'application', count: 59}
        ],
        userInfo: {}
      }
    },
    computed: {
      fit() {
        return this.myPro.fixed
      }
    },
    methods: {
      handleTabChange(name) {
        const path = name === 'article' ? '' : `/${name}`
        this.$router.push(`/account/center${path}`)
      }
    },
    created() {
      this.getMockForExample({
        id: 123
      }).then(res => {
        this.userInfo = res
      })
    }
  }
</script>

<style lang="scss" scoped>
  .el-col {
    margin-bottom: 14px;
  }
</style>
