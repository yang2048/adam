module.exports = {
  name: '系统部门分组',   
  vuex: false,
  model: [
    {
      title: '用户信息', 
      path: '/api/sysDept',
      name: 'sysDeptApi',
      transform: 'convertTable'
    }
  ]
};
