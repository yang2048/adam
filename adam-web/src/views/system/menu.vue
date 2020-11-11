---
title: 菜单路由管理
---

<template>
  <my-wrapper title="增删查改">
    <template v-slot:extra>
      与代码生成器结合使用，快速实现通用增删查功能。
    </template>
    <my-crud
      ref="crud"
      title="数据表格"
      :filter="{ collapsible: true, labelWidth: '60px' }"
      :table-options="{ border: true, stripe: true, rowKey: 'index' }"
      :form-options="{ labelWidth: '80px' }"
      :form-dialog="{ width: '50%' }"
      :view-dialog="{ width: '60%', height: '60%' }"
      :columns="columns"
      :adapter="adapter"
    >
      <!-- 定义筛选条件 -->
      <template v-slot:filter>
        <my-input label="名称" name="text"></my-input>
        <my-input label="路径" name="index"></my-input>
      </template>

      <!-- 定义工具条 -->
      <template v-slot:toolbar>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small"
          @click="openAddDialog"
        >
          新增
        </el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="small"
          @click="batchRemove"
        >
          批量删除
        </el-button>
      </template>

      <!-- 定义新增/修改表单 -->
      <template v-slot:form>
        <div>
          <my-input
            label="名称"
            name="text"
            :rules="{ required: true }"
          ></my-input>
          <my-input
            label="路径"
            name="index"
            :rules="{ required: true }"
          ></my-input>
          <my-input
            label="图标"
            name="icon"
          ></my-input>
        </div>
      </template>

      <!-- 定义详情内容 -->
      <template v-slot:detail="{ row }">
        <my-detail border :column="1">
          <my-detail-item label="姓名">{{ row.text }}</my-detail-item>
          <my-detail-item label="路径"><el-tag size="small" type="primary">{{row.index}}</el-tag></my-detail-item>
          <my-detail-item label="图标"><i :class="row.icon"></i></my-detail-item>
        </my-detail>
      </template>

      <!-- 自定义列内容 -->
      <template v-slot:icon="{ row }">
        <i :class="row.icon"></i>
      </template>
      <template v-slot:index="{row}">
        <el-tag size="small" type="primary">{{row.index}}</el-tag>
      </template>

    </my-crud>
  </my-wrapper>
</template>

<script>
import MockForExample from '$my/code/mixin/mock-for-example'

export default {
  mixins: [MockForExample],
  inject: ['myPro'],
  data() {
    return {
      adapter: {
        get: this.getAdapter,
        fetch: this.fetchAdapter,
        add: this.addAdapter,
        update: this.updateAdapter,
        remove: this.removeAdapter,
        batch: this.batchAdapter
      },
      columns: [
        {
          type: 'selection'
        },
        {
          label: '名称',
          prop: 'text'
        },
        {
          label: '路径',
          prop: 'index'
        },
        {
          label: '图标',
          prop: 'icon'
        },
        {
          type: 'handle',
          label: '操作',
          width: 200,
          handles: [{ view: true }, { edit: true }, { remove: true }]
        }
      ]
    }
  },
  methods: {
    getAdapter({ row }) {
      console.info('=========getAdapter=========' + row.icon)
      return this.getMenusDemo()
    },
    fetchAdapter(query) {
      console.info('=========fetchAdapter=========' + query)
      return this.getMenusDemo({data: {query}})
    },
    addAdapter({row, index}) {
      console.info('=========addAdapter=========')
      return null
    },
    updateAdapter({row, index}) {
      return null
    },
    removeAdapter({row, index}) {
      return null
    },
    batchAdapter({rows, indexes}) {
      return null
    },
    openAddDialog() {
      this.$refs.crud.openAddDialog()
    },
    batchRemove() {
      this.$refs.crud.batchRemove()
    }
  }
}
</script>

<style lang="scss" scoped></style>
