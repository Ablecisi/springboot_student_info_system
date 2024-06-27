<template>
  <!-- 根据classObj计算的类名，设置app-wrapper的类名 -->
  <div :class="classObj" class="app-wrapper">
    <!-- 如果设备是移动设备且侧边栏已打开，显示一个可点击的遮罩层 -->
    <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside" />
    <!-- 侧边栏组件 -->
    <sidebar class="sidebar-container" />
    <!-- 主要内容区域 -->
    <div class="main-container">
      <!-- 如果fixedHeader为true，添加fixed-header类名 -->
      <div :class="{'fixed-header':fixedHeader}">
        <!-- 导航栏组件 -->
        <navbar />
      </div>
      <!-- 主要内容组件 -->
      <app-main />
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain } from './components' // 导入Navbar、Sidebar和AppMain组件
import ResizeMixin from './mixin/ResizeHandler' // 导入ResizeHandler混入

export default {
  name: 'Layout', // 组件名
  components: {
    Navbar, // 导入Navbar组件
    Sidebar, // 导入Sidebar组件
    AppMain // 导入AppMain组件
  },
  mixins: [ResizeMixin], // 使用ResizeMixin混入
  computed: {
    sidebar() { // 计算属性，获取侧边栏的状态
      return this.$store.state.app.sidebar
    },
    device() { // 计算属性，获取设备类型
      return this.$store.state.app.device
    },
    fixedHeader() { // 计算属性，获取是否固定头部的设置
      return this.$store.state.settings.fixedHeader
    },
    classObj() { // 计算属性，根据侧边栏的状态和设备类型，计算app-wrapper的类名
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    }
  },
  methods: {
    handleClickOutside() { // 方法，点击遮罩层时关闭侧边栏
      this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar{
      position: fixed;
      top: 0;
    }
  }
  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
