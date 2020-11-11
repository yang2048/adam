---
title: 标准列表
---
<template>
  <my-wrapper title="标准列表" fit>
    <my-container class="data-stat" :border="false">
      <el-row>
        <el-col :span="8">
          <span class="title">我的代办</span>
          <span class="info">8个任务</span>
        </el-col>
        <el-col :span="8">
          <span class="title">本周任务平均处理时间</span>
          <span class="info">32分钟</span>
        </el-col>
        <el-col :span="8">
          <span class="title">本周完成任务数</span>
          <span class="info">24个任务</span>
        </el-col>
      </el-row>
    </my-container>
    <my-container :border="false">
      <my-table ref="table"
                toolbar
                stripe
                title="基本列表"
                :columns="columns"
                :loader="loader"
                :show-header="false">
        <template v-slot:actions>
          <my-form class="filter-form"
                   :model="query"
                   @change="handleFilterChange"
                   inline
                   :footer="null"
                   size="small">
            <my-radio name="state"
                      button
                      :options="[{label:'全部',value:1},{label:'进行中',value:2},{label:'等待中',value:3}]"></my-radio>
            <my-input name="keyword" placeholder="输入关键字" :props="{prefixIcon:'el-icon-search', clearable:true}">
            </my-input>
          </my-form>
        </template>

        <template v-slot:prepend>
          <div class="add-btn" @click="handleAdd"><i class="el-icon-plus"></i>添加</div>
        </template>

        <template v-slot:name="{row}">
          <div class="data-item">
            <my-avatar :size="50" shape="square">{{row.name.substring(0,1)}}</my-avatar>
            <h4>{{row.name}}</h4>
            <p>{{row.title}}</p>
          </div>
        </template>
        <template v-slot:county="{row}">
          地区 <br> {{row.county}}
        </template>
        <template v-slot:created="{row}">
          发布时间 <br> {{row.created}}
        </template>
        <template v-slot:age="{row}">
          <el-progress :percentage="row.age"></el-progress>
        </template>
        <template v-slot:handle="{row}">
          <el-button type="text" @click="handleEdit(row)">编辑</el-button>
          <el-divider direction="vertical"></el-divider>
          <el-button type="text" @click="handleRemove(row)">删除</el-button>
        </template>

      </my-table>
    </my-container>
    <my-dialog :title="dialogTitle"
               :footer="null"
               target="body"
               modal
               width="50%"
               :visible.sync="visible">
      <my-form size="medium"
               label-width="100px"
               :on-submit="handleSubmit"
               :rules="rules"
               :model="form">
        <my-input label="任务名称" name="title"></my-input>
        <my-date-picker label="开始时间" name="created"
                        :props="{type:'datetime',valueFormat:'yyyy-MM-dd hh:mm:ss'}"></my-date-picker>
        <my-select label="任务负责人" name="name" :options="[{label:'选项一', value:1}]"></my-select>
        <my-input label="任务描述" name="info" :props="{type:'textarea', rows:5}"></my-input>
      </my-form>
    </my-dialog>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        query: {
          state: 1,
          keyword: ''
        },
        columns: [
          {label: '姓名', prop: 'name', minWidth: 200},
          {label: '地区', prop: 'county', minWidth: 150},
          {label: '发布时间', prop: 'created'},
          {label: '年龄', prop: 'age'},
          {label: '操作', prop: 'handle', width: 100}
        ],
        form: {},
        rules: {
          title: {required: true}
        },
        visible: false,
        isEditModel: false
      }
    },
    computed: {
      dialogTitle() {
        return this.isEditModel ? '编辑任务' : '添加任务'
      }
    },
    methods: {
      handleFilterChange(model) {
        clearTimeout(this.refreshTimer)
        this.refreshTimer = setTimeout(() => {
          this.$refs.table.refresh(1)
        }, 300)
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
      handleAdd() {
        this.form = {}
        this.isEditModel = false
        this.visible = true
      },
      handleEdit(row) {
        this.form = row
        this.isEditModel = true
        this.visible = true
      },
      handleRemove(row) {
        this.$confirm('确认删除该条数据?', '提示', {type: 'warning'})
          .then(ok => {
            this.$message.success('删除成功')
            this.removeMockForExample({
              id: row.id
            }).then(r => {
              this.$refs.table.refresh()
            })
          })
          .catch(e => e)
      },
      handleSubmit(model) {
        const handler = this.isEditModel
          ? this.updateMockForExample({
            data: model
          })
          : this.addUser({
            data: model
          })
        return handler.then(res => {
          this.visible = false
          this.$refs.table.refresh(this.isEditModel ? null : 1)
          return res
        }).catch(e => e)
      }
    },
    beforeDestroy() {
      clearTimeout(this.refreshTimer)
    }
  }
</script>


<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .data-stat {
    .el-col {
      text-align: center;
      border-left: 1px solid $--color-divider;

      &:first-child {
        border-left: none;
      }
    }

    .title {
      color: $--color-placeholder;
      display: block;
      margin-bottom: 8px;
    }

    .info {
      color: $--color-primary-text;
      display: block;
      font-size: $--font-size-large;
      padding: 5px;
    }
  }

  .filter-form {
    /deep/ {
      .el-form-item {
        margin-bottom: 0 !important;
      }

      .my-radio {
        transform: translateY(-2px);
      }
    }
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

  .data-item {
    .my-avatar {
      float: left;
    }

    h4, p {
      margin: 0;
      padding: 0 0 0 60px;
    }

    h4 {
      color: $--color-primary-text;
    }
  }
</style>
