---
title: 高级表单
---
<template>
  <my-wrapper class="advance-from" ref="wrapper" title="高级表单">
    <template v-slot:extra>
      高级表单常见于一次性输入和提交大批量数据的场景。
    </template>
    <my-form :footer="null"
             ref="form"
             size="small"
             :model="form"
             :rules="rules"
             @submit="handleSubmit"
             @change="handleFormChange">

      <my-panel theme="border-left" title="仓库管理" shadow="never" :border="false">
        <el-row :gutter="24">
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field1" label="仓库名"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field2" label="仓库域名">

            </my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-select name="field3" label="仓库管理员" :loader="loader"></my-select>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field4" label="审批人" :loader="loader"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field5" label="生效日期"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field6" label="仓库类型"></my-input>
          </el-col>
        </el-row>
      </my-panel>
      <my-panel theme="border-left" title="任务管理" shadow="never" :border="false">
        <el-row :gutter="24">
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field7" label="任务名"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field8" label="任务描述"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-select name="field9" label="执行人" :loader="loader"></my-select>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field10" label="责任人" :loader="loader"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field11" label="生效日期"></my-input>
          </el-col>
          <el-col :span="6" :xs="24" :sm="12" :md="8" :lg="6">
            <my-input name="field12" label="任务类型"></my-input>
          </el-col>
        </el-row>
      </my-panel>
      <my-panel theme="border-left" title="成员管理" shadow="never" :border="false">
        <my-table class="table-form" :columns="columns" :data="form.members">
          <template v-slot:name="{$index}">
            <my-input :name="`members.${$index}.name`"
                      size="small"
                      :rules="{required:true}"
                      class="table-input"></my-input>
          </template>
          <template v-slot:no="{$index}">
            <my-input :name="`members.${$index}.no`"
                      :rules="{required:true}"
                      size="small"
                      class="table-input"></my-input>
          </template>
          <template v-slot:dept="{$index}">
            <my-select :name="`members.${$index}.dept`"
                       size="small"
                       :rules="{required:true}"
                       class="table-input"
                       :loader="loader"></my-select>
          </template>
          <template v-slot:handle="{$index}">
            <el-button size="small" @click="removeMember($index)">删除</el-button>
          </template>
          <template v-slot:append>
            <div class="add-btn" @click="addMember"><i class="el-icon-plus"></i>新增成员</div>
          </template>
        </my-table>
      </my-panel>
    </my-form>


    <my-container theme="info" border shadow class="form-footer">
      <el-button type="primary" @click="submit">提交</el-button>
    </my-container>


  </my-wrapper>
</template>

<script>
  import {cloneDeep} from '$ui/utils/util'

  export default {
    data() {
      return {
        columns: [
          {label: '成员姓名', prop: 'name'},
          {label: '工号', prop: 'no'},
          {label: '所属部门', prop: 'dept'},
          {label: '操作', prop: 'handle'}
        ],
        form: {
          field1: '测试名称',
          members: [
            {
              name: 'abc',
              no: 'no1232',
              dept: 1
            }
          ]
        },
        rules: {
          field1: {required: true}
        }

      }
    },
    methods: {
      loader(model, {name, dict}) {
        const options = [
          {label: '选项一', value: 1},
          {label: '选项二', value: 2},
          {label: '选项三', value: 3}
        ]
        return Promise.resolve(options)
      },
      getTarget() {
        return this.$refs.wrapper.$el.parentNode
      },
      addMember() {
        this.form.members.push({
          name: '',
          no: '',
          dept: ''
        })
        setTimeout(() => {
          console.log(this.form)
        }, 200)
      },
      removeMember(index) {
        this.form.members.splice(index, 1)
      },
      handleFormChange(model) {
        this.form = cloneDeep(model)
      },
      submit() {
        this.$refs.form.submit()
      },
      handleSubmit(model) {
        console.log(model)
        this.$message.success('提交成功')
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .advance-from {
    position: relative;
    padding-bottom: 70px;
  }

  .form-footer {
    text-align: right;
    display: block;
    background: $--background;
    position: fixed;
    width: 100%;
    bottom: 0;
    left: 0;
    padding-right: 30px;
    z-index: 1;
    opacity: 0.85;
  }

  .add-btn {
    border: 1px dashed $--color-divider;
    padding: 5px;
    text-align: center;
    margin: 14px 0;
    cursor: pointer;

    i {
      margin-right: 10px;
    }

    &:hover {
      border-color: $--link-hover-color;
      color: $--color-primary;
    }
  }

  .table-input {
    margin-bottom: 0 !important;
  }

  .table-form {
    /deep/ {
      .cell {
        overflow: visible;
      }
    }
  }


</style>
