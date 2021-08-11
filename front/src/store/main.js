export default {
  namespaced: true,
  state: {
    isSideOn: false,
  },
  getters: {
    pop(state) {
      return state.isSideOn;
    },
  },
  mutations: {
    toggleSideBar(state) {
      state.isSideOn = !state.isSideOn;
      console.log("사이드바 현재 상태 : " + state.isSideOn);
    },
  },
};
