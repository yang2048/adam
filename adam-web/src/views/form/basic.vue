---
title: 基础表单
---
<template>
  <my-wrapper title="基础表单">
    <template v-slot:extra>
      表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。
    </template>
    <my-panel theme="border-top" title="发布文章" size="large" shadow="never" :border="false">
      <my-form label-width="100px"
               :model="form"
               :rules="rules"
               @submit="handleSubmit">
        <my-input label="标题" name="title" placeholder="给目标起个名字"></my-input>
        <my-date-picker label="起止日期" name="range" :props="{
          type: 'daterange',
          startPlaceholder: '开始日期',
          endPlaceholder: '结束日期'
        }"></my-date-picker>
        <my-input label="目标描述" name="info" :props="{type:'textarea',rows:5}"></my-input>
        <my-input label="衡量标准" name="standard" :props="{type:'textarea'}"></my-input>
        <my-select label="客户" name="customer" dict="select" :props="{multiple:true}" :loader="loader"></my-select>
        <my-select label="邀评人" name="commenter" dict="select" :loader="loader"></my-select>
        <my-input-number label="权重" name="weight" width="300px"
                         :props="{controlsPosition:'right',min:1, max:10}"></my-input-number>
        <my-radio label="目标公开" name="opening" dict="select" :loader="loader">
        </my-radio>
      </my-form>
      <el-button type="warning" @click="test">警告按钮 这里是stort------->{{this.$store.getters.getDict('select')}}</el-button>
    </my-panel>
  </my-wrapper>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'
  export default {
    mixins: [MockForExample],
    data() {
      return {
        form: {
          opening: 1
        },
        rules: {
          title: {required: true, message: '标题位必填项'}
        }
      }
    },
    methods: {
      loader(model, {name, dict}) {
        return Promise.resolve(this.$store.getters.getDict(dict))
      },
      handleSubmit(model) {
        this.$message.success('提交成功')
      },
      test() {
        const singles = {select: [{ label: '选择166', value: 'a1', desc: '其他值' }, { label: '选择2', value: 'a2', disabled: false, checked: true }, { label: '选择3', value: 'a3' }]}
        this.$store.dispatch('dictInit', singles) // 触发actions对应的change

        this.$message.success('提交测试')
        this.getTest({
          data: {
            josn: 'sd多少都',
            password: '123456'
          }
        }).then(res => {
          console.info(res)
      })
      }
    }
  }
</script>

<style scoped>

</style>
