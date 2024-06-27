/**
 * 包含一些常用的工具函数，如时间格式化、URL参数解析等。
 */

/**
 * 将时间解析为字符串
 * @param {(Object|string|number)} time
 * @param {string} cFormat
 * @returns {string | null}
 */
export function parseTime(time, cFormat) {
  // 如果没有传入参数或者时间为空，则返回null
  if (arguments.length === 0 || !time) {
    return null
  }
  // 如果没有传入格式，则默认为'{y}-{m}-{d} {h}:{i}:{s}'
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    // 如果时间是字符串
    if ((typeof time === 'string')) {
      if ((/^[0-9]+$/.test(time))) {
        // 如果是纯数字字符串，则将其转换为整数
        time = parseInt(time)
      } else {
        // 如果是日期字符串，则将其中的'-'替换为'/'，以支持safari浏览器
        time = time.replace(new RegExp(/-/gm), '/')
      }
    }

    // 如果时间是10位的整数，则将其转换为毫秒
    if ((typeof time === 'number') && (time.toString().length === 10)) {
      time = time * 1000
    }
    // 将时间转换为Date对象
    date = new Date(time)
  }
  // 定义格式化对象
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  // 格式化时间
  const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
    const value = formatObj[key]
    // 注意：getDay()在周日时返回0
    if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value ] }
    // 将值转换为字符串，如果长度不足2，则在前面补0
    return value.toString().padStart(2, '0')
  })
  return time_str
}

/**
 * 格式化时间
 * @param {number} time
 * @param {string} option
 * @returns {string}
 */
export function formatTime(time, option) {
  // 如果时间是10位的整数，则将其转换为毫秒
  if (('' + time).length === 10) {
    time = parseInt(time) * 1000
  } else {
    time = +time
  }
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  // 如果时间差小于30秒，则返回'刚刚'
  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    // 如果时间差小于1小时，则返回相应的分钟数
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    // 如果时间差小于24小时，则返回相应的小时数
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    // 如果时间差小于48小时，则返回'1天前'
    return '1天前'
  }
  // 如果传入了格式选项，则按照格式选项格式化时间
  if (option) {
    return parseTime(time, option)
  } else {
    // 否则，返回默认的格式
    return (
      d.getMonth() +
      1 +
      '月' +
      d.getDate() +
      '日' +
      d.getHours() +
      '时' +
      d.getMinutes() +
      '分'
    )
  }
}

/**
 * 将URL参数解析为对象
 * @param {string} url
 * @returns {Object}
 */
export function param2Obj(url) {
  // 获取URL中的查询字符串，并进行解码

  /**
   *
   * decodeURIComponent(url.split('?')[1])：
   * 首先，使用split('?')方法将URL分割为两部分，
   * [1]表示获取问号后面的部分，即查询参数部分。
   * 然后，使用decodeURIComponent函数对查询参数进行解码，
   * 将所有的百分号编码字符转换回原始字符。
   * .replace(/\+/g, ' ')：这一步将查询参数中的所有加号（+）替换为空格。
   * 在URL编码中，加号经常用来替代空格。
   * search.split('&')：然后，使用split('&')方法将查询参数字符串分割为一个数组，
   * 数组的每个元素都是一个键值对字符串，如' key=value '。
   * const obj = {}：创建一个空对象，用于存储解析后的查询参数。
   * searchArr.forEach(v => {...})：遍历查询参数数组，对每个键值对字符串进行处理。
   * 在每次迭代中，使用indexOf('=')找到等号的位置，
   * 然后使用substring方法分别获取键和值，最后将键和值添加到对象中。
   * 这样，就可以将URL中的查询参数解析为一个JavaScript对象，方便后续的使用。
   */
  // 例如：url = 'http://localhost:8080/?name=Tom&age=20'
  // search = 'name=Tom&age=20'

  const search = decodeURIComponent(url.split('?')[1]).replace(/\+/g, ' ')
  if (!search) { // 如果查询字符串为空，则返回空对象
    return {}
  }
  const obj = {}
  const searchArr = search.split('&') // 将查询字符串分割为数组
  // 遍历查询字符串数组
  searchArr.forEach(v => { // v = 'name=Tom'
    const index = v.indexOf('=') // index = 4
    if (index !== -1) {                    // name  =  Tom
      const name = v.substring(0, index) // name
      const val = v.substring(index + 1, v.length) // Tom
      obj[name] = val // {name: 'Tom'}
    }
  })
  return obj // {name: 'Tom', age: '20'}
}
