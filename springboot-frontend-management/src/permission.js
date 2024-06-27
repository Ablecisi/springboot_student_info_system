import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // 进度条
import 'nprogress/nprogress.css' // 进度条样式
import { getToken } from '@/utils/auth' // 从cookie中获取token
import getPageTitle from '@/utils/get-page-title' // 获取页面标题

NProgress.configure({ showSpinner: false }) // NProgress配置

const whiteList = ['/login'] // 不需要重定向的白名单

// 在每次路由改变前执行
router.beforeEach(async(to, from, next) => {
  NProgress.start() // 开始进度条
  document.title = getPageTitle(to.meta.title) // 设置页面标题

  const hasToken = getToken() // 获取token

  if (hasToken) { // 如果有token
    if (to.path === '/login') { // 如果是登录页面
      next({ path: '/' }) // 重定向到首页
      NProgress.done() // 结束进度条
    } else { // 如果不是登录页面
      const hasGetUserInfo = store.getters.name // 获取用户信息
      if (hasGetUserInfo) { // 如果有用户信息
        next() // 继续
      } else { // 如果没有用户信息
        next() // 继续
      }
    }
  } else { // 如果没有token
    if (whiteList.indexOf(to.path) !== -1) { // 如果是白名单中的页面
      next() // 继续
    } else { // 如果不是白名单中的页面
      next(`/login?redirect=${to.path}`) // 重定向到登录页面
      NProgress.done() // 结束进度条
    }
  }
})

// 在每次路由改变后执行
router.afterEach(() => {
  NProgress.done() // 结束进度条
})
