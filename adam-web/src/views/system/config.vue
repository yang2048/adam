---
title: 系统配置
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
        <el-table border
          :data="tableData"
          style="width: 100%">
          <el-table-column type="expand">
            <template>
                <div style="float: right;margin-bottom: 10px;">
                  <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
                  <el-button size="mini" type="danger" icon="el-icon-delete">批量删除</el-button>
                </div>
              <el-table
                :data="tableData"
                size="mini"
                max-height="250"
                border
                style="width: 100%">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="参数名称"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="address"
                  label="参数值">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="字典描述"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="是否默认"
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
          <el-table-column
            label="字典名称"
            prop="id">
          </el-table-column>
          <el-table-column
            label="字典编码"
            prop="name">
          </el-table-column>
          <el-table-column
            label="描述"
            prop="desc">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-link @click="handleEdit(scope.$index, scope.row)" type="primary" icon="el-icon-edit">编辑</el-link>
              <el-divider direction="vertical"></el-divider>
              <el-link @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete">删除</el-link>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination ref="pager" background :total="100" layout="total, prev, pager, next" style="padding: 12px 0!important;float: right;"></el-pagination>
      </my-panel>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    inject: ['myPro'],
    data() {
      return {
        showSearch: false,
        tableData: [{
          id: '12987122',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987123',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987125',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987126',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }],
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
            label: '地区',
            prop: 'desc'
          },
          {
            type: 'handle',
            label: '操作',
            width: 200
          }
        ]
      }
    },
    methods: {
      loaderTable(page, limit) {
        return this.fetchMockForExample({
          data: {
            page,
            limit
          }
        })
      }

    }
  }
</script>

<style lang="scss" scoped>

</style>
