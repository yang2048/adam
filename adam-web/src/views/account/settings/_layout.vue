<template>
  <my-wrapper title="个人设置" fit>
    <my-tabs :tabs="tabs"
             fit
             :default-active="active"
             :responsive-width="800"
             @change="handleChange">
      <router-view></router-view>
    </my-tabs>

  </my-wrapper>
</template>

<script>
  export default {
    data() {
      return {
        active: this.$route.path,
        tabs: [
          {name: '/account/settings', label: '基本设置'},
          {name: '/account/settings/safe', label: '安全设置'},
          {name: '/account/settings/account', label: '账号绑定'},
          {name: '/account/settings/message', label: '新消息通知'}
        ]
      }
    },
    methods: {
      handleChange(name) {
        this.$router.push(name)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .my-form {
    padding-left: 30px;
  }

</style>
