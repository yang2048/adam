<template>
  <my-container>
    <el-divider content-position="left">主题</el-divider>
    <el-radio-group class="two-columns" size="small" v-model="setting.skin">
      <el-radio-button label="default">亮色</el-radio-button>
      <el-radio-button label="dark">深蓝</el-radio-button>
    </el-radio-group>

    <el-divider content-position="left">布局</el-divider>
    <el-radio-group class="three-columns" v-model="setting.layout" size="small">
      <el-radio-button label="sidebar">左右</el-radio-button>
      <el-radio-button label="navbar">上下</el-radio-button>
      <el-radio-button label="both">上下 + 左右</el-radio-button>
    </el-radio-group>

    <el-divider content-position="left">配色</el-divider>
    <el-radio-group class="three-columns" size="small" v-model="setting.color">
      <el-radio-button label="simple">简约</el-radio-button>
      <el-radio-button label="tech">科技</el-radio-button>
      <el-radio-button label="pro">专业</el-radio-button>
    </el-radio-group>


    <el-divider content-position="left">其他设置</el-divider>
    <div class="other">
      <my-float>
        <my-float-item>固定菜单栏</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.fixed"></el-switch>
        </my-float-item>
      </my-float>
      <my-float>
        <my-float-item>开启折叠</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.collapsible"></el-switch>
        </my-float-item>
      </my-float>
      <my-float>
        <my-float-item>开启导航Tab</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.tab"></el-switch>
        </my-float-item>
      </my-float>
      <my-float>
        <my-float-item>开启面包屑</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.breadcrumb"></el-switch>
        </my-float-item>
      </my-float>
      <my-float>
        <my-float-item>彩虹边框</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.rainbow"></el-switch>
        </my-float-item>
      </my-float>
      <my-float>
        <my-float-item>颜色反相</my-float-item>
        <my-float-item float="right">
          <el-switch v-model="setting.invert"></el-switch>
        </my-float-item>
      </my-float>
    </div>
  </my-container>
</template>

<script>

  export default {
    props: {
      config: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    data() {
      return {
        setting: {
          skin: 'default',
          layout: 'sidebar',
          color: 'simple',
          fixed: false,
          collapsible: true,
          tab: false,
          breadcrumb: false,
          rainbow: false,
          invert: false
        }
      }
    },
    watch: {
      config: {
        immediate: true,
        handler(val) {
          this.setting = {...this.setting, ...val}
        }
      },
      setting: {
        deep: true,
        handler(val) {
          this.$emit('change', val)
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/style/_vars.scss";

  .two-columns {
    width: 100%;

    /deep/ {
      .el-radio-button {
        width: 50%;
      }

      .el-radio-button__inner {
        width: 100%;
      }
    }
  }

  .three-columns {
    width: 100%;

    /deep/ {
      .el-radio-button {
        width: 33.33%;
      }

      .el-radio-button__inner {
        width: 100%;
      }
    }
  }

  .my-float {

    padding: 8px;

    &:hover {
      background: $--color-table-stripe;
      border-radius: 4px;
    }
  }

  .el-divider {
    margin-top: 50px;

    &:first-child {
      margin-top: 20px;
    }
  }
</style>


