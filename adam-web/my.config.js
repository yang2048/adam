module.exports = {
  devServerPort: 8000,
  previewServerPort: 9001,
  serverPort: 8088,
  devServerProxy: {
    '/api': {
        target: 'http://127.0.0.1:8088',
        ws: true,
        secure: false,
        pathRewrite: {'^/api': '/'},
        changeOrigin: true
    }
  },
  publicPath: '/',
  lintOnSave: true,
  mock: true,
  microApp: false
}
