import { login, logout, getInfo } from '@/api/user' // 用户登录、注销、获取用户信息
import { getToken, setToken, removeToken } from '@/utils/auth' // token
import { resetRouter } from '@/router' // 重置路由

const getDefaultState = () => {
  return {
    token: getToken(), // token
    name: '', // 用户名
    avatar: '' // 头像
  }
}

const state = getDefaultState() // 默认状态

const mutations = { // 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation
  RESET_STATE: (state) => { // 重置状态
    Object.assign(state, getDefaultState()) // 将 state 重置为默认状态
  },
  SET_TOKEN: (state, token) => { // 设置 token
    state.token = token // 将 token 设置为传入的 token
  },
  SET_NAME: (state, name) => { // 设置用户名
    state.name = name // 将用户名设置为传入的用户名
  },
  SET_AVATAR: (state, avatar) => { // 设置头像
    state.avatar = avatar // 将头像设置为传入的头像
  }
}

const actions = {
  // Action 提交的是 mutation，而不是直接变更状态。
  // Action 可以包含任意异步操作
  // 用户登录
  // commit 提交 mutation，userInfo 用户信息，由用户名和密码组成
  login({ commit }, userInfo) {
    const { username, password } = userInfo // 用户名、密码
    return new Promise((resolve, reject) => { // 返回一个 Promise 对象
      // 调用登录接口
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response.data // 获取返回的数据
        commit('SET_TOKEN', data.token) // 设置 token
        setToken(data.token) // 将 token 存储在 cookie 中
        resolve() // 成功时 resolve
      }).catch(error => { // 失败时 reject
        reject(error) // 返回错误信息
      })
    })
  },

  // 获取用户信息
  getInfo({ commit, state }) { // commit 提交 mutation，state 状态
    return new Promise((resolve, reject) => { // 返回一个 Promise 对象
      getInfo(state.token).then(response => { // 调用获取用户信息接口
        const { data } = response // 获取返回的数据

        if (!data) { // 如果没有返回数据
          return reject('验证失败，请重新登录。') // 返回错误信息
        }

          const { name, avatar } = data // 获取用户名、头像

        commit('SET_NAME', name) // 设置用户名
        commit('SET_AVATAR', avatar) // 设置头像
        resolve(data) // 成功时 resolve
      }).catch(error => {   // 失败时 reject
        reject(error) // 返回错误信息
      })
    })
  },

  // 用户注销
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // 必须先移除令牌,也就是这句必须放在前面
        resetRouter() // 重置路由
        commit('RESET_STATE') // 重置状态
        resolve() // 成功时 resolve
      }).catch(error => { // 失败时 reject
        reject(error) // 返回错误信息
      })
    })
  },

  // 删除 token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken()  // 移除 token
      commit('RESET_STATE') // 重置状态
      resolve()  // 成功时 resolve
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

