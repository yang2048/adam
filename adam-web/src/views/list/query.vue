---
title: 查询列表
---
<template>
  <my-wrapper title="查询列表">
    <template v-slot:extra>
      表格查询的复杂示例
    </template>
    <my-container>
      <my-filter label-width="80px" collapsible :model="defaultQuery" @submit="handleQuery">
        <my-input name="q1" label="关键字"></my-input>
        <my-range name="q2" label="时间范围" mode="date" :props="{valueFormat:'yyyy-MM-dd'}"></my-range>
        <my-select name="q3" label="条件三" collapsible :loader="optionsLoader"></my-select>
        <my-select name="q4" label="条件四" collapsible :loader="optionsLoader"></my-select>
        <my-select name="q5" label="条件五" collapsible :loader="optionsLoader"></my-select>
      </my-filter>
    </my-container>
    <my-container>
      <div class="toolbar">
        <el-button size="small" type="primary" icon="el-icon-plus">新建</el-button>
        <el-button size="small" icon="el-icon-upload2">导入</el-button>
        <el-button size="small" icon="el-icon-download">导出</el-button>
        <el-button v-show="selection.length" size="small">批量删除</el-button>
        <el-button v-show="selection.length" size="small">批量审核</el-button>
      </div>
      <my-container class="selection-tip" theme="info" :body-style="{padding:'0 10px'}">
        已选择
        <my-text strong type="primary">{{selection.length}}</my-text>
        项。
        <el-button type="text" @click="clearSelection">清空</el-button>
      </my-container>

      <my-table ref="table"
                stripe
                column-filter
                :columns="tableColumns"
                :pagination="{layout:'total,sizes, ->, prev, pager, next'}"
                size="medium"
                :loader="loader"
                @selection-change="handleSelectionChange">

        <template v-slot:sex="{row}">
          <el-tag v-if="row.sex" size="small" type="primary">男</el-tag>
          <el-tag v-else size="small" type="success">女</el-tag>
        </template>

        <template v-slot:handle>
          <el-link type="primary">编辑</el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link type="primary">删除</el-link>
        </template>

      </my-table>
    </my-container>

  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        columns: [],
        defaultQuery: this.$route.query,
        query: {},
        selection: []
      }
    },
    computed: {
      tableColumns() {
        const columns = this.columns.map((col, index) => {
          return {
            ...col,
            showOverflowTooltip: true,
            display: index < 4 ? 'always' : false,
            filterMethod: col.filters ? this.filterMethod : null
          }
        })
        columns.unshift(
          {
            type: 'selection'
          })

        columns.push({
          label: '操作',
          type: 'handle',
          prop: 'handle',
          width: '120px'
        })
        return columns
      }
    },
    methods: {
      filterMethod(value, row, column) {
        const property = column.property
        return row[property] === value
      },
      optionsLoader() {
        return Promise.resolve([
          {label: '选项一', value: 1},
          {label: '选项二', value: 2}
        ])
      },
      loader(page, limit) {
        return this.fetchMockForExample({
          data: {
            ...this.query,
            page,
            limit
          }
        })
      },
      handleQuery(model) {
        this.query = model
        this.$refs.table.refresh(1)
      },
      handleSelectionChange(selection) {
        this.selection = selection
      },
      clearSelection() {
        this.selection = []
        this.$refs.table.clearSelection()
      }
    },
    created() {
      this.getColumns().then(res => {
        this.columns = res
      })
    }
  }
</script>

<style lang="scss" scoped>
  .toolbar, .selection-tip {
    margin-bottom: 14px;
  }
</style>
