const TokenKey = 'xinrun_token' // 定义Token的键

// 获取Token
export function getToken() {
  return localStorage.getItem(TokenKey); // 从本地存储中获取Token
}

// 设置Token
export function setToken(token) {
  return localStorage.setItem(TokenKey,token); // 在本地存储中设置Token
}

// 移除Token
export function removeToken() {
  return localStorage.removeItem(TokenKey); // 从本地存储中移除Token
}
