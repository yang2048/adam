---
title: 分步表单
keepAlive: true
---
<template>
  <my-wrapper title="分布表单" fit>
    <template v-slot:extra>
      将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
    </template>

    <my-container>
      <el-steps :active="active" finish-status="success" simple>
        <el-step title="填写转账信息"></el-step>
        <el-step title="确认转账信息"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>

      <my-form v-if="active===1"
               :model="form"
               :rules="rules1"
               label-width="100px"
               submit-text="下一步"
               :reset-text="null"
               @submit="handleSubmit">
        <my-input name="to" label="付款账户"></my-input>
        <my-input name="from" label="收款账户"></my-input>
        <my-input name="name" label="收款人姓名"></my-input>
        <my-input name="money" label="转账金额">
          <template slot="prepend">￥</template>
        </my-input>
      </my-form>

      <my-form v-if="active===2"
               label-width="100px"
               :reset-text="null"
               :model="form"
               :rules="rules2"
               @submit="handleSubmit">
        <my-input name="password" label="支付密码" :props="{type:'password'}"></my-input>
        <template slot="actions">
          <el-button @click="active=1">上一步</el-button>
        </template>
      </my-form>

      <my-result v-if="active===3"
                 status="success"
                 :model="form"
                 title="操作成功！"
                 subtitle="预计两小时内到账">
        <template v-slot:extra>
          <el-button type="primary" @click="restart">再帐一笔</el-button>
          <el-button>查看账单</el-button>
        </template>
      </my-result>

    </my-container>

  </my-wrapper>
</template>

<script>
  export default {
    data() {
      return {
        active: 1,
        form: {},
        rules1: {
          to: {required: true},
          from: {required: true},
          name: {required: true},
          money: {required: true}
        },
        rules2: {
          password: {required: true}
        }
      }
    },
    methods: {
      handleSubmit(model) {
        this.form = {
          ...this.form,
          ...model
        }
        ++this.active
      },
      restart() {
        this.form = {}
        this.active = 1
      }
    }
  }
</script>

<style lang="scss" scoped>
  .my-form {
    max-width: 600px;
    margin: 30px auto;
  }
</style>
