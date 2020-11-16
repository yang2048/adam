module.exports = {
  name: '系统字典',   
  vuex: false,
  model: [
    {
      title: '用户信息', 
      path: '/api/sysDict',
      name: 'sysDictApi',
      transform: 'convertTable'
    }
  ]
};
