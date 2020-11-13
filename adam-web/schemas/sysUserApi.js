module.exports = {
  name: '用户信息',   
  vuex: false,
  model: [
    {
      title: '机构列表', 
      path: '/api/sysDept/listDept',
      name: 'getDeptTree',
      methods: false,
      transform: 'convertTree',
      options: {
        method: 'get'
      }
    },
    // {
    //   title: '用户信息', 
    //   path: '/api/sysUser',
    //   name: 'sysUser',
    //   transform: 'convertTable'
    // },
    {
      title: '保存用户', 
      path: '/api/sysUser',
      name: 'saveUser',
      methods: false,
      options: {
        method: 'post'
      }
    },
    {
      title: '更新用户', 
      path: '/api/sysUser/:id',
      name: 'updateUser',
      methods: false,
      options: {
        method: 'patch'
      }
    },
    {
      title: '获取用户', 
      path: '/api/sysUser/:id',
      name: 'getUser',
      methods: false,
      options: {
        method: 'get'
      }
    },
    {
      title: '获取用户分页', 
      path: '/api/sysUser',
      name: 'pageUser',
      methods: false,
      transform: 'convertTable',
      options: {
        method: 'get'
      }
    },
    {
      title: '删除用户', 
      path: '/api/sysUser/:id',
      name: 'removeUser',
      methods: false,
      options: {
        method: 'delete'
      }
    },
    {
      title: '批量删除用户', 
      path: '/api/sysUser/batchRemove',
      name: 'batchRemoveUser',
      methods: false,
      options: {
        method: 'post'
      }
    },
    {
      path: '/api/sysUser/login',
      name: 'login',
      methods: false,
      options: {
        method: 'get'
      }
      // template: 'mockForExampleLogin'
    }
  ]
};
