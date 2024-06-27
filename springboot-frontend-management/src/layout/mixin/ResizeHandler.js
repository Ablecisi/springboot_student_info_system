import store from '@/store'

const { body } = document
const WIDTH = 992 // 参考Bootstrap的响应式设计

export default {
  watch: {
    $route(route) {
      // 如果设备是移动设备并且侧边栏已打开，则关闭侧边栏
      if (this.device === 'mobile' && this.sidebar.opened) {
        store.dispatch('app/closeSideBar', { withoutAnimation: false })
      }
    }
  },
  beforeMount() {
    // 在组件挂载前添加resize事件监听
    window.addEventListener('resize', this.$_resizeHandler)
  },
  beforeDestroy() {
    // 在组件销毁前移除resize事件监听
    window.removeEventListener('resize', this.$_resizeHandler)
  },
  mounted() {
    // 在组件挂载后判断是否为移动设备，如果是则关闭侧边栏
    const isMobile = this.$_isMobile()
    if (isMobile) {
      store.dispatch('app/toggleDevice', 'mobile')
      store.dispatch('app/closeSideBar', { withoutAnimation: true })
    }
  },
  methods: {
    // 使用$_表示mixin的属性
    // https://vuejs.org/v2/style-guide/index.html#Private-property-names-essential
    $_isMobile() {
      // 获取body元素的矩形信息
      const rect = body.getBoundingClientRect()
      // 如果宽度小于992则判断为移动设备
      return rect.width - 1 < WIDTH
    },
    $_resizeHandler() {
      // 当文档不隐藏时执行
      if (!document.hidden) {
        const isMobile = this.$_isMobile()
        // 切换设备类型
        store.dispatch('app/toggleDevice', isMobile ? 'mobile' : 'desktop')

        // 如果是移动设备则关闭侧边栏
        if (isMobile) {
          store.dispatch('app/closeSideBar', { withoutAnimation: true })
        }
      }
    }
  }
}
