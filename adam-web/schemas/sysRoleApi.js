module.exports = {
  name: '系统角色',   
  vuex: false,
  model: [
    {
      title: '系统角色', 
      path: '/api/sysRole',
      name: 'sysRoleApi',
      transform: 'convertTable'
    }
  ]
};
