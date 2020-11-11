---
title: 个人设置
---
<template>
  <my-form :model="model"
           size="small"
           submit-text="更新基本信息"
           :reset-text="null"
           :on-submit="onSubmit">
    <my-title>基本设置</my-title>
    <el-row :gutter="20">
      <el-col :span="12">
        <my-input label="邮箱" name="email"></my-input>
        <my-input label="昵称" name="name"></my-input>
        <my-input label="个人简介" name="info" :props="{type:'textarea',rows:5}"></my-input>
        <my-select label="国家/地区" name="country" :loader="optionsLoader"></my-select>
        <my-cascader label="城市" name="city" :loader="optionsLoader"></my-cascader>
        <my-input label="街道地址" name="county"></my-input>
        <my-input label="联系电话" name="phone"></my-input>
      </el-col>
      <el-col :span="12">
        <my-form-custom name="avatar">
          <my-description top align="center">
            <my-avatar slot="title" :size="200" :src="model.avatar"></my-avatar>
            <el-upload
              class="avatar-uploader"
              action="随便写个地址"
              :show-file-list="false"
              :before-upload="beforeUpload">
              <el-button icon="el-icon-upload2" size="small">更换头像</el-button>
            </el-upload>
          </my-description>
        </my-form-custom>
      </el-col>
    </el-row>
  </my-form>
</template>

<script>
  import MockForExample from '$my/code/mixin/mock-for-example'

  export default {
    mixins: [MockForExample],
    data() {
      return {
        model: {}
      }
    },
    methods: {
      beforeUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        if (isJPG && isLt2M) {
          // 调上传接口
          this.mockUpload({
            data: {
              name: file.name
            }
          }).then(res => {
            this.model.avatar = res
          })
        }
        return false

      },
      optionsLoader(model, {name}) {
        return this.getOptions({
          data: {
            name
          }
        })
      },
      onSubmit(model) {
        return this.updateMockForExample({
          data: model
        }).then(r => {
          this.$message.success('更新成功！')
        })
      }
    },
    created() {
      this.getMockForExample({
        id: '123'
      }).then(res => {
        this.model = res
      })
    }
  }
</script>

