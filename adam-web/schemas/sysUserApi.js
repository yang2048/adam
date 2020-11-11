module.exports = {
    vuex: false,
    model: [
        {
            path: '/sysDept',
            name: 'getDeptTree',
            methods: false,
            options: {
                method: 'get'
            }
        },
        {
            path: '/sysUser/login',
            name: 'login',
            methods: false,
            options: {
                method: 'get'
            }
            // template: 'mockForExampleLogin'
        },
    ]
}
