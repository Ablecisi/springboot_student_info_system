import defaultSettings from '@/settings' // 导入默认设置

const title = defaultSettings.title || '昕润物流' // 获取默认设置中的标题

// 获取页面标题
export default function getPageTitle(pageTitle) {
  if (pageTitle) { // 如果传入了页面标题
    return `${pageTitle} - ${title}` // 返回页面标题 + 默认标题
  }
  return `${title}` // 返回默认标题
}
