---
title: 视图权限
---
<template>
  <my-wrapper title="视图权限控制">
    <my-container class="roles">
      <my-title :level="3">切换登录角色</my-title>
      <el-row :gutter="14">
        <el-col :span="8">
          <my-description top align="center">
            <template v-slot:title>
              <el-button type="danger" @click="refreshRole('admin')">Admin</el-button>
            </template>
            <my-paragraph>管理员，有增、删、查、改权限</my-paragraph>
          </my-description>
        </el-col>
        <el-col :span="8">
          <my-description top align="center">
            <template v-slot:title>
              <el-button type="warning" @click="refreshRole('user')">User</el-button>
            </template>
            <my-paragraph>普通用户，有查看、新增、更新权限</my-paragraph>
          </my-description>
        </el-col>
        <el-col :span="8">
          <my-description top align="center">
            <template v-slot:title>
              <el-button type="success" @click="refreshRole('guest')">Guest</el-button>
            </template>
            <my-paragraph>访客，有查看权限</my-paragraph>
          </my-description>
        </el-col>
      </el-row>
    </my-container>
    <my-panel size="large" title="演示列表" shadow="never" :border="false" theme="border-left">
      <template v-slot:handle>
        <el-button type="primary" size="small" :disabled="!$access.has('add')">新增</el-button>
        <el-button size="small" @click="reload">刷新列表</el-button>
      </template>
      <my-table ref="table" size="small" :columns="columns" :page-size="5" :loader="loader">
        <template v-slot:state="{row}">
          <el-tag v-if="row.state<3" size="small" type="warning">待审核</el-tag>
          <el-tag v-else size="small" type="success">已审核</el-tag>
        </template>
        <template v-slot:actions="{row}">
          <el-button size="small" :disabled="!$access.has('view')">查看</el-button>
          <el-button size="small" :disabled="!$access.has('update')">编辑</el-button>
          <el-button size="small" type="warning" :disabled="!$access.has('remove')">删除</el-button>
          <el-button size="small" type="primary" :disabled="!($access.is('admin') && row.state < 3)">审核</el-button>
        </template>
      </my-table>
    </my-panel>
    <my-panel size="large"
              title="CRUD"
              shadow="never"
              :border="false"
              :body-style="{padding:0}"
              theme="border-left">
      <template v-slot:handle>
        <el-button type="primary" size="small" :disabled="!$access.has('add')">新增</el-button>
      </template>
      <my-crud :adapter="adapter" :columns="crudColumns" :table-options="{size:'small'}">
        <template v-slot:state="{row}">
          <el-tag v-if="row.state<3" size="small" type="warning">待审核</el-tag>
          <el-tag v-else size="small" type="success">已审核</el-tag>
        </template>
      </my-crud>
    </my-panel>
  </my-wrapper>
</template>

<script>
  import {MyCrud} from '$ui'
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        adapter: MyCrud.adapter.call(this, 'MockForExample'),
        columns: [
          {type: 'index', label: '#'},
          {label: '姓名', prop: 'name'},
          {label: '年龄', prop: 'age'},
          {label: '状态', prop: 'state'},
          {label: '操作', prop: 'actions', width: 300}
        ],
        crudColumns: [
          {type: 'index', label: '#'},
          {label: '姓名', prop: 'name'},
          {label: '年龄', prop: 'age'},
          {label: '状态', prop: 'state'},
          {
            label: '操作',
            type: 'handle',
            width: 350,
            theme: 'button',
            handles: [
              {view: true, access: 'view'},
              {edit: true, access: 'update'},
              {remove: true, access: 'remove'},
              {
                text: '审核',
                type: 'primary',
                icon: 'el-icon-s-check',
                access: row => {
                  return this.$access.is('admin') && row.state < 3
                }
              }
            ]
          }
        ]
      }
    },
    methods: {
      loader(page, limit) {
        return this.fetchMockForExample({
          data: {page, limit}
        })
      },
      reload() {
        this.$refs.table.refresh()
      },
      refreshRole(account) {
        this.login({
          data: {
            account,
            password: '123456'
          }
        }).then(res => {
          this.$access.login(res)
          this.$message.success(`${account}登录成功！`)
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .my-container {
    margin-bottom: 14px;
  }
</style>
