export default {
  namespaced: true,
  state: {
    isSideOn: false, // 사이드바 상태
    isAkiOn: true,
  },
  getters: {
    pop(state) {
      return state.isSideOn;
    },
    aki(state) {
      return state.isAkiOn;
    },
  },
  mutations: {
    toggleSideBar(state) {
      state.isSideOn = !state.isSideOn;
      console.log("사이드바 현재 상태 : " + state.isSideOn);
    },
    toggleAki(state) {
      state.isAkiOn = !state.isAkiOn;
      console.log("아키네이터 현재 상태 : " + state.isAkiOn);
    },
  },
};
