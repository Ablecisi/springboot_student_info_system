import request from '@/utils/request'

export function findAll() {
  return request({
    url: '/class',
    method: 'get'
  })
}

export function add(classes) {
  return request({
    url: '/class',
    method: 'post',
    data: classes
  })
}

export function update(classes) {
  return request({
    url: '/class',
    method: 'put',
    data: classes
  })
}

export function deleteById(id) {
  return request({
    url: '/class/' + id,
    method: 'delete'
  })
}

export function selectById(id) {
  return request({
    url: '/class/' + id,
    method: 'get'
  })
}
