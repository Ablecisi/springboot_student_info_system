import defaultSettings from '@/settings' // 引入默认设置

// 从默认设置中获取showSettings, fixedHeader, sidebarLogo的值
const { showSettings, fixedHeader, sidebarLogo } = defaultSettings

// 定义状态
const state = {
  showSettings: showSettings, // 是否显示设置
  fixedHeader: fixedHeader,   // 是否固定头部
  sidebarLogo: sidebarLogo    // 侧边栏是否显示Logo
}

// 定义变更状态的方法
const mutations = {
  // 改变设置
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    // 如果状态中有这个键
    if (state.hasOwnProperty(key)) {
      // 更新这个键的值
      state[key] = value
    }
  }
}

// 定义动作
const actions = {
  // 改变设置的动作
  changeSetting({ commit }, data) {
    // 提交CHANGE_SETTING变更
    commit('CHANGE_SETTING', data)
  }
}

// 导出模块
export default {
  namespaced: true, // 使用命名空间
  state, // 状态
  mutations, // 变更方法
  actions // 动作
}
