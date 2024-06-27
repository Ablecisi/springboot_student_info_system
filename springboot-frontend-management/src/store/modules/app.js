import Cookies from 'js-cookie'

// 定义状态
const state = {
  // 侧边栏状态，从Cookies中获取，如果没有则默认为打开状态
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false // 侧边栏切换时是否有动画
  },
  // 设备类型，默认为电脑端
  device: 'desktop'
}

// 定义变更状态的方法
const mutations = {
  // 切换侧边栏状态
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened // 取反
    state.sidebar.withoutAnimation = false // 现在是有动画，反之没有动画
    if (state.sidebar.opened) { // 如果侧边栏是打开状态
      Cookies.set('sidebarStatus', 1) // 设置Cookies
    } else { // 如果侧边栏是关闭状态
      Cookies.set('sidebarStatus', 0) // 设置Cookies
    }
  },
  // 关闭侧边栏
  CLOSE_SIDEBAR: (state, withoutAnimation) => { // 传入参数withoutAnimation
    Cookies.set('sidebarStatus', 0) // 设置Cookies
    state.sidebar.opened = false // 关闭侧边栏
    state.sidebar.withoutAnimation = withoutAnimation // 设置是否有动画
  },
  // 切换设备类型
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  }
}

// 定义动作
const actions = {
  // 切换侧边栏状态的动作
  toggleSideBar({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  // 关闭侧边栏的动作
  closeSideBar({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  // 切换设备类型的动作
  toggleDevice({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  }
}

// 导出模块
export default {
  namespaced: true,
  state,
  mutations,
  actions
}
