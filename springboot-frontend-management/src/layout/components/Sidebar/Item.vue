<script>
export default {
  name: 'MenuItem', // 组件名
  functional: true, // 函数式组件
  props: {
    icon: { // 图标属性
      type: String,
      default: ''
    },
    title: { // 标题属性
      type: String,
      default: ''
    }
  },
  render(h, context) { // 渲染函数
    const { icon, title } = context.props // 从props中获取icon和title
    const vnodes = [] // 定义一个空数组用于存放虚拟节点

    if (icon) { // 如果有icon
      if (icon.includes('el-icon')) { // 如果icon包含'el-icon'
        vnodes.push(<i class={[icon, 'sub-el-icon']} />) // 创建一个i元素，类名为icon和'sub-el-icon'
      } else {
        vnodes.push(<svg-icon icon-class={icon}/>) // 否则，创建一个svg-icon元素，icon-class属性为icon
      }
    }

    if (title) { // 如果有title
      vnodes.push(<span slot='title'>{(title)}</span>) // 创建一个span元素，slot属性为'title'，内容为title
    }
    return vnodes // 返回虚拟节点数组
  }
}
</script>

<style scoped>
.sub-el-icon {
  color: currentColor;
  width: 1em;
  height: 1em;
}
</style>
