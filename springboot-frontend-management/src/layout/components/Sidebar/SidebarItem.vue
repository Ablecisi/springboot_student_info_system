<template>
  <!-- 如果item.hidden为false，显示内容 -->
  <div v-if="!item.hidden">

    <!-- 如果只有一个子菜单项需要显示，并且该子菜单项没有子菜单或者子菜单都不需要显示，且item.alwaysShow为false -->
    <template v-if="hasOneShowingChild(item.children,item) && (!onlyOneChild.children||onlyOneChild.noShowingChildren)&&!item.alwaysShow">
      <!-- 使用app-link组件实现路由链接，点击时跳转到onlyOneChild.path指定的路由 -->
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path)">
        <!-- 使用el-menu-item组件实现菜单项，index属性为onlyOneChild.path，如果isNest为true，添加submenu-title-noDropdown类名 -->
        <el-menu-item :index="resolvePath(onlyOneChild.path)" :class="{'submenu-title-noDropdown':!isNest}">
          <!-- 使用item组件显示图标和标题 -->
          <item :icon="onlyOneChild.meta.icon||(item.meta&&item.meta.icon)" :title="onlyOneChild.meta.title" />
        </el-menu-item>
      </app-link>
    </template>

    <!-- 如果有多个子菜单项需要显示，或者只有一个子菜单项但该子菜单项有需要显示的子菜单，或者item.alwaysShow为true -->
    <el-submenu v-else ref="subMenu" :index="resolvePath(item.path)" popper-append-to-body>

      <!-- 使用slot="title"插槽显示标题 -->
      <template slot="title">
        <!-- 使用item组件显示图标和标题 -->
        <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title" />
      </template>

      <!-- 使用v-for指令遍历item.children数组，为每个子菜单生成一个sidebar-item组件 -->
      <sidebar-item
        v-for="child in item.children"
        :key="child.path"
        :is-nest="true"
        :item="child"
        :base-path="resolvePath(child.path)"
        class="nest-menu"
      />
    </el-submenu>

  </div>
</template>

<script>
import path from 'path'
import { isExternal } from '@/utils/validate'
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

export default {
  name: 'SidebarItem', // 组件名
  components: { Item, AppLink }, // 定义组件
  mixins: [FixiOSBug], // 混入FixiOSBug
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    this.onlyOneChild = null
    return {}
  },
  methods: {
    // 判断是否只有一个子菜单项需要显示
    hasOneShowingChild(children = [], parent) {
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })

      // 当只有一个子菜单项需要显示时，返回true
      if (showingChildren.length === 1) {
        return true
      }

      // 当没有子菜单项需要显示时，显示父菜单项，并返回true
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ... parent, path: '', noShowingChildren: true }
        return true
      }

      return false
    },
    // 解析路径
    resolvePath(routePath) { // 解析路径
      if (isExternal(routePath)) { // 如果是外部链接
        return routePath // 直接返回
      }
      if (isExternal(this.basePath)) { // 如果basePath是外部链接
        return this.basePath // 直接返回
      }
      return path.resolve(this.basePath, routePath) // 否则返回拼接后的路径
    }
  }
}
</script>
