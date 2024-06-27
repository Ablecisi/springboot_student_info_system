<template>
  <!-- 根据collapse的值决定是否添加collapse类名 -->
  <div class="sidebar-logo-container" :class="{'collapse':collapse}">
    <!-- 使用transition组件实现过渡动画 -->
    <transition name="sidebarLogoFade">
      <!-- 使用router-link组件实现路由链接，点击时跳转到首页 -->
      <router-link key="collapse" class="sidebar-logo-link" to="/">
        <!-- 显示Logo图片 -->
        <img src="https://ablecisi-springboot-web-framework00.oss-cn-chengdu.aliyuncs.com/after_ship.png" class="sidebar-logo" alt="">
        <!-- 显示标题 -->
        <h1 class="sidebar-title">{{ title }} </h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'SidebarLogo', // 组件名
  props: {
    collapse: { // collapse属性，表示是否折叠
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      title: '学生信息管理系统', // 标题
      logo: 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png' // 备用Logo图片的URL
    }
  }
}
</script>

<style lang="scss" scoped>
// 定义过渡动画的样式
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

// 定义sidebar-logo-container的样式
.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #2b2f3a;
  text-align: center;
  overflow: hidden;

  // 定义sidebar-logo-link的样式
  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    // 定义sidebar-logo的样式
    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    // 定义sidebar-title的样式
    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      line-height: 50px;
      font-size: 14px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
    }
  }

  // 当collapse类名存在时，修改sidebar-logo的样式
  &.collapse {
    .sidebar-logo {
      margin-right: 0;
    }
  }
}
</style>
