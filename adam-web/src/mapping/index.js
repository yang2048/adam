/**
 * 数据转换函数集合
 * @module mapping/index
 *
 * @example 定义转换函数方式
 *
 * // res: 接口原始响应JSON数据
 * // method: 接口调用的方法名称，不是http的请求类型，是调用 base/api 方法名称，如：fetchUsers
 * // postData: 请求发送的数据对象
 * // params：请求的Url 路径参数数据对象
 * methodName(res, method, postData, params) {
 *    return {code: 0, data: [], msg: ''}
 * }
 */
import {create} from '$ui/utils/tree'

export default {
  convertTable (res, method, postData, params) {
    if (res.data != null && res.data !== []) {
      // if (res.data.records) {
      //   res.data.list = res.data.records
      // }
      res.data.list = res.data.records
    }
    return {code: res.code, data: res.data, msg: res.msg}
  },
  convertTree (res, method, postData, params) {
    const tree = create(res.data, postData.parentId.toString(), 'id', 'parentId')
    return {code: res.code, data: tree, msg: res.msg}
 }
}
