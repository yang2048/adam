---
title: 首页
access:
- login
---
<template>
  <div class="wrapper">
    <my-pro :fixed="setting.fixed"
            :logo="logo"
            :title="title"
            :mode="setting.layout"
            :menus="menus"
            :menu-props="{router: true, uniqueOpened: true}"
            v-bind="colorTheme"
            :collapsible="setting.collapsible"
            :rainbow="setting.rainbow"
            version="0.0.1"
            :breadcrumb="createBreadcrumb"
            :tab="setting.tab ? createTab : null"
            :document-title="createDocumentTitle">

      <!-- 头部工具按钮 -->
      <template v-slot:actions>
        <UserAction :dropdown-items="dropdown"
                    :username="userInfo.userInfo.nickname"
                    :extra="userInfo.roles[0]"
                    @command="handleUserCommand"
                    :avatar="{theme: 'primary'}"></UserAction>
        <IconAction icon="el-icon-message-solid" :badge="12"></IconAction>
        <IconAction icon="el-icon-info" @click="handleInfo"></IconAction>
        <IconAction icon="el-icon-setting" @click="settingVisible=true"></IconAction>
      </template>

      <!-- 加载子路由页面 -->
      <keep-alive>
        <router-view v-if="$route.meta.keepAlive"></router-view>
      </keep-alive>
      <router-view v-if="!$route.meta.keepAlive"></router-view>

    </my-pro>
    <el-drawer title="设置"
               size="320px"
               append-to-body
               direction="rtl"
               :visible.sync="settingVisible">
      <Setting :config="setting" @change="handleSettingChange"></Setting>
    </el-drawer>
  </div>
</template>

<script>
  import logo from '$ui/assets/logo.png'
  import {MyPro} from '$ui'
  import Setting from '@/components/Setting'
  import {addClass, removeClass} from 'element-ui/lib/utils/dom'
  import skin from '$ui/utils/skin'
  import {isEqual} from '$ui/utils/util'
  import {get, save, LOCAL} from '$ui/utils/storage'
  import MockForExample from '$my/code/mixin/mock-for-example'

  const {IconAction, UserAction} = MyPro
  const SETTING_CACHE_KEY = '__MY_PRO_CONFIG__'
  const defaultSetting = {
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

  export default {
    mixins: [skin(), MockForExample],
    components: {
      MyPro,
      IconAction,
      UserAction,
      Setting
    },
    data() {
      return {
        userInfo: {},
        setting: null,
        logo: logo,
        title: 'SHINE Pro',
        settingVisible: false,
        menus: [],
        dropdown: [
          {
            text: '个人信息',
            command: 'info'
          },
          {
            text: '修改密码',
            command: 'password'
          },
          {
            divided: true,
            text: '注销登录',
            command: 'logout'
          }
        ]
      }
    },
    computed: {
      colorTheme() {
        const {color, layout, skin} = this.setting
        const map = {
          simple: 'light',
          tech: 'dark',
          pro: 'black'
        }
        if (layout === 'sidebar') {
          return {
            sidebarTheme: map[color],
            navbarTheme: skin === 'dark' ? 'dark' : 'light'
          }
        }
        if (layout === 'navbar') {
          return {
            sidebarTheme: 'light',
            navbarTheme: map[color]
          }
        }

        if (layout === 'both') {
          return {
            navbarTheme: map[color],
            sidebarTheme: skin === 'dark' ? 'dark' : 'light'
          }
        }

        return {
          navbarTheme: 'light',
          sidebarTheme: 'light'
        }

      }
    },
    watch: {
      setting(val) {
        this.changeTheme(val.skin)
        this.invert(val.invert)
      }
    },
    methods: {
      // 页面标题构建函数，可根据匹配的路由返回响应的标题名称
      createDocumentTitle(matched) {
        return matched.meta.title
          ? `${matched.meta.title} - 后台管理系统 1.0`
          : '后台管理系统 1.0'
      },
      handleUserCommand(cmd) {
        switch (cmd) {
          case 'info':
            this.$router.push('/account/center')
            break
          case 'password':
            this.$router.push('/account/settings')
            break
          case 'logout':
            this.logout().then(r => {
              this.$access.logout()
            })
            break
        }
      },
      handleSettingChange(setting) {
        if (isEqual(setting, this.setting)) return
        this.setting = {...setting}
        save(SETTING_CACHE_KEY, setting, LOCAL)

      },
      invert(val) {
        val ? addClass(document.body, 'body-invert') : removeClass(document.body, 'body-invert')
      },
      createBreadcrumb() {
        if (!this.setting.breadcrumb) return null
        return this.$route.matched
          .filter(n => n.meta.title)
          .map(n => {
            return {
              label: n.meta.title,
              to: n.path || '/'
            }
          })
      },
      createTab(fullPath, matched) {
        if (!this.setting.tab) return null
        if (fullPath && matched) {
          const {icon, title, tab} = matched.meta
          if (title || tab) {
            return {
              label: tab || title,
              value: fullPath,
              path: matched.path,
              icon: icon,
              closable: fullPath !== '/dashboard/analysis'
            }
          }
          return null
        } else {
          // 缺省首页
          return {
            label: '分析页',
            value: '/dashboard/analysis',
            closable: false
          }
        }
      },
      handleInfo() {
        window.location.href = 'http://newgateway.gitee.io/my/'
      }
    },
    created() {
      this.setting = get(SETTING_CACHE_KEY, LOCAL) || defaultSetting
      this.userInfo = this.$access.get()
      this.getMenus().then(res => {
        this.menus = res
      })
    }
  }
</script>

<style lang="scss">

  body {
    &.body-invert {
      filter: invert(90%) brightness(1.2) hue-rotate(180deg);
    }

    &.__MY_SKIN__dark {
      .my-chart {
        filter: invert(90%) brightness(1.2) hue-rotate(180deg);
      }
    }
  }
</style>
