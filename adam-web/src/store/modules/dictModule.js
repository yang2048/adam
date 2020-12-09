export default {
  state: {
    option: {
      keyName: 'label',
      titleName: 'value'
    },
    dicts: {},
    singles: {}
  },
  getters: {
    getDict: (state, getters) => (dictCode) => {
      if (dictCode) {
        return state.dicts[dictCode]
      } else {
        return state.dicts
      }
    }
  },
  mutations: {
    DICT_INIT (state, payload) {
      // JSON.parse(sessionStorage.getItem(`dicts`))||{}
      // sessionStorage.setItem(`dicts`, JSON.stringify(userInfo))
      state.dicts = payload
    },
    DICT_REMOVE (state, payload) {
      delete state.dicts[payload]
    },
    DICT_EDIT (state, payload) {
      Object.assign(state.dicts, payload)
    }
  },
  actions: {
    dictInit ({ state, commit, rootState }, payload) {
      console.warn('============== dictInit =================')
      commit('DICT_INIT', payload);
      if (Object.keys(state.dicts).length === 0) {
        console.warn('============== 字典数据未初始化 =================')
      }
    },
    dictRemove ({ state, commit, rootState }, payload) {
      commit('DICT_REMOVE', payload);
    },
    dictEdit ({ state, commit, rootState }, payload) {
      commit('DICT_EDIT', payload);
    }
  }
};
