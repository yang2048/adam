---
title: 基础详情页
---
<template>
  <my-wrapper title="基础详情页">
    <my-container>
      <my-title :level="4">退款申请</my-title>
      <my-detail :column="{xxl:4,xl:3,lg:2,md:2,sm:2,xs:1}">
        <my-detail-item label="取货单号">1000000000</my-detail-item>
        <my-detail-item label="状态">已取货</my-detail-item>
        <my-detail-item label="销售单号">1234123421</my-detail-item>
        <my-detail-item label="子订单">3214321432</my-detail-item>
      </my-detail>
      <el-divider></el-divider>
      <my-title :level="4">用户信息</my-title>
      <my-detail :column="{xxl:4,xl:3,lg:2,md:2,sm:2,xs:1}">
        <my-detail-item label="用户姓名">付小小</my-detail-item>
        <my-detail-item label="联系电话">18100000000</my-detail-item>
        <my-detail-item label="常用快递">菜鸟仓储</my-detail-item>
        <my-detail-item label="取货地址">取货地址浙江省杭州市西湖区万塘路18号</my-detail-item>
        <my-detail-item label="备注">无</my-detail-item>
      </my-detail>
      <el-divider></el-divider>
      <my-title :level="4">表格信息</my-title>
      <my-table :columns="columns" :data="list"></my-table>
    </my-container>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        columns: [
          {label: '姓名', prop: 'name'},
          {label: '地区', prop: 'county'},
          {label: '发布时间', prop: 'created'},
          {label: '年龄', prop: 'age'}
        ],
        list: []
      }
    },
    created() {
      this.fetchMockForExample().then(res => {
        this.list = res.list
      })
    }
  }
</script>
