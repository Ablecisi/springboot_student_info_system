<template>
  <!-- 根据showLogo的值决定是否添加has-logo类名 -->
  <div :class="{'has-logo':showLogo}">
    <!-- 如果showLogo为true，显示Logo组件 -->
    <logo v-if="showLogo" :collapse="isCollapse" />
    <!-- 使用el-scrollbar组件实现滚动条 -->
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <!-- 使用el-menu组件实现菜单 -->
      <!-- 默认激活的菜单项 -->
      <!-- 是否折叠 -->
      <!-- 背景颜色 -->
      <!-- 文本颜色 -->
      <!-- 是否只保持一个子菜单的展开 -->
      <!-- 激活菜单项的文本颜色 -->
      <!-- 是否开启折叠动画 -->
      <!-- 菜单模式 -->
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <!-- 使用v-for指令遍历routes数组，为每个路由生成一个sidebar-item组件 -->
        <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex' // 引入vuex的mapGetters
import Logo from './Logo' // 引入Logo组件
import SidebarItem from './SidebarItem' // 引入SidebarItem组件
import variables from '@/styles/variables.scss' // 引入样式变量

export default {
  components: { SidebarItem, Logo }, // 定义组件
  computed: {
    ...mapGetters([
      'sidebar' // 从vuex中获取sidebar状态
    ]),
    routes() { // 计算属性，获取路由数组
      return this.$router.options.routes
    },
    activeMenu() { // 计算属性，获取当前激活的菜单项
      const route = this.$route
      const { meta, path } = route
      // 如果设置了路径，侧边栏将突出显示您设置的路径
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() { // 计算属性，决定是否显示Logo
      return true
    },
    variables() { // 计算属性，获取样式变量
      return variables
    },
    isCollapse() { // 计算属性，决定是否折叠侧边栏
      return !this.sidebar.opened // 如果sidebar.opened为false，返回true
    }
  }
}
</script>
