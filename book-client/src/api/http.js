// 工具类
import axios from 'axios'
axios.defaults.timeout = 5000 // 超时时间
axios.defaults.withCredentials = true // 允许跨域
// 相应头contentType
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'//application/json;charset=UTF-8
// 配置基础url
axios.defaults.baseURL = 'http://localhost:8888'
// axios.defaults.baseURL = 'http://47.120.7.171:8888'

// 相应拦截器
axios.interceptors.response.use(
  response => {
    // 如果response里面的status是200则访问到接口，否则错误
    if (response.status == 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  error => {
    if (error.response.status) { // 如果有值，返回带错误码的返回值
      switch (error.response.status) { // 判断返回值进行操作
        case 401: // 未登录
          router.replace({
            path: '/',
            query: {
              redirect: router.currentRoute.fullPath
            }
          })
          break;
        case 404: // 没找到
          break;
      }
      return Promise.reject(error.response)
    }
  }
)

/**封装get方法 */
export function get(url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, { params: params })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

/**封装post方法 */
export function post(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}
