import { set as setConfig } from '$ui/config'

// 写入运行时配置
setConfig({

  // 路由模式
  router: {
    mode: 'history',
    base: '/'
  },

  // 是否应用自动生成的路由配置
  autoRoutes: true,

  access: {
    // 启用请求权限控制
    axios: true,

    // 启用路由权限控制
    router: true,

    // 缓存存储方式 session 或 local
    storage: 'local',

    // 登录页面路径
    loginPath: '/login',

    // 权限不足页面路径
    authorizePath: '/403',
    
      // 请求拦截函数，axios=true 有效
    request: ({access}, options, config) => {
      // 在这里实现对请求前的处理
      return config
    },
    
    // 请求响应成功拦截函数，axios=true 有效
    response: ({$router}, options, res) => {
      // 在这里实现响应后的处理
      return res
    }
  },
  analysis: false
    /**
   * 属性名映射
   */
  // keys: {
  //   code: 'code',
  //   data: 'data',
  //   message: 'msg',
  //   total: 'total',
  //   list: 'records',
  //   page: 'page',
  //   limit: 'limit',
  //   pages: 'pages'
  // }
})

