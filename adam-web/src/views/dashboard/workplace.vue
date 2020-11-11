---
title: 工作台
---
<template>
  <my-wrapper>
    <my-float slot="extra">
      <my-float-item>
        <my-description :width="72">
          <my-avatar slot="title" :size="72" theme="warning" icon="el-icon-user"></my-avatar>
          <my-title class="welcome" :level="2">早安 <span>Admin</span>，祝你开心每一天！</my-title>
          <my-text type="secondary">交互专家 | 新德汇－某某某事业群－某某平台部－某某技术部－UED</my-text>
        </my-description>
      </my-float-item>
      <my-float-item float="right">
        <my-description title="项目数量" inline top align="right">
          <my-title>
            <my-number :value="56"></my-number>
          </my-title>
        </my-description>
        <el-divider direction="vertical"></el-divider>
        <my-description title="团队内排名" inline top align="right">
          <my-title>
            <my-number :value="8" suffix=" / 24" sup></my-number>
          </my-title>
        </my-description>
        <el-divider direction="vertical"></el-divider>
        <my-description title="项目访问" inline top align="right">
          <my-title>
            <my-number :value="2223"></my-number>
          </my-title>
        </my-description>
      </my-float-item>
    </my-float>

    <el-row :gutter="14">
      <el-col :xs="24" :sm="24" :md="16">
        <my-panel title="进行中的项目"
                  shadow="never"
                  :border="false"
                  :body-style="{padding:0}"
                  theme="border-left">
          <template v-slot:handle>
            <el-button type="text">全部项目</el-button>
          </template>
          <my-list class="project-list" :data="projects" :columns="3" split>
            <template v-slot="{item}">
              <div>
                <my-description :width="32" :gutter="8">
                  <my-avatar slot="title" :src="item.avatar"></my-avatar>
                  <my-title :level="4" :rows="1" ellipsis style="padding-top: 5px">{{item.title}}</my-title>
                </my-description>
                <my-paragraph type="secondary" :rows="2" ellipsis>{{item.info}}</my-paragraph>
                <my-float>
                  <my-float-item>
                    <my-text type="secondary" small>{{item.name}}</my-text>
                  </my-float-item>
                  <my-float-item float="right">
                    <my-text type="secondary" small>{{item.date}}</my-text>
                  </my-float-item>
                </my-float>
              </div>
            </template>
          </my-list>
        </my-panel>

        <my-panel title="动态" shadow="never"
                  :border="false"
                  theme="border-left">

          <my-list :data="projects" split>
            <template v-slot="{item}">
              <my-description :width="40" style="margin-bottom: 0;">
                <my-avatar slot="title" size="large" :src="item.avatar">
                </my-avatar>
                <my-paragraph style="margin-bottom: 0;">{{item.name}} 在
                  <el-link type="primary">{{item.city}}</el-link>
                  新建项目
                  <el-link type="primary">{{item.title}}</el-link>
                  <br>
                  <my-text type="secondary">{{item.created}}</my-text>
                </my-paragraph>

              </my-description>
            </template>
          </my-list>

        </my-panel>

      </el-col>
      <el-col :xs="24" :sm="24" :md="8">
        <my-panel title="便捷导航" shadow="never" :border="false" theme="border-left">
          <template v-slot:handle>
            <el-button type="text" icon="el-icon-plus">添加</el-button>
          </template>
          <my-list :data="projects" :columns="4" size="small">
            <template v-slot="{item}">
              <el-link>{{item.name}}</el-link>
            </template>
          </my-list>
        </my-panel>

        <my-panel title="XX指数" shadow="never" :border="false" theme="border-left">
          <my-chart-radar :data="rader" height="200px"></my-chart-radar>
        </my-panel>

        <my-panel title="团队" shadow="never" :border="false" theme="border-left">
          <my-list :data="projects" :columns="2" size="small">
            <template v-slot="{item}">
              <my-description :width="32">
                <my-avatar :src="item.avatar"></my-avatar>
                {{item.title}}
              </my-description>
            </template>
          </my-list>
        </my-panel>

      </el-col>
    </el-row>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        projects: [],
        rader: {
          columns: ['指标名称', '销售', '管理', '客服', '研发', '市场'],
          rows: [
            ['预算分配', 20, 50, 80, 29, 55, 60],
            ['实际开销', 30, 60, 30, 79, 45, 80]
          ]
        }
      }
    },
    created() {
      this.fetchMockForExample({
        data: {
          page: 1,
          limit: 6
        }
      }).then(res => {
        this.projects = res.list
      })
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .el-col {
    margin-bottom: 14px;
  }

  .my-panel {
    overflow: visible;
  }

  .project-list {
    /deep/ {
      .my-list__item {
        cursor: pointer;

        &:hover {
          box-shadow: $--box-shadow-dark;
        }
      }
    }

  }


  .welcome {
    padding-top: 8px;
  }

  .el-divider {
    height: 40px;
    vertical-align: top;
    margin-top: 10px;
    margin-right: 30px;
  }

</style>
