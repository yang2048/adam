// 模拟登录
export function mockForExampleLogin(Mock, url, query, options) {
  const {account, password} = query
  const users = ['admin', 'user', 'guest']
  const psw = '123456'
  const isValid = users.includes(account) && password === psw
  const canMap = {
    admin: ['view', 'add', 'update', 'remove'],
    user: ['view', 'update', 'add'],
    guest: ['view']
  }
  return {
    code: isValid ? 0 : -1,
    data: isValid
      ? Mock.mock({
        id: '@guid',
        name: account.toUpperCase() + '测试',
        token: '@guid',
        roles: [account],
        can: canMap[account]
      })
      : null,
    msg: isValid
      ? ''
      : '账号或密码不正确'
  }
}

//  模拟注销登录
export function mockForExampleLogout(Mock, url, query, options) {
  return {
    code: 0,
    data: true
  }
}

// 模拟获取数据字段列表接口
export function mockForExampleGetColumns(Mock, url, query, options) {
  const columns = [
    {
      label: '姓名',
      prop: 'name'
    },
    {
      label: '身份证',
      prop: 'id'
    },
    {
      label: '性别',
      prop: 'sex',
      filters: [{text: '男', value: 1}, {text: '女', value: 0}]
    },
    {
      label: '出生日期',
      prop: 'date',
      sortable: true
    },
    {
      label: '地区',
      prop: 'county'
    },
    {
      label: '介绍',
      prop: 'info'
    },
    {
      label: '创建时间',
      prop: 'created'
    }
  ]
  return {
    code: 0,
    data: columns
  }
}

// 模拟获取导航菜单接口
export function mockForExampleGetMenus(Mock, url, query, options) {
  const menus = [
    {
      icon: 'el-icon-s-home',
      text: '仪表盘',
      index: '/',
      children: [
        {
          icon: 'el-icon-document',
          text: '分析页',
          index: '/dashboard/analysis'
        },
        {
          icon: 'el-icon-document',
          text: '监控页',
          index: '/dashboard/monitor'
        },
        {
          icon: 'el-icon-document',
          text: '工作台',
          index: '/dashboard/workplace'
        }
      ]
    },
    {
      icon: 'el-icon-edit-outline',
      text: '系统管理',
      index: '/system',
      children: [
        {
          icon: 'el-icon-document',
          text: '系统用户 ',
          index: '/system/user'
        },
        {
          icon: 'el-icon-document',
          text: '角色管理',
          index: '/system/role'
        },
        {
          icon: 'el-icon-document',
          text: '路由菜单',
          index: '/system/menu'
        },
        {
          icon: 'el-icon-document',
          text: '系统配置',
          index: '/system/config'
        },
        {
          icon: 'el-icon-document',
          text: '系统字典',
          index: '/system/dict'
        }
      ]
    },
    {
      icon: 'el-icon-edit-outline',
      text: '表单页面',
      index: '/form',
      children: [
        {
          icon: 'el-icon-document',
          text: '基础表单',
          index: '/form/basic'
        },
        {
          icon: 'el-icon-document',
          text: '分步表单',
          index: '/form/step'
        },
        {
          icon: 'el-icon-document',
          text: '高级表单',
          index: '/form/advanced'
        }
      ]
    },
    {
      icon: 'el-icon-s-grid',
      text: '列表页面',
      index: '/list',
      children: [
        {
          icon: 'el-icon-document',
          text: '搜索列表',
          index: '/list/search',
          children: [
            {
              icon: 'el-icon-document',
              text: '搜索列表(文章)',
              index: '/list/search/article'
            },
            {
              icon: 'el-icon-document',
              text: '搜索列表(项目)',
              index: '/list/search/project'
            },
            {
              icon: 'el-icon-document',
              text: '搜索列表(应用)',
              index: '/list/search/application'
            }
          ]
        },
        {
          icon: 'el-icon-document',
          text: '标准列表',
          index: '/list/basic'
        },
        {
          icon: 'el-icon-document',
          text: '卡片列表',
          index: '/list/card'
        },
        {
          icon: 'el-icon-document',
          text: '查询列表',
          index: '/list/query'
        },
        {
          icon: 'el-icon-document',
          text: '增删查改',
          index: '/list/crud'
        },
        {
          icon: 'el-icon-document',
          text: '树结构列表',
          index: '/list/tree'
        }
      ]
    },
    {
      text: '详情页面',
      index: '/profile',
      icon: 'el-icon-monitor',
      children: [
        {
          icon: 'el-icon-document',
          text: '基础详情页',
          index: '/profile/basic'
        },
        {
          icon: 'el-icon-document',
          text: '高级详情页',
          index: '/profile/advanced'
        }
      ]
    },
    {
      text: '结果页面',
      index: '/result',
      icon: 'el-icon-circle-check',
      children: [
        {
          icon: 'el-icon-document',
          text: '成功页',
          index: '/result/success'
        },
        {
          icon: 'el-icon-document',
          text: '失败页',
          index: '/result/fail'
        }
      ]
    },
    {
      text: '异常页面',
      index: '/exception',
      icon: 'el-icon-warning-outline',
      children: [
        {
          icon: 'el-icon-document',
          text: '403',
          index: '/exception/403'
        },
        {
          icon: 'el-icon-document',
          text: '404',
          index: '/exception/404'
        },
        {
          icon: 'el-icon-document',
          text: '500',
          index: '/exception/500'
        }
      ]
    },
    {
      text: '个人页面',
      index: '/account',
      icon: 'el-icon-s-custom',
      children: [
        {
          icon: 'el-icon-document',
          text: '个人中心',
          index: '/account/center'
        },
        {
          icon: 'el-icon-document',
          text: '个人设置',
          index: '/account/settings'
        }
      ]
    },
    {
      icon: 'el-icon-menu',
      text: '微应用',
      index: '/suit',
      children: [
        {
          icon: 'el-icon-document',
          text: '组件主题测试',
          index: '/suit'
        }
      ]
    },
    {
      icon: 'el-icon-s-check',
      text: '权限控制',
      index: '/access',
      children: [
        {
          icon: 'el-icon-document',
          text: '视图鉴权',
          index: '/access'
        },
        {
          icon: 'el-icon-document',
          text: '路由鉴权(admin)',
          index: '/access/admin'
        },
        {
          icon: 'el-icon-document',
          text: '路由鉴权(user)',
          index: '/access/user'
        },
        {
          icon: 'el-icon-document',
          text: '路由鉴权(guest)',
          index: '/access/guest'
        }
      ]
    }
  ]
  return {
    code: 0,
    data: menus
  }
}

