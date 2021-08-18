export default {
  namespaced: true,
  state: {
    notices: [],
    queAn: [],
    FQA: [],
  },
  getters: {
    getCenterList(state) {
      state.notices.array.forEach((element) => {
        return element;
      });
    },
    AllCenterList(state) {
      return state.notices.length;
    },
  },
  mutations: {
    setCenternotices(state, payload) {
      return state.notices.push(payload);
    },
    setCenterQueAn(state, payload) {
      return state.queAn.push(payload);
    },
    setCenterFQA(state, payload) {
      return state.FQA.push(payload);
    },
  },
  actions: {},
};
