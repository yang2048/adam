---
title: 角色管理
---

<template>
  <my-wrapper title="角色管理">
    <template v-slot:extra>
      与代码生成器结合使用，快速实现通用增删查功能。
    </template>
    <el-row :class="{ 'is-fit': fit }" :gutter="14">
      <el-col :span="6" :xs="24">
        <my-panel
          theme="border-left"
          :fit="fit"
          shadow="never"
          title="角色列表"
        >
          <template v-slot:handle>
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-plus"
              circle
            ></el-button>
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              circle
            ></el-button>
          </template>
          <div style="height: calc(70vh); overflow: auto;">
            <div class="role-container" ref="scrollTopList">
              <div
                v-for="r of roles"
                :key="r.id"
                class="role-item"
                :class="{ selected: role == r }"
                @click="changeRole(r)"
              >
                <p class="title">{{ r.name }}</p>
                <my-paragraph ellipsis>{{ r.remark }}</my-paragraph>
              </div>
            </div>
          </div>
        </my-panel>
      </el-col>
      <el-col :span="18" :xs="24">
        <my-panel theme="border-left" :fit="fit" shadow="never">
          <template v-slot:title>
            <my-text type="secondary">当前角色：</my-text>
            <el-tag size="small">{{ currentTitle }}</el-tag>
          </template>
          <template v-slot:handle>
            <el-button
              size="mini"
              icon="el-icon-edit"
              v-show="role != null"
            >
              刷新
            </el-button>
          </template>
          <el-tabs value="0" v-show="role != null">
            <el-tab-pane label="角色编辑" name="0">
              <div style="height: calc(65vh); overflow: auto;">
                <my-form label-width="100px" :model="role">
                  <my-input label="角色名称" name="name" placeholder="角色名称"></my-input>
                  <my-input label="角色描述" name="remark" :props="{type:'textarea'}"></my-input>
                  <my-radio label="角色状态" name="disabled" dict="sys_disable_status" :loader="loaderDicts"></my-radio>
                </my-form>
              </div>
            </el-tab-pane>
            <el-tab-pane label="角色成员" name="1">
              <div>
                <my-table ref="table" toolbar
                  fit
                  mode="scroll"
                  :columns="columns"
                  :loader="loaderTable"
                  max-height="500"
                  :page="1" :page-size="40"
                >
                  <template v-slot:actions>
                    <el-button type="primary" icon="el-icon-plus" size="small" @click="openAddDialog">新增</el-button>
                    <el-button type="danger" icon="el-icon-delete" size="small" @click="batchRemove">批量删除</el-button>
                  </template>
                  <template v-slot:handle>
                    <el-link type="primary">详情</el-link>
                    <el-divider direction="vertical"></el-divider>
                    <el-link type="primary">删除</el-link>
                  </template>
                </my-table>
              </div>
            </el-tab-pane>
            <el-tab-pane label="功能权限" name="2">
              <div style="height: calc(65vh); overflow: auto;">
                <el-tree
                  ref="tree"
                  show-checkbox
                  v-treeConnect
                  :data="menus"
                  node-key="id"
                  :props="defaultProps"
                  highlight-current
                ></el-tree>
              </div>
            </el-tab-pane>
            <el-tab-pane label="数据权限" name="3">
              <div style="height: calc(65vh); overflow: auto;"></div>
            </el-tab-pane>
          </el-tabs>
        </my-panel>
      </el-col>
    </el-row>
  </my-wrapper>
</template>

<script>
  import SysRoleApi from '$my/code/mixin/sys-role-api'
import MockForExample from '$my/code/mixin/mock-for-example'
import treeConnect from '$ui/directives/tree-connect'
// import {create} from '$ui/utils/tree'
export default {
  mixins: [SysRoleApi, MockForExample],
  directives: { treeConnect },
  inject: ['myPro'],
  data() {
    return {
      form: {
        opening: 1
      },
      roles: [],
      role: null,
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'text'
      },
      columns: [
        { type: 'selection' },
        { type: 'index', label: '#' },
        { label: '姓名', prop: 'name' },
        { label: '身份证', prop: 'id' },
        { label: '年龄', prop: 'age' },
        { type: 'handle', prop: 'handle', label: '操作', width: '140px' }
      ],
      query: {keyword: ''}
    }
  },
  computed: {
    fit() {
      return this.myPro.fixed
    },
    currentTitle() {
      if (!this.role) return ''
      return this.role.name
    }
  },
  methods: {
    loaderDicts(model, {name, dict}) {
      return Promise.resolve(this.$store.getters.getDict(dict))
    },
    changeRole(r) {
      this.role = r
      this.getMenus().then((res) => {
        this.menus = res
      })
    },
    loaderTable(page, pageSize) {
      return this.fetchMockForExample({
        data: {
          ...this.query,
          page,
          pageSize
        }
      })
    },
    openAddDialog() {

    },
    batchRemove() {

    },
    // 滚动加载分页
    bindScroll () {
      console.info('-滚动加载分页')
      if(this.$refs.scrollTopList.scrollTop + this.$refs.scrollTopList.clientHeight >= this.$refs.scrollTopList.scrollHeight - 50) {
        console.info('---------滚动加载分页1111')
        // if(this.pageSize > this.total) {
        //   return false
        // }else{
        //   this.pageSize = this.pageSize + 10 // 显示条数新增
        //   this.getpageList() // 请求列表list 接口方法
        // } 
      }else{
        return false
      }
    }
  },
  mounted () {
    setTimeout(function() {
      window.addEventListener('scroll', this.bindScroll(), false)
    }, 3000);
    // this.$refs.scrollTopList.addEventListener('scroll', this.bindScroll(), true)
    // window.addEventListener('scroll', this.bindScroll(), false) // 监听（绑定）滚轮滚动事件
  },
  created() {
    // this.fetchMockForExample().then((res) => {
    //   this.roles = res.list
    //   if (this.roles.length) {
    //     this.role = this.roles[0];
    //   }
    // })
    this.fetchSysRoleApi({
      data: {
      }
    }).then((res) => {
      this.roles = res.list
      
    })
  }
  // destroyed () {
  //   window.removeEventListener('scroll', this.scroll) //  离开页面清除（移除）滚轮滚动事件
  // }
}
</script>

<style lang="scss" scoped>
.role-container {
  padding: 0;
  .role-item {
    padding: 5px 10px;
    &:hover {
      background: #c9edff;
      cursor: pointer;
    }
    &.selected {
      border-left: 3px solid #008cff;
      background: #f0faff;
      cursor: pointer;
    }
    .title {
      font-size: 16px;
      font-weight: bold;
      color: #2d8cf0;
    }
  }
}
</style>
