module.exports = {
  devServerPort: 8000,
  previewServerPort: 9001,
  serverPort: 8088,
  devServerProxy: {
    '/api/': {
        target: 'http://127.0.0.1',
        ws: true,
        secure: false,
        pathRewrite: {'^/api/': '/'},
        changeOrigin: true
    },
    '/wx/': {
        target: 'http://127.0.0.2',
        ws: true,
        secure: false,
        pathRewrite: {'^/wx/': '/'},
        changeOrigin: true
    }
  },
  publicPath: '/',
  lintOnSave: true,
  mock: true,
  microApp: false
}
