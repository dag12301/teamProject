export default {
  namespaced: true,
  state: {
    pagingNotices: [],
    pagingQueAn: [],
    pagingFQA: [],
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
    setPagingNotices(state, payload) {
      //Notices페이지 정보
      return (state.pagingNotices = payload);
    },
    setPagingQueAn(state, payload) {
      //QueAn페이지 정보
      return (state.pagingQueAn = payload);
    },
    setPagingFQA(state, payload) {
      //FQA페이지 정보
      return (state.pagingFQA = payload);
    },
    nullCenterQueAn(state, payload) {
      //QueAn페이지 삭제
      return (state.pagingFQA = payload);
    },
  },
  actions: {},
};
