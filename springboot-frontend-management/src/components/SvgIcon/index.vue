<template>
  <!-- 如果是外部图标，使用div元素 -->
  <div v-if="isExternal" :style="styleExternalIcon" class="svg-external-icon svg-icon" v-on="$listeners" />
  <!-- 如果是内部图标，使用svg元素 -->
  <svg v-else :class="svgClass" aria-hidden="true" v-on="$listeners">
    <!-- 使用use元素引用图标 -->
    <use :xlink:href="iconName" />
  </svg>
</template>

<script>
import { isExternal } from '@/utils/validate' // 引入验证工具函数

export default {
  name: 'SvgIcon', // 组件名
  props: {
    iconClass: { // 图标类名
      type: String,
      required: true
    },
    className: { // 自定义类名
      type: String,
      default: ''
    }
  },
  computed: {
    isExternal() { // 计算属性，判断图标是否为外部图标
      return isExternal(this.iconClass)
    },
    iconName() { // 计算属性，生成图标名
      return `#icon-${this.iconClass}`
    },
    svgClass() { // 计算属性，生成svg元素的类名
      if (this.className) {
        return 'svg-icon ' + this.className
      } else {
        return 'svg-icon'
      }
    },
    styleExternalIcon() { // 计算属性，生成外部图标的样式
      return {
        mask: `url(${this.iconClass}) no-repeat 50% 50%`,
        '-webkit-mask': `url(${this.iconClass}) no-repeat 50% 50%`
      }
    }
  }
}
</script>

<style scoped>
.svg-icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.svg-external-icon {
  background-color: currentColor;
  mask-size: cover!important;
  display: inline-block;
}
</style>
