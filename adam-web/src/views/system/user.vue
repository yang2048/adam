---
title: 系统用户
---
<template>
  <my-wrapper title="树结构列表" fit>
    <template v-slot:extra>对树结构数据进行维护管理</template>
    <el-row :class="{'is-fit':fit}" :gutter="14">
      <el-col :span="6" :xs="24">
        <my-panel theme="border-left" :fit="fit" shadow="never" title="组织机构">
          <el-tree ref="tree"
                   v-treeConnect
                   lazy
                   :load="loader"
                   node-key="id"
                   highlight-current
                   :expand-on-click-node="false"
                   :default-expanded-keys="expandedKeys"
                   :current-node-key="0"
                   @current-change="handleCurrentChange"></el-tree>
        </my-panel>
      </el-col>
      <el-col :span="18" :xs="24">
        <my-panel theme="border-left" :fit="fit" shadow="never">
          <template v-slot:title>
            <my-text type="secondary">当前节点：</my-text>
            <el-tag size="small" @click="shrink"><i class="el-icon-s-operation"></i> {{currentTitle}}</el-tag>
          </template>

          <template v-slot:handle>
            <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
            <el-button size="mini" icon="el-icon-bottom-right">迁移</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete">批量删除</el-button>
          </template>
          <my-container>
            <my-filter label-width="60px" collapsible :model="defaultQuery" @submit="handleQuery">
              <my-input label="关键词" name="name"></my-input>
              <my-select label="性别" name="sex" collapsible :options="[{label:'男',value:1},{label:'女',value:0}]"></my-select>
              <my-input label="条件1" name="q1" collapsible></my-input>
              <my-input label="条件2" name="q2" collapsible></my-input>
              <my-input label="条件3" name="q3" collapsible></my-input>
            </my-filter>
          </my-container>
          <my-table ref="table" v-loading.body="loading" :fit="fit" :columns="columns" :loader="loaderTable" :page="1" :page-size="10">
            <template v-slot:handle>
              <el-link type="primary">详情</el-link>
              <el-divider direction="vertical"></el-divider>
              <el-link type="primary">编辑</el-link>
              <el-divider direction="vertical"></el-divider>
              <el-link type="primary">删除</el-link>
            </template>
          </my-table>
        </my-panel>
      </el-col>
    </el-row>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'
  import treeConnect from '$ui/directives/tree-connect'

  export default {
    mixins: [MockForExample],
    directives: {treeConnect},
    inject: ['myPro'],
    data() {
      return {
        defaultQuery: this.$route.query,
        query: {},
        columns: [
          {
            type: 'selection'
          },
          {
            label: '名称',
            prop: 'name'
          },
          {
            label: '邮箱',
            prop: 'email',
            showOverflowTooltip: true
          },
          {
            label: '手机',
            prop: 'phone',
            showOverflowTooltip: true
          },
          {
            label: '城市',
            prop: 'city',
            showOverflowTooltip: true
          },
          {
            type: 'handle',
            prop: 'handle',
            label: '操作',
            width: '140px'
          }
        ],
        currentNode: null,
        expandedKeys: [0],
        loading: false
      }
    },
    computed: {
      fit() {
        return this.myPro.fixed
      },
      currentTitle() {
        if (!this.currentNode) return ''
        return this.currentNode.data.label
      }
    },
    methods: {
      loaderTable(page, limit) {
        if (!this.currentNode) return []
        this.query.nodeId = this.currentNode.data.id
        return this.fetchMockForExample({
          data: {
            ...this.query,
            page,
            limit
          }
        })
      },
      loader(node, resolve) {
        if (node.data) {
          this.loading = true
          this.getTree({
            data: {
              parentId: node.data.id
            }
          }).then(res => resolve(res))
            .finally(() => {
              this.$refs.table.refresh(1)
              this.loading = false
            })
        } else {
          resolve([{
            label: '根节点',
            id: 0,
            parentId: 'root',
            children: []
          }])
        }
      },
      handleCurrentChange(data, node) {
        this.expandedKeys = [data.id]
        this.currentNode = node
      },
      setDefaultCurrentNode() {
        const key = this.$refs.tree.getCurrentKey()
        this.currentNode = this.$refs.tree.getNode(key)
      },
      handleQuery(model) {
        this.query = model
        this.$refs.table.refresh(1)
      },
      shrink() {
        this.$message('展开~~~');
      }
    },
    mounted() {
      this.setDefaultCurrentNode()
    }
  }
</script>

<style lang="scss" scoped>
  .el-row, .el-col {
    height: 100%;
    min-height: calc(100vh - 200px);

    &.is-fit {
      min-height: 100%;

      .el-col {
        min-height: 100%;
      }
    }
  }

</style>
