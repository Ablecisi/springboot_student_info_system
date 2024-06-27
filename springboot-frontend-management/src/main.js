import Vue from 'vue' // 引入vue

import 'normalize.css/normalize.css' // CSS重置的现代替代方案
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // 全局 css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // 图标
import '@/permission' // 权限控制

if (process.env.NODE_ENV === 'production') { // 如果是生产环境
  const { mockXHR } = require('../mock') // 引入mock文件
  mockXHR() // 如果是生产环境，执行mockXHR函数
}

Vue.use(ElementUI) // 使用ElementUI库
Vue.config.productionTip = false // 关闭生产提示

new Vue({
  el: '#app', // 挂载点
  router, // 使用的路由
  store, // 使用的Vuex存储
  render: h => h(App) // 渲染App组件
})
