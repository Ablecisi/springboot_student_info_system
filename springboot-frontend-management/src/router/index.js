import Vue from 'vue' // 导入Vue
import Router from 'vue-router' // 导入Vue的Router插件

// 使用Vue的Router插件
Vue.use(Router)

// 导入Layout组件
import Layout from '@/layout'

// 定义常量路由
export const constantRoutes = [
  {
    path: '/login', // 登录页面的路由
    component: () => import('@/views/login/index'), // 登录页面的组件
    hidden: true // 隐藏此路由
  },

  {
    path: '/404', // 404 页面的路由
    component: () => import('@/views/404'), // 404 页面的组件
    hidden: true // 隐藏此路由
  },

  {
    path: '/', // 根路由
    component: Layout, // 使用Layout组件
    redirect: '/dashboard', // 重定向到/dashboard
    children: [{ // 子路由
      path: 'dashboard', // 仪表盘页面的路由
      name: 'Dashboard', // 路由的名字
      component: () => import('@/views/dashboard/index'), // 仪表盘页面的组件
      meta: { title: '首页', icon: 'dashboard' } // 路由的元信息，包括标题和图标
    }]
  },
  {
    path: '/system', // 系统信息管理页面的路由
    component: Layout, // 使用Layout组件
    redirect: '/system', // 重定向到/system
    name: 'System', // 路由的名字
    meta: { title: '学生信息管理', icon: 'el-icon-s-tools' }, // 路由的元信息，包括标题和图标
    children: [ // 子路由
      {
        path: 'class', // 部门管理页面的路由
        name: 'Class', // 路由的名字
        component: () => import('@/views/class'), // 部门管理页面的组件
        meta: { title: '班级管理', icon: 'el-icon-menu' } // 路由的元信息，包括标题和图标
      },
      {
        path: 'student', // 员工管理页面的路由
        name: 'Student', // 路由的名字
        component: () => import('@/views/student'), // 员工管理页面的组件
        meta: { title: '学生管理', icon: 'el-icon-user-solid' } // 路由的元信息，包括标题和图标
      }
    ]
  },
  // 404页 放在末尾
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // 重置路由器
}

export default router
