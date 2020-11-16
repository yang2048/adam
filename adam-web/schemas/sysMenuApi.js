module.exports = {
  name: '系统菜单权限',   
  vuex: false,
  model: [
    {
      title: '菜单权限', 
      path: '/api/sysMenu',
      name: 'sysMenuApi',
      transform: 'convertTable'
    }
  ]
};
