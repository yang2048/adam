module.exports = {
  name: '用户信息',   
  vuex: false,
  model: [
    {
      title: '用户信息', 
      path: '/api/sysUser',
      name: 'sysUserApi',
      transform: 'convertTable'
    },
    {
      title: '机构列表', 
      path: '/api/sysDept/listDept',
      name: 'getDeptTree',
      methods: false,
      transform: 'convertTree',
      method: 'get',
      options: {
        method: 'get'
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
