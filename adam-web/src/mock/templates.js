/**
 * 以函数的方式自定义模拟数据模板
 *
 * @module mock/templates
 *
 * @example 模板定义方法
 *
 * export function templateFuncName(Mock, url, query, options) {
 *    return {code:0, data:[], msg: ''}
 * }
 *
 */
export function staticDict() {
  return {
    select: [{ title: '选择1', key: 'a1', other: '其他值' }, { title: '选择2', key: 'a2' }, { title: '选择3', key: 'a3' }],
    simple: { 1: '苹果', 2: '梨子', 3: '香蕉', 4: '橙子', 5: '樱桃' }
  };
};

export * from './example'
