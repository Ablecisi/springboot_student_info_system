import request from '@/utils/request'

export function page(name, gender, page, pageSize, birthday) {
  return request({
    url: '/student?name=' + name + '&gender=' + gender + '&page=' + page + '&pageSize=' + pageSize + '&birthday=' + birthday,
    method: 'get'
  })
}

export function add(student) {
  return request({
    url: '/student',
    method: 'post',
    data: student
  })
}

export function update(student) {
  return request({
    url: '/student',
    method: 'put',
    data: student
  })
}

export function deleteByNumber(number) {
  return request({
    url: '/student/' + number,
    method: 'delete'
  })
}

export function selectByNumber(number) {
  return request({
    url: '/student/' + number,
    method: 'get'
  })
}

