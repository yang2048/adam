---
title: 系统配置
---
<template>
  <my-wrapper title="增删查改">
    <template v-slot:extra>与代码生成器结合使用，快速实现通用增删查功能。</template>
    <my-crud ref="crud"
             title="数据表格"
             :filter="{collapsible:true,labelWidth:'60px'}"
             :table-options="{border:true, stripe:true, rowKey:'guid'}"
             :form-options="{labelWidth:'80px'}"
             :form-dialog="{width:'50%'}"
             :view-dialog="{width:'60%', height:'60%'}"
             :columns="columns"
             :adapter="adapter">
      <!-- 定义筛选条件 -->
      <template v-slot:filter>
        <my-input label="姓名" name="name"></my-input>
        <my-select label="性别" name="sex" :options="[{label:'男',value:1},{label:'女',value:0}]"></my-select>
        <my-input label="条件1" name="q1" collapsible></my-input>
        <my-input label="条件2" name="q2" collapsible></my-input>
        <my-input label="条件3" name="q3" collapsible></my-input>
      </template>

      <!-- 定义工具条 -->
      <template v-slot:toolbar>
        <el-button type="primary" icon="el-icon-plus" size="small" @click="openAddDialog">新增</el-button>
        <el-button size="small" icon="el-icon-upload2">导入</el-button>
        <el-button size="small" icon="el-icon-download">导出</el-button>
        <el-button type="danger" icon="el-icon-delete" size="small" @click="batchRemove">批量删除</el-button>
      </template>

      <!-- 定义新增/修改表单 -->
      <template v-slot:form>
        <div>
          <my-input label="姓名" name="name" :rules="{required:true}"></my-input>
          <my-date-picker label="出生日期" name="date" :props="{valueFormat:'yyyy-MM-dd'}"></my-date-picker>
        </div>
      </template>

      <!-- 定义详情内容 -->
      <template v-slot:detail="{row}">
        <my-detail border :column="2">
          <my-detail-item label="姓名">{{row.name}}</my-detail-item>
          <my-detail-item label="身份证">{{row.id}}</my-detail-item>
          <my-detail-item label="出生日期">{{row.date}}</my-detail-item>
          <my-detail-item label="地区">{{row.county}}</my-detail-item>
          <my-detail-item label="简介">{{row.info}}</my-detail-item>
        </my-detail>
      </template>
    </my-crud>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'
  import {MyCrud} from '$ui'

  export default {
    mixins: [MockForExample],
    inject: ['myPro'],
    data() {
      return {
        adapter: MyCrud.adapter.call(this, 'MockForExample'),
        columns: [
          {
            type: 'selection'
          },
          {
            label: '姓名',
            prop: 'name'
          },
          {
            label: '身份证',
            prop: 'id'
          },
          {
            label: '性别',
            prop: 'sex'
          },
          {
            label: '出生日期',
            prop: 'date'
          },
          {
            label: '地区',
            prop: 'county'
          },
          {
            type: 'handle',
            label: '操作',
            width: 200,
            handles: [
              {view: true},
              {edit: true},
              {remove: true}
            ]
          }
        ]
      }
    },
    methods: {
      openAddDialog() {
        this.$refs.crud.openAddDialog()
      },
      batchRemove() {
        this.$refs.crud.batchRemove()
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
