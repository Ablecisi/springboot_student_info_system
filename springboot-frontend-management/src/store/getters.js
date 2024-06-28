const getters = {
  sidebar: state => state.app.sidebar, // 侧边栏
  device: state => state.app.device, // 设备
  token: state => state.user.token, // token
  avatar: state => state.user.avatar, // 头像
  name: state => state.user.name // 用户名
}
export default getters // 导出 getters
