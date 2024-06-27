import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'//svg组件

// 全局注册
Vue.component('svg-icon', SvgIcon)

const req = require.context('./svg', false, /\.svg$/) // 正则匹配以.svg结尾的文件
const requireAll = requireContext => requireContext.keys().map(requireContext) // 获取所有文件
// require.context()返回一个函数，这个函数可以接收一个参数：request，这个request参数是指在require()语句中的表达式
requireAll(req)
