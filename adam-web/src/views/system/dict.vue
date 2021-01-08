---
title: 字典管理
---

<template>
  <my-wrapper title="增删查改">
    <template v-slot:extra>与代码生成器结合使用，快速实现通用增删查功能。</template>
    <my-panel title="容器标题" shadow="never" fit>
      <template v-slot:handle>
          <el-button size="mini" icon="el-icon-plus" @click="showSearch=!showSearch">搜索</el-button>
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openDialog">新增</el-button>
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
                <el-button size="mini" type="primary" icon="el-icon-plus" @click="openSubDialog(props.row)">新增</el-button>
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
                  <el-link @click="handleEditSub(scope.$index, scope.row)" type="primary" icon="el-icon-edit">编辑</el-link>
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

    <!-- 字典添加 -->
    <my-dialog :visible.sync="dialogVisible" target="body" title="字典添加" width="60%" draggable :footer="false">
      <my-form :model="dictModel" label-width="90px" footer-align="right" :on-submit="handleSubmit">
        <el-row>
          <el-col :span="12">
            <my-input name="label" label="字典名称" :rules="{required:true, message:'内容不能为空'}"></my-input>
          </el-col>
          <el-col :span="12">
            <my-input name="name" label="字典编码" :rules="{required:true, message:'内容不能为空'}"></my-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <my-input name="type" label="字典类型" v-show="false"></my-input>
            <my-input name="remark" label="描述"></my-input>
          </el-col>
          <el-col :span="12">
            <my-input name="extend" label="扩展"></my-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <my-radio label="状态" name="disabled" dict="sys_disable_status" :loader="loaderDicts" :rules="{required:true, message:'内容不能为空'}"></my-radio>
          </el-col>
          <el-col :span="12">
            <my-input-number label="排序" name="sort" :props="{controlsPosition:'right',min:0}" width="300px"></my-input-number>
          </el-col>
        </el-row>
      </my-form>
    </my-dialog>

    <!-- 字典内容添加 -->
    <my-dialog :visible.sync="subDialogVisible" target="body" title="字典项添加" width="60%" draggable :footer="false">
      <my-form :model="dictSubModel" label-width="90px"
                footer-align="right"
                :on-submit="handleSubmitSub">
      <el-row>
        <el-col :span="12">
            <my-input name="label" label="字典名称" :rules="{required:true, message:'内容不能为空'}"></my-input>
          </el-col>
          <el-col :span="12">
            <my-input name="value" label="字典值" :rules="{required:true, message:'内容不能为空'}"></my-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <my-input name="remark" label="描述"></my-input>
          </el-col>
          <el-col :span="12">
            <my-input name="extend" label="扩展"></my-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <my-radio label="状态" name="disabled" dict="sys_disable_status" :loader="loaderDicts" :rules="{required:true, message:'内容不能为空'}"></my-radio>
          </el-col>
          <el-col :span="12">
            <my-input-number label="排序" name="sort" :props="{controlsPosition:'right',min:0}" width="300px"></my-input-number>
          </el-col>
        </el-row>
      </my-form>
    </my-dialog>
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
        dialogVisible: false,
        subDialogVisible: false,
        page: 1,
        limit: 10,
        total: 0,
        dictModel: {},
        dictSubModel: {}

      }
    },
    created() {
      this.loaderTable(this.page, this.limit);
    },
    methods: {
      loaderDicts(model, {name, dict}) {
        return Promise.resolve(this.$store.getters.getDict(dict))
      },
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
      },
      openDialog() {
        this.dialogVisible = true
        // 字典组
        this.dictModel.type = 1
      },
      openSubDialog(row) {
        this.subDialogVisible = true
        // 字典属性
        this.dictSubModel.type = 2
        this.dictSubModel.parentId = row.id
      },
      handleSubmit(model) {
        this.addSysDictApi({
          data: model
        }).then((res) => {
          this.$message('添加成功');
          this.loaderTable(this.page, this.limit);
          this.dialogVisible = false
        })
      },
      handleSubmitSub(model) {
        this.addSysDictApi({
          data: model
        }).then((res) => {
          this.$message('添加成功');
          this.loaderTable(this.page, this.limit);
          this.subDialogVisible = false
        })
      },
      handleEdit(index, row) {
        debugger
        this.dialogVisible = true
        this.dictModel = row
      },
      handleEditSub(index, row) {
        this.subDialogVisible = true
        this.dictSubModel = row
      },
      handleDelete(index, row) {

      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
