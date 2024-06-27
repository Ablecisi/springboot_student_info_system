const Mock = require('mockjs') // 引入mockjs库
const { param2Obj } = require('./utils') // 引入utils

const user = require('./user') // 引入user模块
const table = require('./table') // 引入table模块

const mocks = [
  ...user,
  ...table
]

// 为前端模拟数据
// 会重新定义XMLHttpRequest。
// 导致许多第三方库失效(如进度事件)。
function mockXHR() {
  // 模拟 地址
  // https://github.com/nuysoft/Mock/issues/300 错误问题
// 保存原始的send方法
  Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
// 重写send方法
  Mock.XHR.prototype.send = function () {
    // 如果存在自定义的xhr
    if (this.custom.xhr) {
      // 设置withCredentials属性
      this.custom.xhr.withCredentials = this.withCredentials || false

      // 如果存在responseType，设置responseType属性
      if (this.responseType) {
        this.custom.xhr.responseType = this.responseType
      }
    }
    // 调用原始的send方法
    this.proxy_send(...arguments)
  }

// 定义一个函数，将respond包装成Express请求处理函数的形式
  function XHR2ExpressReqWrap(respond) {
    return function (options) {
      let result = null
      // 如果respond是函数
      if (respond instanceof Function) {
        const {body, type, url} = options
        // 调用respond函数，传入一个模拟的Express请求对象
        result = respond({
          method: type,
          body: JSON.parse(body),
          query: param2Obj(url)
        })
      } else {
        // 如果respond不是函数，直接返回respond
        result = respond
      }
      // 使用Mock.mock生成模拟数据
      return Mock.mock(result)
    }
  }

// 遍历mocks数组，对每个元素调用Mock.mock方法
  for (const i of mocks) {
    Mock.mock(new RegExp(i.url), i.type || 'get', XHR2ExpressReqWrap(i.response))
  }

// 导出mocks数组和mockXHR函数
  module.exports = {
    mocks,
    mockXHR
  }
}
