<template>

  <!-- 导航组件 -->
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <!-- 使用transition-group组件包裹el-breadcrumb-item组件，实现导航的过渡效果 -->
    <transition-group name="breadcrumb">
      <!-- 遍历levelList数组，生成导航项 -->
      <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">

        <!-- 如果item的redirect属性为'noRedirect'或者当前项是最后一项，则显示文本，不可点击 -->
        <span v-if="item.redirect==='noRedirect'||index===levelList.length-1" class="no-redirect">{{ item.meta.title }}</span>
        <!-- 否则，显示为可点击的链接 -->
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>

      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>

</template>

<script>
import pathToRegexp from 'path-to-regexp' // 引入path-to-regexp库，用于处理路径字符串

export default {
  data() {
    return {
      levelList: null // 用于存储导航的数据
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb() // 当路由变化时，重新获取导航数据
    }
  },
  created() {
    this.getBreadcrumb() // 组件创建时，获取导航数据
  },
  // 导航组件的方法
  methods: {
    getBreadcrumb() {
      // 只显示带有meta.title的路由
      // 获取当前路由的matched数组，过滤出meta.title属性存在的项
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      const first = matched[0] // 获取第一个路由项

      if (!this.isDashboard(first)) { // 如果第一个路由项不是Dashboard，则手动添加一个Dashboard项
        matched = [{ path: '/dashboard', meta: { title: 'Dashboard' }}].concat(matched)
      }
      // 将matched数组赋值给levelList
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
    },
    // 判断当前路由是否是Dashboard
    isDashboard(route) {
      const name = route && route.name
      if (!name) { // 如果没有name属性，则返回false
        return false
      }// 如果name属性的值为'Dashboard'，则返回true
      return name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
    },
    // 将路径字符串编译为路径函数
    pathCompile(path) {
      // 获取当前路由的params对象
      const { params } = this.$route
      // 使用path-to-regexp库的compile方法，将路径字符串编译为路径函数
      var toPath = pathToRegexp.compile(path)
      // 调用路径函数，传入params对象，返回编译后的路径
      return toPath(params)
    },
    handleLink(item) { // 点击导航项时的处理方法
      const { redirect, path } = item // 获取当前项的redirect和path属性
      if (redirect) { // 如果redirect属性存在，则跳转到redirect指定的路径
        this.$router.push(redirect) // 跳转到redirect指定的路径
        return // 结束方法
      }
      this.$router.push(this.pathCompile(path)) // 跳转到path指定的路径
    }
  }
}
</script>

<style lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
