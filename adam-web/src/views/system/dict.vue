---
title: 字典管理
---

<template>
  <my-wrapper title="增删查改">
    <template v-slot:extra>与代码生成器结合使用，快速实现通用增删查功能。</template>
      <my-panel title="容器标题" shadow="never" fit>
        <template v-slot:handle>
            <el-button size="mini" icon="el-icon-plus" @click="showSearch=!showSearch">搜索</el-button>
            <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
        </template>
        <my-container v-show="showSearch">
          <my-filter label-width="60px" >
            <my-input label="关键词" name="name"></my-input>
            <my-select label="性别" name="sex" :options="[{label:'男',value:1},{label:'女',value:0}]"></my-select>
          </my-filter>
        </my-container>
        <el-table
         :data="tableData"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
                <div style="float: right;margin-bottom: 10px;">
                  <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
                  <el-button size="mini" type="danger" icon="el-icon-delete">批量删除</el-button>
                </div>
              <el-table
                :data="props.row.subList"
                size="mini"
                max-height="250"
                border
                style="width: 100%">
                <el-table-column
                  type="selection"
                  width="50">
                </el-table-column>
                <el-table-column
                  prop="label"
                  label="参数名称">
                </el-table-column>
                <el-table-column
                  prop="value"
                  label="参数值">
                </el-table-column>
                <el-table-column
                  prop="checked"
                  label="是否默认"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="extend"
                  label="扩展"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="remark"
                  label="字典描述"
                  width="180">
                </el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-link @click="handleEdit(scope.$index, scope.row)" type="primary" icon="el-icon-edit">编辑</el-link>
                    <el-divider direction="vertical"></el-divider>
                    <el-link @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete">删除</el-link>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column
            label="字典名称"
            prop="label">
          </el-table-column>
          <el-table-column
            label="字典编码"
            prop="name">
          </el-table-column>
          <el-table-column
            label="描述"
            prop="remark">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-link @click="handleEdit(scope.$index, scope.row)" type="primary" icon="el-icon-edit">编辑</el-link>
              <el-divider direction="vertical"></el-divider>
              <el-link @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete">删除</el-link>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination ref="pager" background :total="total" layout="total, prev, pager, next" style="padding: 12px 0!important;float: right;"></el-pagination>
      </my-panel>
  </my-wrapper>
</template>

<script>
  import SysDictApi from '$my/code/mixin/sys-dict-api'

  export default {
    mixins: [SysDictApi],
    data() {
      return {
        showSearch: false,
        tableData: [],
        subTableData: [],
        page: 1,
        limit: 10,
        total: 0

      }
    },
    created() {
      this.loaderTable(this.page, this.limit);
    },
    methods: {
      loaderTable(page, limit) {
        return this.fetchSysDictApi({
          data: {
            type: 1,
            page,
            limit
          }
        }).then(res => {
          this.tableData = res.records
          this.total = res.total
        })
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
