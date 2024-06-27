import axios from 'axios' // 引入axios库
import Router from '@/router' // 引入路由
import { removeToken, getToken } from '@/utils/auth' // 引入auth中的方法

// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // 基础URL, 定义在.env文件中
  withCredentials: true, // 允许跨域请求携带cookie
  timeout: 500000 // 请求超时时间
})

// axios请求拦截器
service.interceptors.request.use(
  config => {
    const token = getToken() // 获取token
    if (token) {
      config.headers['token'] = token // 如果token存在，将token添加到请求头
    }
    return config // 返回配置
  },
  error => {
    console.log(error) // 打印错误信息
    return Promise.reject(error) // 返回错误
  }
)

// axios响应拦截器
service.interceptors.response.use(res => {
  if (res.data.code === 0 && res.data.msg === 'NOT_LOGIN') { // 如果返回的数据中code为0且msg为'NOT_LOGIN'
    console.log('用户未登录, 直接跳转至登录页面') // 打印信息
    removeToken() // 移除token
    Router.replace('/login') // 跳转到登录页面
    return res // 返回响应
  } else {
    return res // 返回响应
  }
},

error => {
  console.log('err' + error) // 打印错误信息
  let { message } = error // 获取错误信息
  if (message === 'Network Error') { // 如果错误信息为'Network Error'
    message = '后端接口连接异常' // 修改错误信息
  } else if (message.includes('timeout')) { // 如果错误信息包含'timeout'
    message = '系统接口请求超时' // 修改错误信息
  } else if (message.includes('请求失败，状态码错误')) { // 如果错误信息包含'请求失败，状态码错误'
    message = '系统接口' + message.substr(message.length - 3) + '异常' // 修改错误信息
  }
  return Promise.reject(error) // 返回错误
})

export default service // 导出service
