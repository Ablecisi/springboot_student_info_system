<template>
  <!-- 点击区域 -->
  <div style="padding: 0 15px;" @click="toggleClick">
    <!-- SVG图标，根据isActive的值改变类名 -->
    <!--xmlns为命名空间-->
    <svg
      :class="{'is-active':isActive}"
      class="hamburger"
      viewBox="0 0 1024 1024"
      xmlns="http://www.w3.org/2000/svg"
      width="64"
      height="64"
    >
      <!-- SVG路径 -->
      <path d="M408 442h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8zm-8 204c0 4.4 3.6 8 8 8h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56zm504-486H120c-4.4 0-8 3.6 8-8v56c0-4.4 3.6 8 8-8v-56c0-4.4-3.6-8-8-8zm0 632H120c-4.4 0-8 3.6-8 8v56c0-4.4 3.6 8 8-8v-56c0-4.4-3.6-8-8-8zM142.4 642.1L298.7 519a8.84 8.84 0 0 0 0-13.9L142.4 381.9c-5.8-4.6-14.4-.5-14.4 6.9v246.3a8.9 8.9 0 0 0 14.4 7z" />
    </svg>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',
  props: {
    // 接收一个布尔类型的属性，用于控制SVG图标的类名
    isActive: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    // 点击事件处理函数，触发一个自定义事件
    toggleClick() {
      this.$emit('toggleClick')
    }
  }
}
</script>

<style scoped>
/*SVG图标的样式*/
.hamburger {
  display: inline-block;
  vertical-align: middle;
  width: 20px;
  height: 20px;
}

/*当isActive为true时，旋转SVG图标*/
.hamburger.is-active {
  transform: rotate(180deg);
}
</style>
