<template>
  <my-micro-app></my-micro-app>
</template>

<script>
  export default {
    inject: {
      myPro: {default: null},
      masterApp: {default: null}
    },
    watch: {
      $route: {
        immediate: true,
        handler() {
          this.addTab()
        }
      }
    },
    methods: {
      addTab() {
        let label = '404'
        const matchApp = this.masterApp?.matchApp
        if (matchApp) {
          label = matchApp.props?.title || '子应用'
        }
        this.myPro.addTab({
          label,
          value: this.$route.path,
          path: '/suit',
          closable: true
        })
      }
    }
  }
</script>
