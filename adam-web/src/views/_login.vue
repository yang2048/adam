<template>
  <div class="bg">
    <my-login :logo="logo"
              title="演示系统"
              center
              :login="loginHandler"
              @success="handleSuccess">
      <div slot="footer">
        账号：admin / user / guest <br>
        密码： 123456
      </div>
    </my-login>
    <Wave width="100%" height="100%"></Wave>
  </div>
</template>

<script>
  import logo from '$ui/assets/logo.png'
  import skin from '$ui/utils/skin'
  import sysUserApi from '$my/code/mixin/sys-user-api'

  export default {
    mixins: [skin(), sysUserApi],
    components: {
      Wave: () => import('$ui/components/my-wave')
    },
    props: {
      logo: {
        type: String,
        default: logo
      },
      // url查询参数名称
      urlQueryName: {
        type: String,
        default: 'url'
      },
      // 默认登录成功跳转页面地址
      defaultUrl: {
        type: String,
        default: '/'
      }
    },
    methods: {
      loginHandler({account, password}) {
        return this.login({
          data: {
            account,
            password
          }
        })
      },
      handleSuccess(res) {
        // 在此记录登录信息和跳转
        if (!this.$access) return
        this.$access.login(res)
        this.redirect()
      },
      isSelf(url) {
        const {fullPath, path} = this.$route
        return url === fullPath || url === path || url === window.location.href
      },
      redirect() {
        const redirectUrl = this.$route.query[this.urlQueryName] || this.defaultUrl
        const path = decodeURIComponent(redirectUrl)
        // 如果准备要跳转的地址就是页面本身，不需要处理
        if (this.isSelf(path)) return
        const regex = /^http(s)?:\/\/.+/
        if (regex.test(path)) {
          window.location.href = path
        } else {
          this.$router.push(path)
        }
      }
    },
    created() {
      // 如果已经是登录状态，跳转到首页或指定页面
      if (this.$access && this.$access.isLogin()) {
        this.redirect()
      }
    }
  }
</script>

<style lang="scss" scoped>
  .bg {
    position: relative;
    width: 100%;
    height: 100vh;
    background: url("~$ui/assets/bg/01.jpg") center center;
  }

  .my-login {
    z-index: 3;
  }

  .my-wave {
    position: absolute;
    left: 0;
    bottom: 0;
  }
</style>
