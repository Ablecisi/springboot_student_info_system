/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) { // 判断是否是外部链接
  return /^(https?:|mailto:|tel:)/.test(path) // 匹配http或https或mailto或tel
}

/**
 * @param {string} str
 * @returns {number}
 */
export function validUsername(str) { // 验证用户名
  return str.length // 返回用户名长度
}
