---
title: 系统用户
---
<template>
  <my-wrapper title="系统用户管理列表" fit>
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
                   :render-content="renderContent"
                   @current-change="handleCurrentChange">
          </el-tree>
        </my-panel>
      </el-col>
      <el-col :span="18" :xs="24">
        <my-panel theme="border-left" :fit="fit" shadow="never">
          <template v-slot:title>
            <my-text type="secondary">当前节点：</my-text>
            <el-tag size="small" @click="shrink"><i class="el-icon-s-operation"></i> {{currentTitle}}</el-tag>
          </template>

          <template v-slot:handle>
            <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
            <el-button size="mini" icon="el-icon-bottom-right">迁移</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="batchRemove">批量删除</el-button>
          </template>
          
          <my-crud ref="table" v-loading.body="loading" :fit="fit" :columns="columns"
          :filter="{ collapsible: true, labelWidth: '60px' }"
          :form-options="{ labelWidth: '80px' }"
          :form-dialog="{ width: '50%' }"
          :view-dialog="{ width: '60%', height: '60%' }"
          :adapter="adapter">
            <!-- 定义筛选条件 -->
            <template v-slot:filter>
                <my-input label="用户名" name="userAccount" placeholder="请输入用户名" :props="{clearable: true}"></my-input>
                <my-select label="性别" name="sex" :props="{clearable: true}" dict="sys_user_sex" :loader="loaderDicts"></my-select>
                <my-input label="条件1" name="q1" :props="{clearable: true}" collapsible></my-input>
            </template>

            <!-- 定义新增/修改表单 -->
            <template v-slot:form>
              <div>
                <my-input label="用户名" name="userAccount" :rules="{ required: true }" ></my-input>
                <my-input label="昵称" name="nickname" :rules="{ required: true }" ></my-input>
                <my-input label="手机" name="phone" :rules="{ required: true }" ></my-input>
                <my-input label="邮箱" name="email" :rules="{ required: true }" ></my-input>
                <my-radio label="性别" name="sex" dict="sys_user_sex" :loader="loaderDicts"></my-radio>
                <my-date-picker label="出生日期" name="birthday" :props="{valueFormat:'yyyy-MM-dd'}"></my-date-picker>
                <my-input label="部门" name="deptId" :rules="{ required: true }" ></my-input>
                <my-input label="头像" name="avatar" ></my-input>
                <my-input label="备注" name="remark" ></my-input>
                <my-input label="类型" name="userType" ></my-input>
                <my-radio label="状态" name="disabled" dict="sys_disable_status" :loader="loaderDicts"></my-radio>
              </div>
            </template>

            <!-- 定义详情内容 -->
            <template v-slot:detail="{ row }">
              <my-detail border :column="2">
                <my-detail-item label="用户名">{{ row.userAccount }}</my-detail-item>
                <my-detail-item label="昵称">{{row.nickname}}</my-detail-item>
                <my-detail-item label="手机">{{row.email}}</my-detail-item>
                <my-detail-item label="邮箱">{{row.phone}}</my-detail-item>
              </my-detail>
            </template>
          </my-crud>
        </my-panel>
      </el-col>
    </el-row>
  </my-wrapper>
</template>

<script>
  import SysUserApi from '$my/code/mixin/sys-user-api'
  import SysDeptApi from '$my/code/mixin/sys-dept-api'
  import treeConnect from '$ui/directives/tree-connect'
  import {MyCrud} from '$ui'

  export default {
    mixins: [SysUserApi, SysDeptApi],
    directives: {treeConnect},
    inject: ['myPro'],
    data() {
      return {
        // defaultQuery: this.$route.query,
        query: {},
        adapter: MyCrud.adapter.call(this, 'SysUserApi'),
        columns: [
          {
            type: 'selection'
          },
          {
            label: '账号',
            prop: 'userAccount'
          },
          {
            label: '昵称',
            prop: 'nickname'
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
          type: 'handle',
          label: '操作',
          width: 200,
          handles: [{ view: true }, { edit: true }, { remove: true }]
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
        return this.currentNode.data.name
      }
    },
    created: {},
    mounted() {
      this.setDefaultCurrentNode()
    },
    methods: {
      loader(node, resolve) {
        if (node.data) {
          this.loading = true
          this.fetchSysDeptApi({
            data: {
              pagination: false,
              parentId: node.data.id
            }
          }).then(res => resolve(res))
            .finally(() => {
              this.$refs.table.refresh(1)
              this.loading = false
            })
        } else {
          resolve([{
            name: '根节点',
            id: 0,
            parentId: 'root',
            children: []
          }])
        }
      },
      loaderDicts(model, {name, dict}) {
        return Promise.resolve(this.$store.getters.getDict(dict))
      },
      renderContent(h, { node, data, store }) {
        return (
          <span className="custom-tree-node">
            <span>{data.name}</span>
            <span >
              <el-button size="mini" type="text" on-click={ () => this.append(data) }>编辑</el-button>
            </span>
          </span>);
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
      },
      updateNode(node, data) {

      },
      openAddDialog() {
        this.$refs.table.openAddDialog()
      },
      batchRemove() {
        this.$refs.table.batchRemove()
      }
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
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }

</style>