// 模拟获取树结构数据
export function mockForExampleGetTree(Mock, url, query, options) {
  const {parentId, count} = query
  const total = count || 8
  const root = Mock.mock({
    [`list|${total}`]: [{
      id: '@guid',
      parentId: function () {
        return parentId || null
      },
      label: '@ctitle',
      value: '@guid',
      created: '@date(yyyy-MM-dd hh:mm:ss)',
      isLeaf: false
    }]
  })

  return {
    code: 0,
    data: root.list
  }

}

// 模拟选项数据
export function mockForExampleGetOptions(Mock, url, query, options) {
  const total = 50
  const rootCount = 6
  let index = 0
  const root = Mock.mock({
    [`list|${total}`]: [{
      id: function () {
        return ++index
      },
      parentId: function () {
        return this.id > rootCount ? Math.floor(Math.random() * this.id) : null
      },
      label: '@ctitle',
      value: function () {
        return this.id
      }
    }]
  })

  return {
    code: 0,
    data: root.list
  }
}

// 模拟上传文件
export function mockForExampleUpload(Mock, url, query, options) {
  const root = Mock.mock({
    image: '@dataImage'
  })
  return {
    code: 0,
    data: root.image
  }
}

export function mockGetTest(Mock, url, query, options) {
  return {
    code: 0,
    data: 'root.image'
  }
}

export function mockGetMenusDemo (Mock, url, query, options) {
  const menus = [
    {
      icon: 'el-icon-s-home',
      text: '仪表盘',
      index: '/',
      children: [
        {
          icon: 'el-icon-document',
          text: '分析页',
          index: '/dashboard/analysis'
        },
        {
          icon: 'el-icon-document',
          text: '监控页',
          index: '/dashboard/monitor'
        },
        {
          icon: 'el-icon-document',
          text: '工作台',
          index: '/dashboard/workplace'
        }
      ]
    },
    {
      icon: 'el-icon-edit-outline',
      text: '系统管理',
      index: '/system',
      children: [
        {
          icon: 'el-icon-document',
          text: '系统用户 ',
          index: '/system/user'
        },
        {
          icon: 'el-icon-document',
          text: '角色管理',
          index: '/system/role'
        },
        {
          icon: 'el-icon-document',
          text: '路由菜单',
          index: '/system/menu'
        },
        {
          icon: 'el-icon-document',
          text: '系统配置',
          index: '/system/config'
        },
        {
          icon: 'el-icon-document',
          text: '系统字典',
          index: '/system/dict'
        }
      ]
    },
    {
      icon: 'el-icon-edit-outline',
      text: '表单页面',
      index: '/form',
      children: [
        {
          icon: 'el-icon-document',
          text: '基础表单',
          index: '/form/basic'
        },
        {
          icon: 'el-icon-document',
          text: '分步表单',
          index: '/form/step'
        },
        {
          icon: 'el-icon-document',
          text: '高级表单',
          index: '/form/advanced'
        }
      ]
    },
    {
      icon: 'el-icon-s-grid',
      text: '列表页面',
      index: '/list',
      children: [
        {
          icon: 'el-icon-document',
          text: '搜索列表',
          index: '/list/search',
          children: [
            {
              icon: 'el-icon-document',
              text: '搜索列表(文章)',
              index: '/list/search/article'
            },
            {
              icon: 'el-icon-document',
              text: '搜索列表(项目)',
              index: '/list/search/project'
            },
            {
              icon: 'el-icon-document',
              text: '搜索列表(应用)',
              index: '/list/search/application'
            }
          ]
        },
        {
          icon: 'el-icon-document',
          text: '标准列表',
          index: '/list/basic'
        },
        {
          icon: 'el-icon-document',
          text: '卡片列表',
          index: '/list/card'
        },
        {
          icon: 'el-icon-document',
          text: '查询列表',
          index: '/list/query'
        },
        {
          icon: 'el-icon-document',
          text: '增删查改',
          index: '/list/crud'
        },
        {
          icon: 'el-icon-document',
          text: '树结构列表',
          index: '/list/tree'
        }
      ]
    },
    {
      text: '详情页面',
      index: '/profile',
      icon: 'el-icon-monitor',
      children: [
        {
          icon: 'el-icon-document',
          text: '基础详情页',
          index: '/profile/basic'
        },
        {
          icon: 'el-icon-document',
          text: '高级详情页',
          index: '/profile/advanced'
        }
      ]
    }
  ]
  return {
    code: 0,
    data: {
      total: menus.length,
      list: menus
    }
  }
}