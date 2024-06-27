import request from '@/utils/request'

export function page(page, pageSize) {
  return request({
    url: '/student/report?page=' + page + '&pageSize=' + pageSize,
    method: 'get'
  })
}
export function ZhanBi() {
  return request({
    url: '/student/ZhanBi',
    method: 'get'
  })
}
